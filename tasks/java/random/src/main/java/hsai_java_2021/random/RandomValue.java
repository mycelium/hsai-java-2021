package hsai_java_2021.random;

import java.util.ArrayList;

import hsai_java_2021.random.distribution.Distribution;
import hsai_java_2021.random.distribution.Distribution.DistributionType;
import hsai_java_2021.random.distribution.NormalDistribution;
import hsai_java_2021.random.distribution.PoissonDistribution;
import hsai_java_2021.random.distribution.UniformDistribution;

public class RandomValue {
    private String name;
    private DistributionType type;
    private Distribution distribution;
    private ArrayList<Double> sample;

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
     * Generates the sequence of random values with the given distribution
     * 
     * @param size Size of the sequence
     * @return Sequence of random values
     */
    public ArrayList<Double> generateSample(int size) {
        sample = distribution.randomSample(size);
        return sample;
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
        this.sample = new ArrayList<Double>();
    }
}
