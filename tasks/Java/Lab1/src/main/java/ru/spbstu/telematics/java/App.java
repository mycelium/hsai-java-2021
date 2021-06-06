package ru.spbstu.telematics.java;

import ru.spbstu.telematics.java.data.*;
import ru.spbstu.telematics.java.saving.DataSaver;

import java.util.ArrayList;

public class App {
    public static void main( String[] args ) {
        VariableFactory factory = new VariableFactory();
        RandomData randomData = new RandomData("TestData").
                addVariable(factory.makeNormalDistributionVariable("Standard Normal Distribution")).
                addVariable(factory.makeUniformDistributionVariable("Uniform", 1, 2));
        DataSaver.saveCSV(randomData, 10, "testCSV");
    }
}
