package ru.spbstu.telematics.lab1.random;

import org.junit.Test;

import static org.junit.Assert.*;
import static ru.spbstu.telematics.lab1.random.DistributionTestUtil.MVComparator;

public class NormalTest {

    @Test
    public void isNotDiscrete() {
        Normal distribution = new Normal();
        assertFalse(distribution.isDiscrete());
    }

    @Test
    public void testSelection() {
        float[][] params = {{0, 1}, {3, 2}, {4, 10}, {100, 25}, {-50, 4}};
        final MVComparator comparator = new MVComparator(1000, 100);
        for (float[] parameter: params) {
            Normal normal = new Normal(parameter[0], parameter[1], 10);
            float[] t = {parameter[0], parameter[1] * parameter[1]};
            assertTrue(comparator.compare(normal, t));
        }
    }
}
