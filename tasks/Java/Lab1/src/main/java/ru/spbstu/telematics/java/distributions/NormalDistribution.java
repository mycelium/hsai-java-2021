package ru.spbstu.telematics.java.distributions;

import java.text.DecimalFormat;

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
     * @return random integer value
     */
    @Override
    public Double generateValue() {
        double normalized = random.nextGaussian();
        return deviation * normalized + expectation;
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
        return "Normal Distribution (" + dFormat.format(expectation) + ", " + dFormat.format(deviation) + ")";
    }
}
