package ru.spbstu.telematics.distribution;

import java.util.ArrayList;
import java.util.Random;

public class UniformDistribution implements Distribution{
    Random random = new Random(System.currentTimeMillis());
    long size;
    double min, max;

    public UniformDistribution(long size, double min, double max) {
        this.size = size;
        this.min = min;
        this.max = max;
    }

    @Override
    public ArrayList<Double> generate() {
        ArrayList<Double> arrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arrayList.add(min + random.nextDouble() * (max - min + 1));
        }
        return arrayList;
    }
}
