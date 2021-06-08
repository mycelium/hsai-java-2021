package ru.spbstu.telematics.java;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import ru.spbstu.telematics.java.utils.Sample;
import ru.spbstu.telematics.java.utils.SamplesForTests;

import static org.junit.Assert.*;

public class CharacteristicsTest
{
    @Test
    public void standardBehaviourTest() {
        Sample sampleNormal = SamplesForTests.getNormalSample();
        Sample samplePoisson = SamplesForTests.getPoissonSample();
        Sample sampleUniform = SamplesForTests.getUniformSample();
        Characteristics characteristicsNormal = new Characteristics(sampleNormal);
        Characteristics characteristicsPoisson = new Characteristics(samplePoisson);
        Characteristics characteristicsUniform = new Characteristics(sampleUniform);
        double[] expectedNormal = SamplesForTests.getNormalCharacteristics();
        double[] expectedPoisson = SamplesForTests.getPoissonCharacteristics();
        double[] expectedUniform = SamplesForTests.getUniformCharacteristics();
        assertEquals(expectedNormal[0], characteristicsNormal.getMean(), 0.01);
        assertEquals(expectedNormal[1], characteristicsNormal.getMedian(), 0.01);
        assertEquals(expectedNormal[2], characteristicsNormal.getMin(), 0.01);
        assertEquals(expectedNormal[3], characteristicsNormal.getMax(), 0.01);
        assertEquals(expectedPoisson[0], characteristicsPoisson.getMean(), 0.01);
        assertEquals(expectedPoisson[1], characteristicsPoisson.getMedian(), 0.01);
        assertEquals(expectedPoisson[2], characteristicsPoisson.getMin(), 0.01);
        assertEquals(expectedPoisson[3], characteristicsPoisson.getMax(), 0.01);
        assertEquals(expectedUniform[0], characteristicsUniform.getMean(), 0.01);
        assertEquals(expectedUniform[1], characteristicsUniform.getMedian(), 0.01);
        assertEquals(expectedUniform[2], characteristicsUniform.getMin(), 0.01);
        assertEquals(expectedUniform[3], characteristicsUniform.getMax(), 0.01);
    }
}
