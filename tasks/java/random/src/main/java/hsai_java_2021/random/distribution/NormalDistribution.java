package hsai_java_2021.random.distribution;

import java.util.ArrayList;
import java.util.Random;

public class NormalDistribution implements Distribution {
    private double mu;
    private double sigma;
    private Random generator;

    public NormalDistribution(double mu, double sigma) {
        this.mu = mu;
        this.sigma = sigma;
        generator = new Random(System.currentTimeMillis());
    }

    @Override
    public double random() {
        return mu + generator.nextGaussian() * sigma;
    }

    @Override
    public ArrayList<Double> randomSample(int size) {
        ArrayList<Double> sample = new ArrayList<Double>(size);

        for (int i = 0; i < size; i++) {
            sample.add(random());
        }

        return sample;
    }
    
    @Override
    public DistributionType getType() {
        return DistributionType.NORMAL;
    }   
}
