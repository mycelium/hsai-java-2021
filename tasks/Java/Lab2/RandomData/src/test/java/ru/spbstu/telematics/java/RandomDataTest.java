package ru.spbstu.telematics.java;

import org.junit.Before;
import org.junit.Test;
import ru.spbstu.telematics.java.data.RandomData;
import ru.spbstu.telematics.java.data.VariableFactory;
import ru.spbstu.telematics.java.saving.DataSaver;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class RandomDataTest
{
    private final String fileName = "testFile";

    @Before
    public void setUp() {
        File CSVFile = new File(new File("").getAbsoluteFile() + "/Output/" + fileName + ".csv");
        File DBFile = new File(new File("").getAbsoluteFile() + "/Output/" + fileName + ".db");
        if(CSVFile.exists())
            CSVFile.delete();
        if(DBFile.exists())
            DBFile.delete();

    }

    @Test
    public void emptyDataTest() {
        RandomData rd = new RandomData("testName");
        try {
            String CSVFilePath = DataSaver.saveCSV(rd,1, "testFile");
            String DBFilePath = DataSaver.saveDB(rd,1,"testFile");
            File CSVFile = new File(CSVFilePath);
            File DBFile = new File(DBFilePath);
            assertTrue(CSVFile.exists());
            assertTrue(DBFile.exists());
            CSVFile.delete();
            DBFile.delete();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nullSizeTest() {
        RandomData rd = new RandomData("testName").
                addVariable(VariableFactory.makeNormalDistributionVariable("test"));
        try {
            String CSVFilePath = DataSaver.saveCSV(rd,0, "testFile");
            String DBFilePath = DataSaver.saveDB(rd,0,"testFile");
            File CSVFile = new File(CSVFilePath);
            File DBFile = new File(DBFilePath);
            assertTrue(CSVFile.exists());
            assertTrue(DBFile.exists());
            CSVFile.delete();
            DBFile.delete();
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void negativeSizeCSVTest () {
        RandomData rd = new RandomData("testName").
                addVariable(VariableFactory.makeNormalDistributionVariable("TestVariable"));
        try {
            String CSVFilePath = DataSaver.saveCSV(rd,-1, "testFile");
        } catch (IllegalArgumentException e) {
            assertEquals("Size of the sample cannot be a negative number", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void negativeSizeDBTest () {
        RandomData rd = new RandomData("testName").
                addVariable(VariableFactory.makeNormalDistributionVariable("TestVariable"));
        try {
            String CSVFilePath = DataSaver.saveDB(rd,-1, "testFile");
        } catch (IllegalArgumentException e) {
            assertEquals("Size of the sample cannot be a negative number", e.getMessage());
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void standardBehaviourTest () {
        RandomData rd = new RandomData("testName").
                addVariable(VariableFactory.makeNormalDistributionVariable("test")).
                addVariable(VariableFactory.makeNormalDistributionVariable("anotherTest", 0, 1)).
                addVariable(VariableFactory.makePoissonDistributionVariable("YetAnotherTest", 1)).
                addVariable(VariableFactory.makeUniformDistributionVariable("OneMoreTest", -1,1));
        try {
            String CSVFilePath = DataSaver.saveCSV(rd,10, "testFile");
            String DBFilePath = DataSaver.saveDB(rd,10,"testFile");
            File CSVFile = new File(CSVFilePath);
            File DBFile = new File(DBFilePath);
            assertTrue(CSVFile.exists());
            assertTrue(DBFile.exists());
        } catch (IOException | ClassNotFoundException | SQLException e) {
            assertTrue(false);
        }
    }
}
