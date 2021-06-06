package ru.spbstu.telematics.java.hsai_java_lab.distribution;

import java.util.ArrayList;

public interface Distribution {
    public enum DistributionType {
        UNIFORM,
        NORMAL,
        POISSON
    }

    /**
     * Returns a type of the distribution
     * 
     * @return type of the distribution
     * @see DistributionType
     */
    DistributionType getType();

    /**
     * Generates new random value with the given distributoin
     * 
     * @return random value that has given distribution
     */
    double random();

    /**
     * Generates new sequence of random values with the given distribution
     * 
     * @param size Size of the sequence
     * @return Sequence of the given size of the random values with the given distribution
     */
    ArrayList<Double> randomSample(int size);
}
