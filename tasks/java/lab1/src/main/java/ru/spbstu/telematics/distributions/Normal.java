package ru.spbstu.telematics.distributions;

public class Normal extends Distribution<Double> {

    private Double variance;
    private Double mean;

    public Normal(Double variance, Double mean) {
        this("Normal", variance, mean);
    }

    public Normal(String name, Double variance, Double mean) {
        super(name);
        this.variance = variance;
        this.mean = mean;
    }

    @Override
    public Double getRandom() {
        return random.nextGaussian() * variance + mean;
    }

}
