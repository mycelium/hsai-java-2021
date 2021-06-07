package ru.spbstu.telematics.java.hsai_java_lab;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ru.spbstu.telematics.java.hsai_java_lab.storage.StorageException;
import ru.spbstu.telematics.java.hsai_java_lab.storage.Storage.StorageType;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValue;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueTable;

public class StorageTest {
    private RandomValueTable tableCsv;
    private RandomValueTable tableDb;
    private ArrayList<RandomValue> values;

    @Before
    public void setUp() {
        values = new ArrayList<RandomValue>();
        values.add(RandomValue.uniformDistribution("Uniform", -10, 10));
        values.add(RandomValue.normalDistribution("Normal", 0, 1));
        values.add(RandomValue.poissonDistribution("Poisson", 10));

        tableCsv = new RandomValueTable(values, "Table_1", 100, StorageType.CSV);
        tableDb = new RandomValueTable(values, "Table_2", 100, StorageType.DATABASE);
    }

    @Test
    public void saveTableCsv() {
        try {
            String filePath = tableCsv.save();
            System.out.println(filePath);
        }
        catch (NullPointerException e) {
            Assert.fail("Unexpected Nullptr exception");
        }
        catch (StorageException e) {
            Assert.fail("Unexpected Storage Exception");
        } 
    }

    @Test
    public void saveTableDb() {
        try {
            String filePath = tableDb.save();
            System.out.println(filePath);
        }
        catch (NullPointerException e) {
            Assert.fail("Unexpected Nullptr exception");
        }
        catch (StorageException e) {
            Assert.fail("Unexpected Storage Exception");
        }
    }
}
