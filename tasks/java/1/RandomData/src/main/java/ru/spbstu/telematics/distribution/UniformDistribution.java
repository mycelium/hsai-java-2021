package ru.spbstu.telematics.distribution;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UniformDistribution implements Distribution{
    Random random = new Random(System.currentTimeMillis());
    long size;
    double min, max;

    public UniformDistribution(long size, double min, double max) {
        this.size = size;
        this.min = min;
        this.max = max;
    }

    /**
     * Generate a number that obeys uniform distribution
     * @return the number
     */
    @Override
    public Double generateOne() {
        return min + random.nextDouble() * (max - min + 1);
    }

    /**
     * Generate an array that obeys uniform distribution
     * @return the array
     */
    @Override
    public ArrayList<Double> generate() {
        ArrayList<Double> arrayList;
        arrayList = Stream.generate(this::generateOne)
                .limit(size)
                .collect(Collectors.toCollection(ArrayList::new));
        return arrayList;
    }
}
