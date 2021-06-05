package hsai_java_2021.random;

import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import hsai_java_2021.random.distribution.Distribution.DistributionType;
import hsai_java_2021.random.storage.Storage.StorageType;

public class StorageTest {
    private RandomValuesTable tableCsv;
    private RandomValuesTable tableDb;
    private ArrayList<RandomValue> values;

    @Before
    public void setUp() {
        values = new ArrayList<RandomValue>();
        values.add(new RandomValue("Uniform", DistributionType.UNIFORM));
        values.add(new RandomValue("Normal", DistributionType.NORMAL));
        values.add(new RandomValue("Poisson", DistributionType.POISSON));

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
