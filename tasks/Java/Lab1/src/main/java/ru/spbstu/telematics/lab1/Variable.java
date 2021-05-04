package ru.spbstu.telematics.lab1;

import ru.spbstu.telematics.lab1.random.*;

/**
 * Column of random data of given distribution.
 */
public class Variable {
    /**
     * Distribution for generating random data.
     */
    private final Distribution distribution;
    /**
     * Name of column.
     */
    private final String name;

    public Variable(Distribution distribution, String name) {
        this.distribution = distribution;
        this.name = name;
    }

    @Override
    public String toString() {
        return "(" + getName() + " - " + getDistribution() + ")";
    }

    /**
     * Builder for variable with normal distribution.
     * @param name name of column.
     * @param mean placement parameter.
     * @param std scale parameter.
     * @return variable object with normal distribution.
     */
    public static Variable buildNormal(String name, float mean, float std) {
        return new Variable(new Normal(mean, std), name);
    }

    /**
     * Builder for variable with poisson distribution.
     * @param name name of column.
     * @param expectation expectation of distribution.
     * @return variable object with poisson distribution.
     */
    public static Variable buildPoisson(String name, float expectation) {
        return new Variable(new Poisson(expectation), name);
    }

    /**
     * Builder for variable with uniform distribution.
     * @param name name of column.
     * @return variable object with uniform distribution.
     */
    public static Variable buildUniform(String name, float lowerBound, float upperBound) {
        return new Variable(new Uniform(lowerBound, upperBound), name);
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public String getName() {
        return name;
    }
}
