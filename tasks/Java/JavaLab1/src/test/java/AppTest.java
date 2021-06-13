
import com.linuxense.javadbf.DBFReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void testApp() throws IOException {
        Main.main("uniformFile", "uniform", "100", "CSV", "2", "3");
        Reader reader = new FileReader("uniformFile.csv");
        CSVParser csvRecords = new CSVParser(reader, CSVFormat.DEFAULT);
        Assert.assertEquals(csvRecords.getRecords().size(), 101);
    }

    @Test
    public void testApp2() throws FileNotFoundException {
        Main.main("poissonFile", "poisson", "10", "DBF", "2");
        DBFReader dbfReader = new DBFReader(new FileInputStream("poissonFile.dbf"));
        Assert.assertEquals(dbfReader.getRecordCount(), 10);
    }

    @Test
    public void testApp3() throws IOException {
        Main.main("normalFile", "normal", "50", "CSV", "2", "3");
        Reader reader = new FileReader("normalFile.csv");
        CSVParser csvRecords = new CSVParser(reader, CSVFormat.DEFAULT);
        Assert.assertEquals(csvRecords.getRecords().size(), 51);
    }
}