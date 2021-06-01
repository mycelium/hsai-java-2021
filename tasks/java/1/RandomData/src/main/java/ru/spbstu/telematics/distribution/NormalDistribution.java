package ru.spbstu.telematics.distribution;

import java.util.ArrayList;
import java.util.Random;

public class NormalDistribution implements Distribution {
    Random random = new Random(System.currentTimeMillis());
    long size;
    double mu, sigmaSquared;

    public NormalDistribution(long size, double mu, double sigmaSquared) {
        this.size = size;
        this.mu = mu;
        this.sigmaSquared = sigmaSquared;
    }

    @Override
    public ArrayList<Double> generate() {
        ArrayList<Double> arrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arrayList.add(Math.sqrt(sigmaSquared) * random.nextGaussian() + mu);
        }
        return arrayList;
    }
}
