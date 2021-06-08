package ru.spbstu.telematics.java;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import ru.spbstu.telematics.java.data.RandomData;
import ru.spbstu.telematics.java.data.VariableFactory;
import ru.spbstu.telematics.java.saving.DataSaver;
import ru.spbstu.telematics.java.utils.Sample;

import java.beans.Transient;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CheckNormalTest
{
    private final String fileName = "NormalTest";
    private final String variable1 = "FirstVariable";
    private final String variable2 = "SecondVariable";
    private final String variable3 = "ThirdVariable";
    private final String variable4 = "FourthVariable";

    @Test
    public void standardBehaviourTest() {
        RandomData rd = new RandomData("RandomData").
                addVariable(VariableFactory.makeNormalDistributionVariable(variable1)).
                addVariable(VariableFactory.makeNormalDistributionVariable(variable2, 10, 1)).
                addVariable(VariableFactory.makeUniformDistributionVariable(variable3, -5, 5)).
                addVariable(VariableFactory.makePoissonDistributionVariable(variable4, 5));
        try {
            CSVReader reader = new CSVReader(DataSaver.saveCSV(rd, 100000   , fileName));
            ArrayList<Sample> variables = reader.getVariables();
            assertTrue(CheckNormal.isNormal(variables.get(0)));
            assertTrue(CheckNormal.isNormal(variables.get(1)));
            assertFalse(CheckNormal.isNormal(variables.get(2)));
            assertFalse(CheckNormal.isNormal(variables.get(3)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
