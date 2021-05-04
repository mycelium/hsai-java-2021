package ru.spbstu.telematics.lab1.random;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PseudoRandomTest {
    @Test
    public void justTest() {
        final int N = 10;
        PseudoRandom random = new PseudoRandom(10);
        for (int i = 0; i < N; i++) {
            System.out.println(random.nextInt());
        }
    }

    @Test
    public void randrangeTest() {
        int[][] ranges = {{1, 100}, {10, 20}, {30, 1000}, {5, 30}};
        PseudoRandom random = new PseudoRandom();
        for (int[] range : ranges) {
            int N = 10 * (range[1] - range[0]);
            for (int i = 0; i < N; i++) {
                int res = random.randrange(range[0], range[1]);
                assertTrue(range[0] <= res && res < range[1]);
            }
        }
    }

    @Test
    public void nextRandTest() {
        final int N = 10;
        PseudoRandom random = new PseudoRandom(20);
        for (int i = 0; i < N; i++) {
            System.out.println(random.nextRand());
        }
    }
}
