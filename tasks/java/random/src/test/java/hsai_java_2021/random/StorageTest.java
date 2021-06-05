package hsai_java_2021.random;

import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import hsai_java_2021.random.storage.Storage.StorageType;

public class StorageTest {
    private RandomValuesTable tableCsv;
    private RandomValuesTable tableDb;
    private ArrayList<RandomValue> values;

    @Before
    public void setUp() {
        values = new ArrayList<RandomValue>();
        values.add(RandomValue.uniformDistribution("Uniform", -10, 10));
        values.add(RandomValue.normalDistribution("Normal", 0, 1));
        values.add(RandomValue.poissonDistribution("Poisson", 10));

        tableCsv = new RandomValuesTable(values, 100, StorageType.CSV);
        tableDb = new RandomValuesTable(values, 100, StorageType.DATABASE);
    }

    @Test
    public void saveTableCsv() {
        assertNotEquals(null, tableCsv.save());
    }

    @Test
    public void saveTableDb() {
        assertNotEquals(null, tableDb.save());
    }
}
