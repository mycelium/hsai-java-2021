package ru.spbstu.telematics.java.data;

import ru.spbstu.telematics.java.distributions.NormalDistribution;
import ru.spbstu.telematics.java.distributions.PoissonDistribution;
import ru.spbstu.telematics.java.distributions.UniformDistribution;

/**
 * This class is used to make variables.
 * You should avoid making variables without using factory's "make" methods.
 * */
public class VariableFactory {
    /**
     * Makes a new variable of uniform distribution with given lower and upper bound.
     *
     * @param name name of the variable.
     * @param lowerBound lower bound of uniform distribution range.
     * @param upperBound upper bound of uniform distribution range.
     * @return variable of uniform distribution with given parameters.
     * */
    public Variable makeUniformDistributionVariable(String name, double lowerBound, double upperBound) {
        return new Variable(name, new UniformDistribution(lowerBound, upperBound));
    }

    /**
     * Makes a new variable of normal distribution with given expectation and deviation.
     *
     * @param name name of the variable.
     * @param expectation expectation of normal distribution.
     * @param deviation deviation of normal distribution.
     * @return variable of normal distribution with given parameters.
     * */
    public Variable makeNormalDistributionVariable(String name, double expectation, double deviation) {
        return new Variable(name, new NormalDistribution(expectation, deviation));
    }

    /**
     * Makes a new variable of standard normal distribution N(0,1).
     *
     * @param name name of the variable.
     * @return variable of standard normal distribution.
     * */
    public Variable makeNormalDistributionVariable(String name) {
        return new Variable(name, new NormalDistribution());
    }

    /**
     * Makes a new variable of Poisson distribution with given expectation.
     *
     * @param name name of the variable.
     * @param expectation expectation of Poisson distribution.
     * @return variable of Poisson distribution with given distribution.
     * */
    public Variable makePoissonDistributionVariable(String name, double expectation) {
        return new Variable(name, new PoissonDistribution(expectation));
    }
}
