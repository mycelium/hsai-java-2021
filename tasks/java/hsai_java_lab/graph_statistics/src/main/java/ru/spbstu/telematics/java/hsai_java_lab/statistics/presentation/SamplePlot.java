package ru.spbstu.telematics.java.hsai_java_lab.statistics.presentation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries.CategorySeriesRenderStyle;
import org.knowm.xchart.Histogram;

import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueSample;

public class SamplePlot {
    private final String propertyPath = "src/main/resources/plot.properties";

    String plotFolderPath;
    ArrayList<RandomValueSample> samples;

    public SamplePlot(ArrayList<RandomValueSample> samples) {
        if (samples == null) {
            throw new NullPointerException("Samples array is null");
        }

        if (samples.isEmpty()) {
            throw new IllegalArgumentException("Samples array is empty");
        }

        this.samples = samples;

        try (InputStream istream = new FileInputStream(propertyPath)) {
            Properties storageProp = new Properties();
            storageProp.load(istream);
            plotFolderPath = storageProp.getProperty("plot.folder");
        }
        catch (IOException e) {
            plotFolderPath = ".";
            //TODO Добавить логер
        }
    }

    public void plotSamples() {
        for (RandomValueSample s : samples) {
            String fileName = s.getName();
            
            try {
                plotHistogram(s, plotFolderPath + "/" + fileName + "_histo");
                plotData(s, plotFolderPath + "/" + fileName + "_data");
            }
            catch (IOException e) {
                continue; //TODO Добавить логер
            } 
        }
    }

    private void plotHistogram(RandomValueSample sample, String filePath) throws IOException {      
        ArrayList<Double> rawData = new ArrayList<Double>(sample.getSample());
        int binsNumber = (int) Math.ceil(2. * Math.pow((double) rawData.size(), (1. / 3.)));
        Histogram histogram = new Histogram(sample.getSample(), binsNumber);

        CategoryChart chart = new CategoryChart(800, 600);
        chart.setTitle(sample.getName());
        chart.setXAxisTitle("Value");
        chart.setYAxisTitle("Frequency");
        chart.getStyler().setLegendVisible(false);
        chart.addSeries("Sample Histogram", histogram.getxAxisData(), histogram.getyAxisData());

        try {
            BitmapEncoder.saveBitmapWithDPI(chart, filePath, BitmapFormat.PNG, 300);
        }
        catch (IOException e) {
            throw e;
        }
    }

    private void plotData(RandomValueSample sample, String filePath) throws IOException {
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).build();
        ArrayList<Integer> indexArray = new ArrayList<Integer>(sample.getSample().size());
        for (int i = 0; i < sample.getSample().size(); i++) {
            indexArray.add(i);
        }

        chart.setTitle(sample.getName());
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setXAxisTicksVisible(false);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setDefaultSeriesRenderStyle(CategorySeriesRenderStyle.Stick);
        chart.addSeries("Sample Data PLot", indexArray, sample.getSample());

        try {
            BitmapEncoder.saveBitmapWithDPI(chart, filePath, BitmapFormat.PNG, 300);
        }
        catch (IOException e) {
            throw e;
        }
    }
}
