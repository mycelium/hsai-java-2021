package ru.spbstu.telematics.java;

import org.knowm.xchart.*;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.telematics.java.utils.Sample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphOutput {
    private final ArrayList<Sample> data;
    private final ArrayList<String> names;
    private final String dataName;

    private Logger logger = LoggerFactory.getLogger(GraphOutput.class);

    public GraphOutput(ArrayList<Sample> data, String name) {
        this.data = data;
        names = new ArrayList<>();
        for(Sample s: data) {
            names.add(s.getName());
        }
        dataName = name;
    }

    /**
     * Creates graphs for all the variables.
     *
     * @return paths to files with graphs.
     * */
    public String plotGraph(String fileName) {
        String paths = null;
        for(int i = 0; i < data.size(); i++) {
            paths = plotGraphForVariable(i, fileName);
        }
        return paths;
    }

    /**
     * Creates graph for the variable with the specified index.
     *
     * @return path to file with graph.
     * */
    public String plotGraphForVariable(int varNum, String fileName) throws IllegalArgumentException {
        if(0 > varNum || varNum >= data.size()) {
            logger.error("Index " + varNum + " is out of range for list containing " + data.size() + " variables");
            throw new IllegalArgumentException("Index " + varNum + " is out of range for list containing " + data.size() + " variables");
        }
        logger.info("Starting plotting graph for variable " + data.get(varNum).getName() + " ...");
        XYChart chart = new XYChart(800, 600);
        chart.setTitle(dataName);
        chart.setXAxisTitle("X");
        chart.setXAxisTitle(data.get(varNum).getName());
        XYSeries series = chart.addSeries("Value", null, data.get(varNum).getValues());
        series.setMarker(SeriesMarkers.SQUARE);
        logger.info("Graph created successfully");
        return saveChart(fileName + "." + data.get(varNum).getName(), chart);
    }

    /**
     * Creates histograms for all the variables.
     *
     * @return paths to files with histograms.
     * */
    public String plotHisto(String fileName) {
        String paths = null;
        for(int i = 0; i < data.size(); i++) {
            paths = plotHistoForVariable(i, fileName);
        }
        return paths;
    }

    /**
     * Creates histo for the variable with the specified index.
     *
     * @return path to file with histo.
     * */
    public String plotHistoForVariable(int varNum, String fileName) throws IllegalArgumentException {
        if(0 > varNum || varNum >= data.size()) {
            logger.error("Index " + varNum + " is out of range for list containing " + data.size() + " variables");
            throw new IllegalArgumentException("Index " + varNum + " is out of range for list containing " + data.size() + " variables");
        }
        logger.info("Starting plotting histo for variable " + data.get(varNum).getName() + " ...");
        ArrayList<Double> values = new ArrayList<>(data.get(varNum).getValues());
        Collections.sort(values);
        int bins = (int) (1 + Math.log(values.size()));
        double d = (values.get(values.size() - 1) - values.get(0)) / bins + 0.001;
        double min = values.get(0);
        int[] frequencies = new int[bins];
        for (Double v: values) frequencies[(int) Math.floor((v - min) / d)] += 1;
        double[] y = new double[bins];
        double[] x = new double[bins];
        for (int j = 0; j < bins; j++) {
            y[j] = (float) frequencies[j] / values.size();
            x[j] = min + j * d;
        }
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600)
                .title(dataName)
                .xAxisTitle(data.get(varNum).getName())
                .yAxisTitle("Frequency")
                .build();
        chart.getStyler().setAvailableSpaceFill(0.99);
        chart.getStyler().setOverlapped(true);
        chart.addSeries("histogram", x, y);
        logger.info("Histogram created successfully");
        return saveChart(fileName + "." + data.get(varNum).getName(), chart);
    }

    private String saveChart(String filename, Chart chart) {
        logger.info("Saving histo to " + filename + "...");
        String filePath = new File("").getAbsolutePath() + "/Graphs/";
        File directory = new File(filePath);
        directory.mkdir();
        try {
            BitmapEncoder.saveBitmap(chart, filePath + filename, BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            logger.error("Error with saving " + filePath + filename + ": " + e.getMessage());
        }
        return filePath;
    }
}
