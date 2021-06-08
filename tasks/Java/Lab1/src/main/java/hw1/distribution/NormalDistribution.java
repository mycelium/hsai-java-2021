package hw1.distribution;

import java.util.ArrayList;
import java.util.Random;

public class NormalDistribution implements Distribution {
    private Random generator;
    private double mu, sigma;

    public NormalDistribution(double mu, double sigma) {
        this.mu = mu;
        this.sigma = sigma;
        this.generator = new Random(System.currentTimeMillis());
    }

    @Override
    public double random() {
        return Math.sqrt(sigma) * generator.nextGaussian() + mu;
    }

    @Override
    public ArrayList<Double> generate(int size) {
        ArrayList<Double> arrayList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(random());
        }
        return arrayList;
    }
}
