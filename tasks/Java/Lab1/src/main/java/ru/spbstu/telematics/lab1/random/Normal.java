package ru.spbstu.telematics.lab1.random;

import java.util.ArrayList;

/**
 * Normal distribution with given placement and scale parameters.
 * Normal distribution is continuous.
 */
public class Normal implements Distribution {
    /**
     * Placement parameter.
     */
    private final float m;
    /**
     * Scale parameter, size of one 'sigma'.
     */
    private final float l;
    /**
     * Random number generator.
     */
    private final PseudoRandom random = new PseudoRandom();

    public Normal() {
        this.m = 0;
        this.l = 1;
    }

    public Normal(float m, float l) {
        this.m = m;
        this.l = l;
    }

    public Normal(float m, float l, int seed) {
        this.m = m;
        this.l = l;
        random.setSeed(seed);
    }

    /**
     * @return random value for N(0, 1).
     */
    private float normalized() {
        float sum = -6;
        for (int i = 0; i < 12; i++) {
            sum += random.nextRand();
        }
        return sum;
    }

    @Override
    public float generateFloat() {
        return l * normalized() + m;
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
        return "Normal(" + m + ", " + l + ")";
    }
}
