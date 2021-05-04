package ru.spbstu.telematics.lab1.random;

import static java.lang.Math.abs;

/**
 * Linear congruential generator of Integer values. Uses modulus 2^31. <br>
 * r(i+1) = (a * r(i) + c) % m.
 */
public class PseudoRandom {
    private Integer a = 1103515245;
    private Integer c = 12345;

    private Integer next = 1;

    public PseudoRandom() {
        randSeed();
    }

    public PseudoRandom(int seed) {
        setSeed(seed);
    }

    public PseudoRandom(int A, int C, int seed) {
        setParameters(A, C);
        setSeed(seed);
    }

    public void setSeed(int seed) {
        next = seed;
    }

    public void randSeed() {
        next = (int) System.currentTimeMillis();
    }

    public void setParameters(int A, int C) {
        if (A > 0) a = A;
        if (C > 0) c = C;
    }

    public int nextInt() {
        next = (a*next + c) & Integer.MAX_VALUE;
        return next;
    }

    /**
     * Returns random value in [0; to), if 'to' < 1, then 0 is returned. <br>
     * Fast implementation, where distribution is not really uniform,
     * it can be noticed for big values of 'to'.
     * @param to upper bound, is excluded.
     * @return random value between [0; to).
     */
    public int randrange(int to) {
        if (to < 1) return 0;
        return nextInt() % to;
    }

    /**
     * Returns random value in [from; to), if from >= to or to - from > Integer.MAX_VALUE,
     * then 0 is returned. <br>
     * Fast implementation, where distribution is not really uniform,
     * it can be noticed for big difference between 'from' and 'to'.
     * @param from lower bound, is included.
     * @param to upper bound, is excluded.
     * @return random value between [0; to).
     */
    public int randrange(int from, int to) {
        return from + randrange(to - from);
    }

    public boolean nextBoolean() {
        return nextInt() % 2 == 0;
    }

    /**
     * @return uniformly distributed float value in [0, 1).
     */
    public float nextRand() {
        return (float) nextInt() / Integer.MAX_VALUE;
    }
}
