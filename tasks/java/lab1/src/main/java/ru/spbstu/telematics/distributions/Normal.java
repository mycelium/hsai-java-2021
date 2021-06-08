package ru.spbstu.telematics.distributions;

public class Normal extends Distribution<Double> {

    private Double variance;
    private Double mean;

    public Normal(Double variance, Double mean) {
        checkNotInfinityOrNan(variance);
        checkNotInfinityOrNan(mean);
        if (variance <= 0) {
            throw new IllegalArgumentException("Variance must be > 0, but variance = " + variance);
        }
        this.variance = variance;
        this.mean = mean;
    }

    @Override
    public Double getRandom() {
        return random.nextGaussian() * variance + mean;
    }

}