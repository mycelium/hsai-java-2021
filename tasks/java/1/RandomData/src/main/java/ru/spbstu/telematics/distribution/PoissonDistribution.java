package ru.spbstu.telematics.distribution;

import java.util.ArrayList;
import java.util.Random;

public class PoissonDistribution implements Distribution {
    static Random random = new Random(System.currentTimeMillis());
    long size;
    double lambda;

    public PoissonDistribution(long size, double lambda) {
        this.lambda = lambda;
        this.size = size;
    }

    private int getPoissonRandom() {
        double L = Math.exp(-lambda);
        int k = 0;
        double p = 1.0;
        do {
            p = p * random.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }

    @Override
    public ArrayList<Double> generate() {
        ArrayList<Double> array = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            array.add((double)getPoissonRandom());
        }
        return array;
    }
}
