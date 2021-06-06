package ru.spbstu.telematics.java.distributions;

public class PoissonDistribution implements Distribution<Integer>{
    private final double expectation;

    /**
     * @throws IllegalArgumentException if expectation is not a positive number
     * */
    public PoissonDistribution(double expectation) {
        if(expectation <= 0)
            throw new IllegalArgumentException("Expectation should be a positive number");

        this.expectation = expectation;
        random.setSeed(System.currentTimeMillis());
    }

    /**
     * Generates one random value of Integer type.
     *
     * @return random integer value
     */
    @Override
    public Integer generateValue() {
        int x = 0;
        double p = Math.exp(-expectation);
        double r = random.nextDouble() - p;
        while (r > 0) {
            x ++;
            p *= expectation / x;
            r -= p;
        }
        return x;
    }

    /**
     * @return true if distribution is discrete, false otherwise
     */
    @Override
    public boolean isDiscrete() {
        return true;
    }

    /**
     * @return String containing distribution type and its parameters
     * */
    @Override
    public String toString() {
        return "Poisson Distribution (" + expectation + ")";
    }
}
