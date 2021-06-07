package ru.spbstu.telematics.java.hsai_java_lab.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueSample;

public class DatabaseReaderTest 
{
    @Test
    public void openDbFileTest() throws FileNotFoundException {
        try {
            DatabaseReader reader = new DatabaseReader("src/test/test_data/test.db");
        }
        catch (FileNotFoundException e) {
            throw e;
        }
        catch (NullPointerException e) {
            throw e;
        }
    }

    @Test(expected = NullPointerException.class)
    public void openNullDbFileTest() throws FileNotFoundException {
        try {
            DatabaseReader reader = new DatabaseReader(null);
            assertNotNull(reader);
        }
        catch (FileNotFoundException e) {
            throw e;
        }
        catch (NullPointerException e) {
            throw e;
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void openFnfCsvFileTest() throws FileNotFoundException{
        try {
            DatabaseReader reader = new DatabaseReader("src/test/test_data/garbage.db");
        }
        catch (FileNotFoundException e) {
            throw e;
        }
        catch (NullPointerException e) {
            throw e;
        }
    }

    @Test
    public void readCsvTest() throws FileNotFoundException, SQLException {
        DatabaseReader reader;

        try {
            reader = new DatabaseReader("src/test/test_data/test.db");
            ArrayList<RandomValueSample> samples = reader.readSamples();
            assertEquals(3, samples.size());
            assertEquals("Uniform", samples.get(0).getName());
            assertEquals("Normal", samples.get(1).getName());
            assertEquals("Poisson", samples.get(2).getName());
            assertEquals(100, samples.get(0).getSample().size());
            assertEquals(100, samples.get(1).getSample().size());
            assertEquals(100, samples.get(2).getSample().size());
        }
        catch (NullPointerException e) {
            throw e;
        }
        catch (NumberFormatException e) {
            throw e;
        }
        catch (SQLException e) {
            throw e;
        }
    }
}
