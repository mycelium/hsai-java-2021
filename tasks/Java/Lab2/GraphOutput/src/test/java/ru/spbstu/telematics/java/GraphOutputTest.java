package ru.spbstu.telematics.java;

import org.junit.Test;
import ru.spbstu.telematics.java.data.RandomData;
import ru.spbstu.telematics.java.data.VariableFactory;
import ru.spbstu.telematics.java.saving.DataSaver;
import ru.spbstu.telematics.java.utils.Sample;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class GraphOutputTest
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
            GraphOutput out = new GraphOutput(variables, "RandomData");
            out.plotHisto("TestHisto");
            out.plotGraph("TestGraph");
        } catch (IOException e) {
           assertTrue(false);
        }
    }
}
