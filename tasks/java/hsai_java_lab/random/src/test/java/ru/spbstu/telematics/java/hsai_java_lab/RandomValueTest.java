package ru.spbstu.telematics.java.hsai_java_lab;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import ru.spbstu.telematics.java.hsai_java_lab.distribution.Distribution.DistributionType;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValue;

public class RandomValueTest {
    final int sampleSize = 10;

    @Test
    public void uniformDistribTest() {
        RandomValue X = RandomValue.uniformDistribution("Uniform", -10, 10);
        ArrayList<Double> sample = new ArrayList<Double>();
        for (int i = 0; i < sampleSize; i++) {
            sample.add(X.generate());
        }

        assertEquals("Uniform", X.getName());
        assertEquals(DistributionType.UNIFORM, X.getType());
        assertEquals(sampleSize, sample.size());

        System.out.println("\nUniform Distribution Sample: ");
        System.out.println(sample);
    }

    @Test
    public void normalDistribTest() {
        RandomValue X = RandomValue.normalDistribution("Normal", 0, 1);
        ArrayList<Double> sample = new ArrayList<Double>();
        for (int i = 0; i < sampleSize; i++) {
            sample.add(X.generate());
        }

        assertEquals("Normal", X.getName());
        assertEquals(DistributionType.NORMAL, X.getType());
        assertEquals(10, sample.size());

        System.out.println("\nNormal Distribution Sample: ");
        System.out.println(sample);
    }

    @Test
    public void poissonDistribTest() {
        RandomValue X = RandomValue.poissonDistribution("Poisson", 10);
        ArrayList<Double> sample = new ArrayList<Double>();
        for (int i = 0; i < sampleSize; i++) {
            sample.add(X.generate());
        }

        assertEquals("Poisson", X.getName());
        assertEquals(DistributionType.POISSON, X.getType());
        assertEquals(sampleSize, sample.size());

        System.out.println("\nPoisson Distribution Sample: ");
        System.out.println(sample);
    }
}
