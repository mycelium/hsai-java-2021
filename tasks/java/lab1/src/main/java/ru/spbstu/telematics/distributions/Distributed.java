package ru.spbstu.telematics.distributions;

import java.util.ArrayList;
import java.util.List;

public interface Distributed<T> {

    T getRandom();

    String getName();

    default List<T> getRandoms(int size) {
        List<T> sample = new ArrayList<>(size);
        for(int i = 0; i < size; i++) {
            sample.add(getRandom());
        }
        return sample;
    }

    static Distributed<Double> getNormal(String name, Double variance, Double mean) {
        return new Normal(name, variance, mean);
    }

    static Distributed<Integer> getPoisson(String name, Integer mean) {
        return new Poisson(name, mean);
    }

    static Distributed<Double> getUniform(String name, Double leftBound, Double rightBound) {
        return new Uniform(name, leftBound, rightBound);
    }

}
