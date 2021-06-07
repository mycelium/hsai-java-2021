package ru.spbstu.telematics.java.hsai_java_lab.value;

import ru.spbstu.telematics.java.hsai_java_lab.distribution.Distribution;
import ru.spbstu.telematics.java.hsai_java_lab.distribution.Distribution.DistributionType;
import ru.spbstu.telematics.java.hsai_java_lab.distribution.NormalDistribution;
import ru.spbstu.telematics.java.hsai_java_lab.distribution.PoissonDistribution;
import ru.spbstu.telematics.java.hsai_java_lab.distribution.UniformDistribution;

public class RandomValue {
    private String name;
    private DistributionType type;
    private Distribution distribution;

    /**
     * Creates the instance of the random value
     * with the continuous uniform distribution
     * 
     * @param name Name of the random value
     * @param a Start of the distribution interval
     * @param b End of thr distribution interval
     * @return Instance of the random value
     */
    static public RandomValue uniformDistribution(String name, double a, double b) {
        return new RandomValue(name, DistributionType.UNIFORM, new UniformDistribution(a, b));
    }

    /**
     * Creates the instance of the random value
     * with the normal distribution
     * 
     * @param name Name of the random value
     * @param mu Expected value
     * @param sigma Dispersion
     * @return Instance of the random value
     */
    static public RandomValue normalDistribution(String name, double mu, double sigma) {
        return new RandomValue(name, DistributionType.NORMAL, new NormalDistribution(mu, sigma));
    }

    /**
     * Creates the instance of the random value
     * with the discrete Poisson distribution
     * 
     * @param name Name of the random value
     * @param lambda Expected value
     * @return
     */
    static public RandomValue poissonDistribution(String name, int lambda) {
        int checkedLambda = (lambda < 0) ? 0 : lambda;
        return new RandomValue(name, DistributionType.POISSON, new PoissonDistribution(checkedLambda));
    }

    /**
     * Returns the name of the random value
     * 
     * @return Name of the random value
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of the random value
     * 
     * @return Type of the random value
     */
    public DistributionType getType() {
        return type;
    }

    /**
     * Generates the random value with the given distribution
     * 
     * @return Random value with the given distribution
     */
    public double generate() {
        return distribution.random();
    }

    /**
     * Private constructor
     * 
     * @param name Name of the random value
     * @param type Type of the random value
     * @param distribution Distribution of the random value @see Distribution
     */
    private RandomValue(String name, DistributionType type, Distribution distribution) {
        this.name = name;
        this.type = type;
        this.distribution = distribution;
    }
}
