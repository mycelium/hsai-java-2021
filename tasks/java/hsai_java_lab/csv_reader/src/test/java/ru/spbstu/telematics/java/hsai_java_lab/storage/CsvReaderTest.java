package ru.spbstu.telematics.java.hsai_java_lab.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import junit.framework.Assert;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueSample;

public class CsvReaderTest 
{
    @Test
    public void openCsvFileTest() {
        try {
            CsvReader reader = new CsvReader("src/test/test_data/test.csv");
        }
        catch (FileNotFoundException e) {
            System.err.println("Unexpected FNF exception");
            Assert.fail();
        }
        catch (NullPointerException e) {
            System.err.println("Unexpected Nullptr exception");
            Assert.fail();
        }
    }

    @Test(expected = NullPointerException.class)
    public void openNullCsvFileTest() throws NullPointerException{
        try {
            CsvReader reader = new CsvReader(null);
            assertNotNull(reader);
        }
        catch (FileNotFoundException e) {
            System.err.println("Unexpected FNF exception");
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
            System.err.println("Unxpected Nullptr exception");
        }
    }

    @Test
    public void readCsvTest() {
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
            Assert.fail("Unexpected Nullptr exception");
        }
        catch (FileNotFoundException e) {
            Assert.fail("Unexpected FNF exception");
        }
        catch (NumberFormatException e) {
            Assert.fail("Unxpected Number Format exception");
        }
        catch (IOException e) {
            Assert.fail("Unxpected IO exception");
        }
        
    }
}
