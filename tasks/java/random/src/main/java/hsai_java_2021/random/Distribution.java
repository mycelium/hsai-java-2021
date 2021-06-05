package hsai_java_2021.random;

import java.util.ArrayList;

public interface Distribution {
    public enum DistributionType {
        CONTINUOUS,
        DISCRETE
    }

    double random();

    ArrayList<Double> randomSample(int size);
}
