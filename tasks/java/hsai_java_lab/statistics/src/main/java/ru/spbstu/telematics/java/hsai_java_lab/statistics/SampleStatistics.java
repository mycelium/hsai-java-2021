package ru.spbstu.telematics.java.hsai_java_lab.statistics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import ru.spbstu.telematics.java.hsai_java_lab.statistics.data.SampleStatisticsData;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueSample;

public class SampleStatistics {
    /**
     * Creates an array of samples statistic data
     * 
     * @param samples Array of random value samples
     * @return Array of samples statistic data
     * @throws NullPointerException if {@code samples} is {@code null}
     */
    public static ArrayList<SampleStatisticsData> getStatistics(ArrayList<RandomValueSample> samples) {
        if (samples == null) {
            throw new NullPointerException("Samples array is null");
        }

        ArrayList<SampleStatisticsData> samplesData = new ArrayList<SampleStatisticsData>();

        for (RandomValueSample s : samples) {
            SampleStatisticsData newSampleData = new SampleStatisticsData(s.getName());
            newSampleData.addAllStatistics(getSampleStatistics(s.getSample()));
            samplesData.add(newSampleData);
        }

        return samplesData;
    }

    /**
     * Creates a map of sample statistic name and its value
     * 
     * @param sample Array of floating point values
     * @return Map of sample statistic name and its value
     * @throws NullPointerException if {@code sample} is {@code null}
     * @throws IllegalArgumentException if {@code sample} is empty
     */
    private static HashMap<String, Object> getSampleStatistics(ArrayList<Double> sample) {
        if (sample == null) {
            throw new NullPointerException("Smaple is null");
        }

        HashMap<String, Object> statistics = new HashMap<String, Object>();

        if (sample.isEmpty()) {
            statistics.put("mean", "Undefined");
            statistics.put("median", "Undefined");
            statistics.put("min", "Undefined");
            statistics.put("max", "Undefined");
        }
        else {
            statistics.put("mean", mean(sample));
            statistics.put("median", median(sample));
            statistics.put("min", min(sample));
            statistics.put("max", max(sample));
        }

        return statistics;
    }

    /**
     * Calculates the mean value of the sample
     * 
     * @param sample Array of floating point values
     * @return Mean value of the sample
     * @throws NullPointerException if {@code sample} is {@code null}
     * @throws IllegalArgumentException if {@code sample} is empty
     */
    public static double mean(ArrayList<Double> sample) {
        if (sample == null) {
            throw new NullPointerException("Sample is null");
        }

        if (sample.isEmpty()) {
            throw new IllegalArgumentException("Sample is empty");
        }

        double mean = 0.;
        double n = 0.;

        for (Double x : sample) {
            mean = (mean * n + x) / ++n;
        }

        return mean;
    }

    /**
     * Calculates the median value of the sample
     * 
     * @param sample Array of floating point values
     * @return Median value of the sample
     * @throws NullPointerException if {@code sample} is {@code null}
     * @throws IllegalArgumentException if {@code sample} is empty
     */
    public static double median(ArrayList<Double> sample) {
        if (sample == null) {
            throw new NullPointerException("Sample is null");
        }

        int sampleSize = sample.size();

        if (sample.isEmpty()) {
            throw new IllegalArgumentException("Sample is empty");
        }

        ArrayList<Double> orderedSample = new ArrayList<Double>(sample);
        Collections.sort(orderedSample);

        double median;

        if (sampleSize % 2 == 0) {
            median = (orderedSample.get((sampleSize / 2) - 1) + orderedSample.get(sampleSize / 2)) / 2.;
        }
        else {
            median =  orderedSample.get(sampleSize / 2);
        }

        return median;
    }

    /**
     * Calculates the minimum value of the sample
     * 
     * @param sample Array of floating point values
     * @return Minimum value of the sample
     * @throws NullPointerException if {@code sample} is {@code null}
     * @throws IllegalArgumentException if {@code sample} is empty
     */
    public static double min(ArrayList<Double> sample) {
        if (sample == null) {
            throw new NullPointerException("Sample is null");
        }

        if (sample.isEmpty()) {
            throw new IllegalArgumentException("Sample is empty");
        }

        return Collections.min(sample);
    }

    /**
     * Calculates the maximum value of the sample
     * 
     * @param sample Array of floating point values
     * @return Maximum value of the sample
     * @throws NullPointerException if {@code sample} is {@code null}
     * @throws IllegalArgumentException if {@code sample} is empty
     */
    public static double max(ArrayList<Double> sample) {
        if (sample == null) {
            throw new NullPointerException("Sample is null");
        }

        if (sample.isEmpty()) {
            throw new IllegalArgumentException("Sample is empty");
        }

        return Collections.max(sample);
    }
}
