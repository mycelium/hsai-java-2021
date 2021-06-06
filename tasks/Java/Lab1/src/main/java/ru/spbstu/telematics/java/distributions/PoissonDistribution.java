package ru.spbstu.telematics.java.distributions;

import java.util.ArrayList;

public class PoissonDistribution implements Distribution<Integer>{
    private final double expectation;

    public PoissonDistribution(double expectation) {
        if(expectation <= 0)
            throw new IllegalArgumentException("Expectation should be a positive number");

        this.expectation = expectation;
        random.setSeed(System.currentTimeMillis());
    }

    /**
     * Randomly generates one value of Integer type.
     *
     * @return random integer value.
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
     * Generates sample of given size.
     *
     * @param size size of the sample.
     * @return sample of values of Integer type.
     */
    @Override
    public ArrayList<Integer> generateSample(int size) {
        if(size < 0)
            throw new IllegalArgumentException("Size of the sample cannot be a negative number");

        ArrayList<Integer> sample = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            sample.add(generateValue());
        }
        return sample;
    }

    /**
     * @return true if distribution is discrete, false otherwise.
     */
    @Override
    public boolean isDiscrete() {
        return true;
    }

    /**
     * @return String containing distribution type and its parameters.
     * */
    @Override
    public String toString() {
        return "Poisson Distribution (" + expectation + ")";
    }
}
