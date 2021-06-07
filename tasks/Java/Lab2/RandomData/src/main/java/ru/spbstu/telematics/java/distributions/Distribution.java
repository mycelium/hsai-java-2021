package ru.spbstu.telematics.java.distributions;

import java.util.ArrayList;
import java.util.Random;

/**
 * This interface generalizes all the distributions needed.
 * @param <T> might be Double for continuous distributions or Integer for discrete ones.
 * */
public interface Distribution<T> {

    Random random = new Random();

    /**
     * Randomly generates one value of T type (Integer for discrete distributions, Double for continuous distributions).
     *
     * @return random integer value
     * */
    T generateValue();

    /**
     * Generates sample of given size (Integer for discrete distributions, Double for continuous distributions).
     *
     * @param size size of the sample
     * @return sample of values of T type
     * */
    default ArrayList<T> generateSample(int size) {
        if(size < 0)
            throw new IllegalArgumentException("Size of the sample cannot be a negative number");

        ArrayList<T> sample = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            sample.add(generateValue());
        }
        return sample;
    }

    /**
     * @return true if distribution is discrete, false otherwise
     * */
    boolean isDiscrete();
}
