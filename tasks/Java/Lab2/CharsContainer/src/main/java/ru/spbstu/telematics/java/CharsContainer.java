package ru.spbstu.telematics.java;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that contains data for JSON output (variables' names and their characteristics).
 * */
public class CharsContainer {
    private HashMap<String, HashMap<String, Double>> data;
    private ArrayList<String> names;
    private int variablesAmount;

    public CharsContainer(String variableName, HashMap<String, Double> characteristics) {
        data = new HashMap<>();
        names = new ArrayList<>();
        data.put(variableName, characteristics);
        names.add(variableName);
        variablesAmount = 1;
    }

    public CharsContainer addVariable(String variableName, HashMap<String, Double> characteristics) {
        data.put(variableName, characteristics);
        names.add(variableName);
        variablesAmount++;
        return this;
    }

    public HashMap<String, HashMap<String, Double>> getData() {
        return data;
    }

    public HashMap<String, Double> getVariable(String variableName) {
        return data.get(variableName);
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public int size() {return variablesAmount; }
}
