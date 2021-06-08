package ru.spbstu.telematics.java.hsai_java_lab.value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class RandomValueSample {
    private String name;
    private ArrayList<Double> sample;
    private static final Logger logger = LoggerFactory.getLogger(RandomValueSample.class);

    /**
     * Creates new instance of RandomValueSample
     * 
     * @param name Name of the sample
     * @param sample Array of sample data
     * @throws NullPointerException if {@code sample} is {@code null}
     */
    public RandomValueSample(String name, ArrayList<Double> sample) {
        this.name = (name == null) ? "RandomValueSample" : name;
        if (sample == null) {
            logger.error("Sample is null");
            throw new NullPointerException("Sample is null");
        }
        else {
            this.sample = sample;
        }
        logger.info("Random Value Table" + name + "created");
    }

    /**
     * Creates new instance of RandomValueSample with the empty data array
     * 
     * @param name Name of the sample
     * @throws NullPointerException if {@code sample} is {@code null}
     */
    public RandomValueSample(String name) {
        this.name = (name == null) ? "RandomValueSample" : name;
        this.sample = new ArrayList<Double>();
        logger.info("Random Value Table" + name + "created");
    }

    public String getName() {
        return name;
    }

    public ArrayList<Double> getSample() {
        return sample;
    }

    public void addValue(double value) {
        sample.add(value);
    }
}
