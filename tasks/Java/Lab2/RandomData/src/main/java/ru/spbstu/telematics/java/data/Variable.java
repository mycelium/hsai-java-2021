package ru.spbstu.telematics.java.data;

import ru.spbstu.telematics.java.distributions.Distribution;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class is used to store the certain distribution with certain parameters.
 * You should avoid making variables without using {@link VariableFactory}'s "make" methods.
 * */
public class Variable {
    private final String name;
    private final Distribution<?> distribution;

    /**
     * @param name the name of the variable
     * @param distribution implementation of Distribution interface of certain type
     * @throws NullPointerException if the distribution is NULL
     * */
    public Variable(String name, Distribution<?> distribution) throws NullPointerException {
        this.name = name;
        this.distribution = Objects.requireNonNull(distribution);
    }

    public String getName() {
        return name;
    }

    public Distribution<?> getDistribution() {
        return distribution;
    }

    /**
     * Generates a random value of variable's distribution type
     * @return random value of Double or Integer type (type of the value depends on the distribution's type)
     * */
    public Object generateValue() {
        return distribution.generateValue();
    }

    /**
     * Generates a sample of given size of variable's distribution type
     *
     * @param size size of the sample
     * @return sample of given size
     * */
    public ArrayList<?> generateSample(int size) {
        return distribution.generateSample(size);
    }

    @Override
    public String toString() {
        return "Variable " + name + ": " + distribution;
    }
}