package ru.spbstu.telematics.cvs;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.spbstu.telematics.reader.TableReader;

public class CSVReaderTest {

    private final String CSV_FILE = "src/test/resources/outCSV.csv";
    private final int NUMBER_OF_VALUES = 30;
    private final int SIZE = 3;

    @Test
    public void CSVReaderCorrectLinesTest() throws Exception {
        TableReader reader = new CSVReader(CSV_FILE);
        var list = reader.readAllDistribution();
        Assert.assertEquals(list.size(), SIZE);
        for (var distribution : list) {
//            System.out.println(distribution.getName());
//            distribution.forEach(System.out::println);
            Assert.assertEquals(distribution.size(), NUMBER_OF_VALUES);
        }
    }

}
