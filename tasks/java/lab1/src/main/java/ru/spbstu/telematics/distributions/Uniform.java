package ru.spbstu.telematics.distributions;

public class Uniform extends Distribution<Double> {

    private Double leftBound;
    private Double rightBound;

    public Uniform(Double leftBound, Double rightBound) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    @Override
    public Double getRandom() {
        return leftBound + random.nextDouble() * (rightBound - leftBound);
    }
}
