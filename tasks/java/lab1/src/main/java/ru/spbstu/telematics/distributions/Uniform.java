package ru.spbstu.telematics.distributions;

public class Uniform extends Distribution<Double> {

    private Double leftBound;
    private Double rightBound;

    public Uniform(Double leftBound, Double rightBound) {
        this("Uniform", leftBound, rightBound);
    }

    public Uniform(String name, Double leftBound, Double rightBound) {
        super(name);
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    @Override
    public Double getRandom() {
        return leftBound + random.nextDouble() * (rightBound - leftBound);
    }
}
