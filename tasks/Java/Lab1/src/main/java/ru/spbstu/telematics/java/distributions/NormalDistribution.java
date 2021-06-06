package ru.spbstu.telematics.java.distributions;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class NormalDistribution implements Distribution<Double>{
    private final double expectation;
    private final double deviation;

    /**
     * Default constructor. Sets the expectation equals to 0 and deviation equals to 1.
     */
    public NormalDistribution() {
        this.expectation = 0;
        this.deviation = 1;
        random.setSeed(System.currentTimeMillis());
    }

    public NormalDistribution(double expectation, double deviation) {
        this.expectation = expectation;
        this.deviation = deviation;
        random.setSeed(System.currentTimeMillis());
    }

    /**
     * Randomly generates one value of Double type.
     *
     * @return random integer value.
     */
    @Override
    public Double generateValue() {
        double normalized = random.nextGaussian();
        return deviation * normalized + expectation;
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
        return "Normal Distribution (" + dFormat.format(expectation) + ", " + dFormat.format(deviation) + ")";
    }
}