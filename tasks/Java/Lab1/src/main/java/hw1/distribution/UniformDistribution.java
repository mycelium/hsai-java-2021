package hw1.distribution;

import java.util.ArrayList;
import java.util.Random;

public class UniformDistribution implements Distribution {
    private Random generator;
    private double min, max, delt;

    public UniformDistribution(double min, double max) {
        this.min = min;
        this.max = max;
        this.delt = max - min;
        this.generator = new Random(System.currentTimeMillis());
    }

    @Override
    public double random() {
        return min + generator.nextDouble() * delt;
    }

    @Override
    public ArrayList<Double> generate(int size) {
        ArrayList<Double> array = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            array.add(random());
        }
        return array;
    }
}

