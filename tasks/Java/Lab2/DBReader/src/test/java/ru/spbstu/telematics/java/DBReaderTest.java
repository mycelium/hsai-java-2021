package ru.spbstu.telematics.java;


import org.junit.Test;
import ru.spbstu.telematics.java.data.RandomData;
import ru.spbstu.telematics.java.data.VariableFactory;
import ru.spbstu.telematics.java.saving.DataSaver;
import ru.spbstu.telematics.java.utils.Sample;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class DBReaderTest
{
    private final String outputPathDB = new File("").getAbsolutePath() + "/Output/";
    private final String outputFileNameDB = "testFile.db";
    private final String variableName1 = "TestVariable1";

    @Test
    public void standardBehaviourTest() {
        RandomData rd = new RandomData("RandomData").
                addVariable(VariableFactory.makeNormalDistributionVariable(variableName1));
        try {
            DataSaver.saveDB(rd, 10, outputFileNameDB);
            DBReader dbReader = new DBReader(outputPathDB + outputFileNameDB, "RandomData");
            Sample sample = dbReader.getVariables().get(0);
            assertEquals(variableName1, sample.getName());
        } catch (ClassNotFoundException | SQLException | IOException e) {
            assertTrue(false);
        }
    }
}
