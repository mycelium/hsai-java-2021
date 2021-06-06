package ru.spbstu.telematics.utils;

import java.util.Arrays;

public class StatsUtils {
    public static double mean(double[] values) {
        double sum = 0;
        for (double v: values) sum += v;
        return sum / values.length;
    }

    public static double var(double[] values) {
        double mean = mean(values);
        double sum = 0;
        for (double v: values) sum += (v - mean) * (v - mean);
        return sum / values.length;
    }

    public static double[] meanVar(double[] values) {
        double mean = mean(values);
        double sum = 0;
        for (double v: values) sum += (v - mean) * (v - mean);
        return new double[] {mean, sum / values.length};
    }

    public static double medianSort(double[] temp) {
        Arrays.sort(temp);
        int n = temp.length;
        return (temp[n / 2] + temp[(n + 1) / 2]) / 2;
    }

    public static double median(double[] values) {
        double[] temp = values.clone();
        return medianSort(temp);
    }

    public static double[] quartilesSort(double[] temp) {
        Arrays.sort(temp);
        int n = temp.length;
        double lower = (temp[n / 4] + temp[(n + 1) / 4] + temp[(n + 2) / 4] + temp[(n + 3) / 4]) / 4;
        n = 3 * n;
        double upper = (temp[n / 4] + temp[(n + 1) / 4] + temp[(n + 2) / 4] + temp[(n + 3) / 4]) / 4;
        return new double[] {lower, upper};
    }

    public static double[] quartiles(double[] values) {
        double[] temp = values.clone();
        return quartilesSort(temp);
    }
}
