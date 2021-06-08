package ru.spbstu.telematics.java.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CharacteristicsCalculator {

    public static double mean (Sample sample) {
        ArrayList<Double> tmp = sample.getValues();
        double sum = 0;
        for(Double d: tmp)
            sum += d;
        return (sum / tmp.size());
    }

    public static double median (Sample sample) {
        ArrayList<Double> tmp = new ArrayList<>(sample.getValues());
        Collections.sort(tmp);
        int size = tmp.size();
        return ((tmp.get(size / 2) + tmp.get((size + 1) / 2)) / 2);
    }

    public static HashMap<String, Double> quartiles (Sample sample) {
        HashMap<String, Double> quartiles = new HashMap<>();
        ArrayList<Double> tmp = new ArrayList<>(sample.getValues());
        Collections.sort(tmp);
        int n = tmp.size();
        double lower = (tmp.get(n / 4) + tmp.get((n + 1) / 4) + tmp.get((n + 2) / 4) + tmp.get((n + 3) / 4)) / 4;
        n *= 3;
        double upper = (tmp.get(n / 4) + tmp.get((n + 1) / 4) + tmp.get((n + 2) / 4) + tmp.get((n + 3) / 4)) / 4;
        quartiles.put("Lower", lower);
        quartiles.put("Upper", upper);
        return quartiles;
    }
}
