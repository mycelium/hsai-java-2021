package ru.spbstu.telematics.java;

import org.junit.Test;
import ru.spbstu.telematics.java.data.RandomData;
import ru.spbstu.telematics.java.data.VariableFactory;
import ru.spbstu.telematics.java.saving.DataSaver;
import ru.spbstu.telematics.java.utils.Sample;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class CSVReaderTest
{
    private final String outputPathCSV = new File("").getAbsolutePath() + "/Output/";
    private final String outputFileNameCSV = "testFile.csv";
    private final String variableName1 = "TestVariable1";

    @Test
    public void standardBehaviourTest() {
        try {
            RandomData randomData = new RandomData("TestData").
                    addVariable(VariableFactory.makeUniformDistributionVariable(variableName1, -1,1));
            DataSaver.saveCSV(randomData, 10, outputFileNameCSV);
            CSVReader reader = new CSVReader(outputPathCSV + outputFileNameCSV);
            ArrayList<Sample> test = reader.getVariables();
            assertEquals(randomData.getVariables().get(0).getName(), test.get(0).getName());
        } catch (IOException e) {
            assertTrue(false);
        }
    }
}
