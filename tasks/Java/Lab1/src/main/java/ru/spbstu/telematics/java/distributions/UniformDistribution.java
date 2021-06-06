package ru.spbstu.telematics.java.distributions;

import java.text.DecimalFormat;

public class UniformDistribution implements Distribution<Double>{
    private final double lowerBound;
    private final double upperBound;


    /**
     * @throws IllegalArgumentException if lower bound is not less than upper bound
     * */
    public UniformDistribution(double lowerBound, double upperBound) {
        if(lowerBound >= upperBound)
            throw new IllegalArgumentException("Invalid range: (" + lowerBound + "; " + upperBound + ")");

        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        random.setSeed(System.currentTimeMillis());
    }

    /**
     * Randomly generates one value of Double type.
     *
     * @return random integer value
     */
    @Override
    public Double generateValue() {
        return lowerBound + ((upperBound - lowerBound) * random.nextDouble());
    }

    /**
     * @return true if distribution is discrete, false otherwise
     */
    @Override
    public boolean isDiscrete() {
        return false;
    }

    /**
     * @return String containing distribution type and its parameters
     * */
    @Override
    public String toString() {
        DecimalFormat dFormat = new DecimalFormat("0.000E00");
        return "Uniform Distribution (" + dFormat.format(lowerBound) + ", " + dFormat.format(upperBound) + ")";
    }
}
