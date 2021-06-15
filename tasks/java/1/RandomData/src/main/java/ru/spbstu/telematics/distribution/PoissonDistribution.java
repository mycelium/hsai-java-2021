package ru.spbstu.telematics.distribution;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PoissonDistribution implements Distribution {
    static Random random = new Random(System.currentTimeMillis());
    long size;
    double lambda;

    public PoissonDistribution(long size, double lambda) {
        this.lambda = lambda;
        this.size = size;
    }

    /**
     * Generate a number that obeys poisson distribution
     * @return the number
     */
    @Override
    public Double generateOne() {
        double L = Math.exp(-lambda);
        int k = 0;
        double p = 1.0;
        do {
            p = p * random.nextDouble();
            k++;
        } while (p > L);
        return (double) (k - 1);
    }

    /**
     * Generate an array that obeys poisson distribution
     * @return the array
     */
    @Override
    public ArrayList<Double> generate() {
        ArrayList<Double> array;
        array = Stream.generate(this::generateOne)
                .limit(size)
                .collect(Collectors.toCollection(ArrayList::new));
        return array;
    }
}
