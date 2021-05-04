package ru.spbstu.telematics.lab1.random;

import java.util.ArrayList;

/**
 * Discrete Poisson distribution with given expectation.
 */
public class Poisson implements Distribution {
    /**
     * Expectation of distribution.
     */
    private final float mu;
    private final PseudoRandom random = new PseudoRandom();

    /**
     * @param mu expectation of distribution.
     * @throws IllegalArgumentException if mu is negative.
     */
    public Poisson(float mu) {
        this.mu = mu;
        if (mu < 0) throw new IllegalArgumentException("Mu cannot be negative");
    }

    /**
     * @param mu expectation of distribution.
     * @param seed seed for random number generator.
     * @throws IllegalArgumentException if mu is negative.
     */
    public Poisson(float mu, int seed) {
        this.mu = mu;
        random.setSeed(seed);
        if (mu < 0) throw new IllegalArgumentException("Mu cannot be negative");
    }

    @Override
    public float generateFloat() {
        return generateInt();
    }

    @Override
    public int generateInt() {
        int x = 0;
        float p = (float) Math.exp(-mu);
        float r = random.nextRand() - p;
        while (r > 0) {
            x ++;
            p *= mu / x;
            r -= p;
        }
        return x;
    }

    @Override
    public ArrayList<Float> selectionFloat(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be negative");
        }
        ArrayList<Float> res = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            res.add(generateFloat());
        }
        return res;
    }

    @Override
    public ArrayList<Integer> selectionInt(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be negative");
        }
        ArrayList<Integer> res = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            res.add(generateInt());
        }
        return res;
    }

    @Override
    public boolean isDiscrete() {
        return true;
    }

    @Override
    public String toString() {
        return "Poisson(" + mu + ")";
    }
}
