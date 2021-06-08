package hw1.distribution;

import java.util.ArrayList;
import java.util.Random;

public class PoissonDistribution implements Distribution  {
    private Random generator;
    private double lambda;

    public PoissonDistribution(double lambda) {
        this.lambda = lambda;
        this.generator = new Random(System.currentTimeMillis());
    }

    @Override
    public double random() {
        double p = Math.exp(-lambda);
        double r = generator.nextDouble();
        double x = 0;
        while (r > 0) {
            x++;
            p *= lambda / x;
            r -= p;
        }
        return x;
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
