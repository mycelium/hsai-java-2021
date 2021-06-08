package ru.spbstu.telematics.distributions;

public class Uniform extends Distribution<Double> {

    private Double leftBound;
    private Double rightBound;

    public Uniform(Double leftBound, Double rightBound) {
        checkNotInfinityOrNan(leftBound);
        checkNotInfinityOrNan(rightBound);
        if (leftBound > rightBound) {
            throw new IllegalArgumentException("Left bound is bigger than right bound: " + leftBound + " > " + rightBound);
        }
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    @Override
    public Double getRandom() {
        return leftBound + random.nextDouble() * (rightBound - leftBound);
    }
}
