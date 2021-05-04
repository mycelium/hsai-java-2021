package ru.spbstu.telematics.lab1.random;

import org.junit.Test;

import static org.junit.Assert.*;
import static ru.spbstu.telematics.lab1.random.DistributionTestUtil.MVComparator;

public class UniformTest {
    float[][] boundsArray = {{0, 1}, {2, 4}, {1, 9}, {0.000001F, 0.000002F}, {-10000, 10000}};

    @Test
    public void isNotDiscrete() {
        Uniform dst = new Uniform(0, 1);
        assertFalse(dst.isDiscrete());
    }

    @Test
    public void testBounds() {
        final int N = 10000;
        for (float[] bounds: boundsArray) {
            Uniform dst = new Uniform(bounds[0], bounds[1]);
            float val = dst.generateFloat();
            assertTrue(bounds[0] <= val && val <= bounds[1]);
        }
    }

    @Test
    public void testSelection() {
        MVComparator comparator = new MVComparator(1000, 100);
        for (float[] bounds: boundsArray) {
            Uniform dst = new Uniform(bounds[0], bounds[1]);
            float[] expected = {(bounds[0] + bounds[1]) / 2,
                    (bounds[1]-bounds[0])*(bounds[1]-bounds[0])/12};
            assertTrue(comparator.compare(dst, expected));
        }
    }
}
