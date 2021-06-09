package graph;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.internal.chartpart.Chart;
import sample.Variable;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Painter {

    private List<Variable<Double>> variables;

    public Painter(List<Variable<Double>> variables) {
        this.variables = variables;
    }

    public void plotHists(String dir) throws IOException {
        Path directory = Path.of(dir);
        int counter = 0;
        for (Variable<Double> variable : variables) {
            Path file = Path.of(dir + "/" + variable.getName() + counter++);
            plotHist(variable, file);
        }
    }

    private void plotHist(Variable<Double> variable, Path filePath) throws IOException {
        int numberOfBar = (int) Math.log(variable.size()) + 1;
        double min = variable.stream().max(Double::compareTo).get();
        double width = variable.stream().max(Double::compareTo).get() - min;
        double barWidth = width / numberOfBar;
        List<Long> bars = new ArrayList<>(numberOfBar);
        for (int i = 0; i < numberOfBar; i++) {
            bars.add(0L);
        }
        for (Double value : variable) {
            double pos = value - min;
            int index = (int) (pos / width);
            bars.set(index, bars.get(index));
        }
        List<Double> x = new ArrayList<>();
        for (int i = 0; i < numberOfBar; i++) {
            x.add(min);
            min += barWidth;
        }
        List<Double> barsRelative = bars.stream()
                .map(l -> l / (double) variable.size())
                .collect(Collectors.toList());
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600)
                .title(variable.getName())
                .xAxisTitle("Values")
                .yAxisTitle("Frequency")
                .build();
        chart.getStyler().setAvailableSpaceFill(0.99);
        chart.getStyler().setOverlapped(true);
        chart.addSeries("histogram", x, barsRelative);
        save(chart, filePath);
    }

    private void save(Chart chart, Path filePath) throws IOException {
        BitmapEncoder.saveBitmap(chart, filePath.toString(), BitmapEncoder.BitmapFormat.PNG);
    }

}
