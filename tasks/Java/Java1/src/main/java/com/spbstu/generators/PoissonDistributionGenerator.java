package com.spbstu.generators;

import java.util.ArrayList;
import java.util.Random;

public class PoissonDistributionGenerator implements Generator{
    private Double mu;
    Random random = new Random();

    public PoissonDistributionGenerator(Double mu) {
        this.mu = mu;
    }

    @Override
    public ArrayList<Double> generate(int n) {
        ArrayList<Double> distribution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double x = 0;
            double p = Math.exp(-mu);
            double r = random.nextDouble() - p;
            while (r > 0) {
                x ++;
                p *= mu / x;
                r -= p;
            }
            distribution.add(x);
        }
        return distribution;
    }
}
