package ru.spbstu.telematics.java.hsai_java_lab.distribution;

import java.util.ArrayList;
import java.util.Random;

public class PoissonDistribution implements Distribution {
    private int lambda;
    private Random generator;

    public PoissonDistribution(int lambda) {
        this.lambda = lambda;
        generator = new Random(System.currentTimeMillis());
    }

    @Override
    public double random() {
        double x = 0.;
        double p = Math.exp(-lambda);
        double s = p;
        double r = generator.nextDouble();
        
        while (r > s) {
            x += 1;
            p *= lambda / x;
            s += p;
        }

        return x;
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
        return DistributionType.POISSON;
    }   
}
