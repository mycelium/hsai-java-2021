package wsyconan.OutputImage;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.*;
import java.util.*;

public class OutputImage {
    ArrayList<Double> data;
    HashMap<Integer, Integer> dataGroup;
    String fileName;
    String outputPath;
    JFreeChart jFreeChart;
    DefaultCategoryDataset dataset;

    public OutputImage(ArrayList<Double> data, String path, String fileName) {
        this.data = data;
        this.outputPath = path;
        this.fileName = fileName.substring(0, fileName.lastIndexOf("."));
        dataset = new DefaultCategoryDataset();
    }

    public void draw() {
        dataGroup = groupBy();
        for (Integer d : dataGroup.keySet()) {
            dataset.addValue(dataGroup.get(d), "ROW1", d);
        }
        jFreeChart = ChartFactory.createBarChart(
                fileName,
                "Value",
                "Count",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
        try {
            File file = new File(outputPath + fileName + ".png");
            OutputStream out = new FileOutputStream(file);
            ChartUtilities.writeChartAsPNG(out, jFreeChart, 720, 480);
        } catch (IOException e) {

        }
    }

    private LinkedHashMap<Integer, Integer> groupBy() {
        HashMap<Integer, Integer> group = new HashMap<>();
        LinkedHashMap<Integer, Integer> newGroup = new LinkedHashMap<>();
        for (Double d : data) {
            if (!group.containsKey(d.intValue())) {
                group.put(d.intValue(), 1);
            } else group.put(d.intValue(), group.get(d.intValue()) + 1);
        }
        Object[] keys = group.keySet().toArray();
        Arrays.sort(keys);
        for(Object key: keys){
            newGroup.put((Integer) key, group.get(key));
        }
        return newGroup;
    }

}
