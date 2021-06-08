package hw1.distribution;

import java.util.ArrayList;

public interface Distribution {
    double random();
    ArrayList<Double> generate(int size);
}