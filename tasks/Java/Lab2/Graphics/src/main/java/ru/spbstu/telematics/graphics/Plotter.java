package ru.spbstu.telematics.graphics;

import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ru.spbstu.telematics.dao.DAO;

import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

public class Plotter {
    static Logger logger = LogManager.getLogger(Plotter.class);

    DAO dao;
    int width = 800;
    int height = 600;
    JFrame frame;


    public Plotter(DAO dao) {
        this.dao = dao;
    }

    Plotter(DAO dao, int width, int height) {
        this.dao = dao;
    }

    private XYChart graph(int i) {
        logger.info("Creating graph...");
        assertIndex(i);
        XYChart chart = new XYChart(width, height);
        chart.setTitle(dao.getTitle());
        chart.setXAxisTitle("X");
        chart.setXAxisTitle(dao.getNames()[i]);
        XYSeries series = chart.addSeries("Value", null, dao.getColumn(i));
        series.setMarker(SeriesMarkers.CIRCLE);
        logger.info("Graph created!");
        return chart;
    }

    private void assertIndex(int i) {
        int n = dao.getNames().length;
        if (i < 0 || i >= n) {
            logger.error("Invalid column index: " + i + ".");
            throw new IllegalArgumentException("Invalid column index: " + i + ".");
        }
    }

    private CategoryChart histogram(int i) {
        logger.info("Creating histogram...");
        assertIndex(i);
        double[] vals = dao.getColumn(i).clone();
        Arrays.sort(vals);
        int bins = (int) (1 + Math.log(vals.length));
        double d = (vals[vals.length - 1] - vals[0]) / bins + 0.001;
        double min = vals[0];
        int[] frequencies = new int[bins];
        for (double v: vals) frequencies[(int) Math.floor((v - min) / d)] += 1;
        double[] y = new double[bins];
        double[] x = new double[bins];
        for (int j = 0; j < bins; j++) {
            y[j] = (float) frequencies[j] / vals.length;
            x[j] = min + j * d;
        }
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600)
                .title(dao.getTitle())
                .xAxisTitle(dao.getNames()[i])
                .yAxisTitle("Frequency")
                .build();
        chart.getStyler().setAvailableSpaceFill(0.99);
        chart.getStyler().setOverlapped(true);
        chart.addSeries("histogram", x, y);
        logger.info("Histogram created!");
        return chart;
    }

    public void showGraph(int i) {
        frame = new SwingWrapper<>(graph(i)).displayChart();
    }

    public void saveGraph(int i, String filename) {
        XYChart chart = graph(i);
        logger.info("Saving to " + filename + "...");
        try {
            BitmapEncoder.saveBitmap(chart, filename, BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            logger.error("Error with saving " + filename + ": " + e.getMessage());
        }
    }

    public void saveHistogram(int i, String filename) {
        CategoryChart chart = histogram(i);
        logger.info("Saving to " + filename + "...");
        try {
            BitmapEncoder.saveBitmap(chart, filename, BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            logger.error("Error with saving " + filename + ": " + e.getMessage());
        }
    }

    public void showHistogram(int i) {
        frame = new SwingWrapper<>(histogram(i)).displayChart();
    }
}
