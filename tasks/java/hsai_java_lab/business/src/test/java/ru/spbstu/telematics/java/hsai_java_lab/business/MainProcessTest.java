package ru.spbstu.telematics.java.hsai_java_lab.business;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ru.spbstu.telematics.java.hsai_java_lab.storage.Storage.StorageType;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValue;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueTable;

public class MainProcessTest {
    private RandomValueTable csvTable;
    private RandomValueTable dbTable;
    private MainBusinessProcess process;
    private final int sampleSize = 66000;

    @Before
    public void setUp() {
        ArrayList<RandomValue> values1 = new ArrayList<RandomValue>(8);
        values1.add(RandomValue.normalDistribution("Normal 1", 0., 1.));
        values1.add(RandomValue.uniformDistribution("Uniform 1", 5., 15.));
        values1.add(RandomValue.normalDistribution("Normal 2", 5., 3.));
        values1.add(RandomValue.uniformDistribution("Uniform 2", -10., 10.));
        values1.add(RandomValue.poissonDistribution("Poisson 1", 5));
        values1.add(RandomValue.poissonDistribution("Poisson 2", 10));
        values1.add(RandomValue.uniformDistribution("Uniform 3", -2., 2.));
        values1.add(RandomValue.uniformDistribution("Uniform 4", -5., 10.));

        ArrayList<RandomValue> values2 = new ArrayList<RandomValue>(8);
        values2.add(RandomValue.normalDistribution("Normal 1", 0., 1.));
        values2.add(RandomValue.uniformDistribution("Uniform 1", 5., 15.));
        values2.add(RandomValue.normalDistribution("Normal 2", 5., 3.));
        values2.add(RandomValue.uniformDistribution("Uniform 2", -10., 10.));
        values2.add(RandomValue.poissonDistribution("Poisson 1", 5));
        values2.add(RandomValue.poissonDistribution("Poisson 2", 10));
        values2.add(RandomValue.uniformDistribution("Uniform 3", -2., 2.));
        values2.add(RandomValue.uniformDistribution("Uniform 4", -5., 10.));

        csvTable = new RandomValueTable(values1, "Table CSV", sampleSize, StorageType.CSV);
        dbTable = new RandomValueTable(values2, "Table DB", sampleSize, StorageType.DATABASE);
    }

    @Test
    public void csvTableProcessTest() {
        process = new MainBusinessProcess(csvTable);
        int status = process.runProcess();
        assertEquals(0, status);
    }

    @Test
    public void dbTableProcessTest() {
        process = new MainBusinessProcess(dbTable);
        int status = process.runProcess();
        assertEquals(0, status);
    }
}
