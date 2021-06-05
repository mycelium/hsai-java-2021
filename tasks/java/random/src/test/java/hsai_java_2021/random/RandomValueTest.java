package hsai_java_2021.random;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import hsai_java_2021.random.distribution.Distribution.DistributionType;

public class RandomValueTest {
    final int sampleSize = 10;

    @Test
    public void uniformDistribTest() {
        RandomValue X = new RandomValue("Uniform", DistributionType.UNIFORM);
        ArrayList<Double> sample = X.generateSample(sampleSize);

        assertEquals("Uniform", X.getName());
        assertEquals(DistributionType.UNIFORM, X.getType());
        assertEquals(sampleSize, sample.size());

        for (int i = 0; i < sampleSize; i++) {
            assertEquals(sample.get(i), X.getSample().get(i));
        }

        System.out.println("\nUniform Distribution Sample: ");
        System.out.println(X.getSample());
    }

    @Test
    public void normalDistribTest() {
        RandomValue X = new RandomValue("Normal", DistributionType.NORMAL);
        ArrayList<Double> sample = X.generateSample(10);

        assertEquals("Normal", X.getName());
        assertEquals(DistributionType.NORMAL, X.getType());
        assertEquals(10, sample.size());

        for (int i = 0; i < sampleSize; i++) {
            assertEquals(sample.get(i), X.getSample().get(i));
        }

        System.out.println("\nNormal Distribution Sample: ");
        System.out.println(X.getSample());
    }

    @Test
    public void poissonDistribTest() {
        RandomValue X = new RandomValue("Poisson", DistributionType.POISSON);
        ArrayList<Double> sample = X.generateSample(sampleSize);

        assertEquals("Poisson", X.getName());
        assertEquals(DistributionType.POISSON, X.getType());
        assertEquals(sampleSize, sample.size());

        for (int i = 0; i < sampleSize; i++) {
            assertEquals(sample.get(i), X.getSample().get(i));
        }

        System.out.println("\nPoisson Distribution Sample: ");
        System.out.println(X.getSample());
    }
}
