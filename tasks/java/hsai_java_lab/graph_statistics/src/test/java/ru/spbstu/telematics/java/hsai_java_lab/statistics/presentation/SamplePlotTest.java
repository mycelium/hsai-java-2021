package ru.spbstu.telematics.java.hsai_java_lab.statistics.presentation;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueSample;

public class SamplePlotTest {
    private RandomValueSample sample1;
    private RandomValueSample sample2;

    @Before
    public void setUp() {
        sample1 = new RandomValueSample("Normal Distrib");
        sample2 = new RandomValueSample("Uniform Distrib");
        Random generator = new Random();
        
        for (int i = 0; i < 200; i++) {
            sample1.addValue(generator.nextGaussian());
            sample2.addValue(generator.nextDouble());
        }
    }

    @Test
    public void plotSamplesTest() {
        ArrayList<RandomValueSample> samples = new ArrayList<RandomValueSample>(2);
        samples.add(sample1);
        samples.add(sample2);

        SamplePlot plot = new SamplePlot(samples);
        plot.plotSamples();
    }
}
