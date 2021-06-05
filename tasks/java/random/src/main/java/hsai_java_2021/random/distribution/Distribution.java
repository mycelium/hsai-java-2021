package hsai_java_2021.random.distribution;

import java.util.ArrayList;

public interface Distribution {
    public enum DistributionType {
        UNIFORM,
        NORMAL,
        POISSON
    }

    DistributionType getType();

    double random();

    ArrayList<Double> randomSample(int size);
}
