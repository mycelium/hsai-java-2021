package ru.spbstu.telematics.java.hsai_java_lab.distribution;

import java.util.ArrayList;
import java.util.Random;

public class UniformDistribution implements Distribution {
    private double a;
    private double delta;
    private Random generator;

    public UniformDistribution(double a, double b) {
        this.a = Math.min(a, b);
        delta = b - a;
        generator = new Random(System.currentTimeMillis());
    }

    @Override
    public double random() {
        return a + generator.nextDouble() * delta;
    }

    @Override
    public ArrayList<Double> randomSample(int size) {
        ArrayList<Double> sample = new ArrayList<Double>(size);

        for (int i = 0; i < size; i++) {
            sample.add(random());
        }

        return sample;
    }

    @Override
    public DistributionType getType() {
        return DistributionType.UNIFORM;
    }   
}
