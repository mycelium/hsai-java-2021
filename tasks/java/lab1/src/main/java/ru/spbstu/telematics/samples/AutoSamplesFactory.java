package ru.spbstu.telematics.samples;

import ru.spbstu.telematics.distributions.Normal;
import ru.spbstu.telematics.distributions.Poisson;
import ru.spbstu.telematics.distributions.Uniform;

public class AutoSamplesFactory {

    static AutoSample<Double> getNormal(String name, Double variance, Double mean) {
        return new AutoSample<>(name, new Normal(variance, mean));
    }

    static AutoSample<Integer> getPoisson(String name, Integer mean) {
        return new AutoSample<>(name, new Poisson(mean));
    }

    static AutoSample<Double> getUniform(String name, Double leftBound, Double rightBound) {
        return new AutoSample<>(name, new Uniform(leftBound, rightBound));
    }

}
