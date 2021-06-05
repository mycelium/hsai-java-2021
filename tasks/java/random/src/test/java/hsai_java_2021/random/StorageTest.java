package hsai_java_2021.random;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import hsai_java_2021.random.distribution.Distribution.DistributionType;
import hsai_java_2021.random.storage.Storage.StorageType;

public class StorageTest {
    private RandomValuesTable tableCsv;
    private RandomValuesTable tableDbf;
    private ArrayList<RandomValue> values;

    @Before
    public void setUp() {
        values = new ArrayList<RandomValue>();
        values.add(new RandomValue("Uniform", DistributionType.UNIFORM));
        values.add(new RandomValue("Normal", DistributionType.NORMAL));
        values.add(new RandomValue("Poisson", DistributionType.POISSON));

        tableCsv = new RandomValuesTable(values, 20, StorageType.CSV);
        tableDbf = new RandomValuesTable(values, 20, StorageType.DATABASE);
    }

    @Test
    public void saveTableCsv() {
        tableCsv.save();
    }
}
