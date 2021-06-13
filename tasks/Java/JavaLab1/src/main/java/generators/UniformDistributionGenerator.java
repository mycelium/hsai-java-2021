package generators;

import java.util.ArrayList;
import java.util.Random;

public class UniformDistributionGenerator implements Generator {
    private Double a;
    private Double b;
    Random random = new Random();

    public UniformDistributionGenerator(Double a, Double b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public ArrayList<Double> generate(int n) {
        ArrayList<Double> distribution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            distribution.add(a + (b - a)*random.nextDouble());
        }
        return distribution;
    }
}