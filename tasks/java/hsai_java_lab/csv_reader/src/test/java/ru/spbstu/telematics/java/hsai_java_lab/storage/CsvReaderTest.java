package ru.spbstu.telematics.java.hsai_java_lab.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueSample;

public class CsvReaderTest 
{
    @Test
    public void openCsvFileTest() throws FileNotFoundException {
        try {
            CsvReader reader = new CsvReader("src/test/test_data/test.csv");
        }
        catch (FileNotFoundException e) {
            throw e;
        }
        catch (NullPointerException e) {
            throw e;
        }
    }

    @Test(expected = NullPointerException.class)
    public void openNullCsvFileTest() throws FileNotFoundException {
        try {
            CsvReader reader = new CsvReader(null);
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
            CsvReader reader = new CsvReader("src/test/test_data/garbage.csv");
        }
        catch (FileNotFoundException e) {
            throw e;
        }
        catch (NullPointerException e) {
            throw e;
        }
    }

    @Test
    public void readCsvTest() throws FileNotFoundException, IOException {
        CsvReader reader;

        try {
            reader = new CsvReader("src/test/test_data/test.csv");
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
        catch (FileNotFoundException e) {
            throw e;
        }
        catch (NumberFormatException e) {
            throw e;
        }
        catch (IOException e) {
            throw e;
        }
        
    }
}
