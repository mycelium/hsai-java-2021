package ru.spbstu.telematics.java.hsai_java_lab.statistics;

import java.util.ArrayList;
import java.util.Collections;

public class SampleStatistics {
    /**
     * Calculates the nean value of the sample
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
        Collections.sort(sample);

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
