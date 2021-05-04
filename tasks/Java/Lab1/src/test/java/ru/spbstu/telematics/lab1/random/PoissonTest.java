package ru.spbstu.telematics.lab1.random;

import org.junit.Test;
import static org.junit.Assert.*;
import static ru.spbstu.telematics.lab1.random.DistributionTestUtil.MVComparator;

public class PoissonTest {
    @Test
    public void isDiscrete() {
        Poisson dst = new Poisson(3);
        assertTrue(dst.isDiscrete());
    }

    @Test
    public void testSelection() {
        float[] mus = {1, 2.4F, 4, 10, 4.3F};
        MVComparator comparator = new MVComparator(1000, 100);
        for (float mu: mus) {
            Poisson dst = new Poisson(mu);
            assertTrue(comparator.compare(dst, new float[]{mu, mu}));
        }
    }
}
