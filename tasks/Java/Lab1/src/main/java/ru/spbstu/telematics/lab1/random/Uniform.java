package ru.spbstu.telematics.lab1.random;

import java.util.ArrayList;

/**
 * Uniform distribution with given lower and upper bounds.
 */
public class Uniform implements Distribution{
    private final float lowerBound;
    private final float upperBound;
    private final PseudoRandom random = new PseudoRandom();

    public Uniform(float lowerBound, float upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        if (lowerBound >= upperBound)
            throw new IllegalArgumentException("Invalid range: (" + lowerBound + "; " + upperBound + ")");
    }

    public Uniform(float lowerBound, float upperBound, int seed) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        if (lowerBound >= upperBound)
            throw new IllegalArgumentException("Invalid range: (" + lowerBound + "; " + upperBound + ")");
        random.setSeed(seed);
    }

    @Override
    public float generateFloat() {
        return lowerBound + (upperBound - lowerBound) * random.nextRand();
    }

    @Override
    public int generateInt() {
        return 0;
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
        return null;
    }

    @Override
    public boolean isDiscrete() {
        return false;
    }

    @Override
    public String toString() {
        return "Uniform(" + lowerBound + ", " + upperBound + ")";
    }
}
