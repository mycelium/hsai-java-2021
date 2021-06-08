package ru.spbstu.telematics.java.utils;

import java.util.ArrayList;

/**
 * Simple wrapper for stored Double data with name.
 * */
public class Sample {
    private final String name;
    private final ArrayList<Double> values;

    public Sample(String name, ArrayList<Double> values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getValues() {
        return values;
    }
}
