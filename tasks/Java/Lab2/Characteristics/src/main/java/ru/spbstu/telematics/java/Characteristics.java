package ru.spbstu.telematics.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.telematics.java.utils.CharacteristicsCalculator;
import ru.spbstu.telematics.java.utils.Sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Characteristics {
    private final double mean;
    private final double median;
    private final double min;
    private final double max;
    private Logger logger = LoggerFactory.getLogger(Characteristics.class);

    public Characteristics(Sample sample) {
        logger.info("Variable " + sample.getName() + ": characteristics calculations started...");
        ArrayList<Double> tmp = new ArrayList<>(sample.getValues());
        Collections.sort(tmp);
        mean = CharacteristicsCalculator.mean(sample);
        median = CharacteristicsCalculator.median(sample);
        min = tmp.get(0);
        max = tmp.get(tmp.size() - 1);
        logger.info("Variable " + sample.getName() + ": characteristics calculations ended successfully");
    }

    public double getMean() {
        return mean;
    }

    public double getMedian() {
        return median;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public HashMap<String, Double> getCharacteristics() {
        HashMap<String, Double> characteristics = new HashMap<>();
        characteristics.put("Mean", mean);
        characteristics.put("Median", median);
        characteristics.put("Min", min);
        characteristics.put("Max", max);
        return characteristics;
    }
}
