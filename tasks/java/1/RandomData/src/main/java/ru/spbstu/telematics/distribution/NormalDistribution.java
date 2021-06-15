package ru.spbstu.telematics.distribution;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NormalDistribution implements Distribution {
    Random random = new Random(System.currentTimeMillis());
    long size;
    double mu, sigmaSquared;

    public NormalDistribution(long size, double mu, double sigmaSquared) {
        this.size = size;
        this.mu = mu;
        this.sigmaSquared = sigmaSquared;
    }

    /**
     * Generate a number that obeys normal distribution
     * @return the number
     */
    @Override
    public Double generateOne() {
        return Math.sqrt(sigmaSquared) * random.nextGaussian() + mu;
    }

    /**
     * Generate an array that obeys normal distribution
     * @return the array
     */
    @Override
    public ArrayList<Double> generate() {
        ArrayList<Double> arrayList;
        arrayList = Stream.generate(this::generateOne)
                .limit(size)
                .collect(Collectors.toCollection(ArrayList::new));
        return arrayList;
    }
}
