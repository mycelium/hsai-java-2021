package ru.spbstu.telematics.java.data;

import ru.spbstu.telematics.java.distributions.Distribution;
import java.util.ArrayList;

/**
 * This class is used to store the certain distribution with certain parameters.
 * You should avoid making variables without using VariableFactory's "make" methods.
 * */
public class Variable {
    private final String name;
    private final Distribution distribution;

    public Variable(String name, Distribution distribution) {
        this.name = name;
        this.distribution = distribution;
    }

    public String getName() {
        return name;
    }

    public Distribution getDistribution() {
        return distribution;
    }

    /**
     * Generates a sample of given size of variable's distribution type.
     *
     * @param size size of the sample.
     * @return sample of given size.
     * */
    public ArrayList generateSample(int size) {
        return distribution.generateSample(size);
    }

    @Override
    public String toString() {
        return "Variable " + name + ": " + distribution;
    }
}