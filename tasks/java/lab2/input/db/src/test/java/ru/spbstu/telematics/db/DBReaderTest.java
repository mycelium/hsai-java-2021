package ru.spbstu.telematics.db;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.spbstu.telematics.reader.TableReader;

public class DBReaderTest {

    private final String DB_FILE = "src/test/resources/outDB.db";
    private final int NUMBER_OF_VALUES = 30;
    private final int SIZE = 3;

    @Test
    public void DBReaderCorrectLinesTest() throws Exception {
        TableReader reader = new DBReader(DB_FILE);
        var list = reader.readAllDistribution();
        Assert.assertEquals(list.size(), SIZE);
        for (var distribution : list) {
            Assert.assertEquals(distribution.size(), NUMBER_OF_VALUES);
        }
    }

}
