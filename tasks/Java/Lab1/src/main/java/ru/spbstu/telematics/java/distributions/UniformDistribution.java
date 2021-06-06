package ru.spbstu.telematics.java.distributions;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class UniformDistribution implements Distribution<Double>{
    private final double lowerBound;
    private final double upperBound;


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
     * @return random integer value.
     */
    @Override
    public Double generateValue() {
        return lowerBound + ((upperBound - lowerBound) * random.nextDouble());
    }

    /**
     * Generates sample of given size.
     *
     * @param size size of the sample.
     * @return sample of values of Double type.
     */
    @Override
    public ArrayList<Double> generateSample(int size) {
        if(size < 0)
            throw new IllegalArgumentException("Size of the sample cannot be a negative number");

        ArrayList<Double> sample = new ArrayList<>(size);
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
        return false;
    }

    /**
     * @return String containing distribution type and its parameters.
     * */
    @Override
    public String toString() {
        DecimalFormat dFormat = new DecimalFormat("0.000E00");
        return "Uniform Distribution (" + dFormat.format(lowerBound) + ", " + dFormat.format(upperBound) + ")";
    }
}
