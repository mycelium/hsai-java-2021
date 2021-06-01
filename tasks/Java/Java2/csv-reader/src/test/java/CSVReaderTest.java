import com.spbstu.data.TableData;
import com.spbstu.reader.CSVOutput;
import com.spbstu.reader.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class CSVReaderTest {
    @Test
    public void ReaderTest() throws IOException {
        ArrayList<Double> listOut = new ArrayList<>();
        for(int i=0;i<8;i++){
            listOut.add(Double.valueOf(i));
        }
        CSVOutput output = new CSVOutput("csvoutput");
        output.save(listOut);
        CSVReader reader=new CSVReader("csvoutput");
        TableData gotTable = reader.read();
        Assert.assertTrue(listOut.equals(gotTable.getColumn(0)));
    }
}
