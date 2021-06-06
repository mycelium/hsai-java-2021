package ru.spbstu.telematics.java.distributions;

import java.util.ArrayList;
import java.util.Random;

public interface Distribution<T> {

    Random random = new Random();

    /**
     * Randomly generates one value of T type (Integer for discrete distributions, Double for continuous distributions).
     *
     * @return random integer value.
     * */
    T generateValue();

    /**
     * Generates sample of given size (Integer for discrete distributions, Double for continuous distributions).
     *
     * @param size size of the sample.
     * @return sample of values of T type.
     * */
    ArrayList<T> generateSample(int size);

    /**
     * @return true if distribution is discrete, false otherwise.
     * */
    boolean isDiscrete();
}
