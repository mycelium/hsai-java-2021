package com.spbstu.generators;

import java.util.ArrayList;
import java.util.Random;

public class NormalDistributionGenerator implements Generator {
    private Double m;
    private Double l;
    Random random = new Random();

    public NormalDistributionGenerator(Double a, Double b) {
        this.m = a;
        this.l = b;
    }

    @Override
    public ArrayList<Double> generate(int n) {
        ArrayList<Double> distribution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Double x = -6.0;
            for (int j = 0; j < 12; j++) {
                x += random.nextDouble();
            }
            distribution.add(l*x+m);
        }
        return distribution;
    }
}
