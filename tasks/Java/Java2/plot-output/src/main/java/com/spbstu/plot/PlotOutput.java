package com.spbstu.plot;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYDataset;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PlotOutput implements com.spbstu.data.Output{
    public PlotOutput(ArrayList<Double> data, String filename) {
        this.data = data;
        this.filename = filename;
    }

    ArrayList<Double> data;
    String filename;

    @Override
    public void output() {
        HistogramDataset dataset = new HistogramDataset();

        dataset.addSeries("key", data.stream().mapToDouble(Double::doubleValue).toArray(), 50);

        JFreeChart histogram = ChartFactory.createHistogram("Distribution",
                "y values", "x values", dataset);
        try {
            ChartUtils.saveChartAsPNG(new File(filename), histogram, 450, 400);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
