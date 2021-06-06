package ru.spbstu.telematics.java.data;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class RandomData {
    private ArrayList<Variable> variables;
    private String name = "RandomData";

    public RandomData(String name) {
        this.name = name;
        variables = new ArrayList<>();
    }

    public RandomData(ArrayList<Variable> variables) {
        this.variables = variables;
    }

    public RandomData(ArrayList<Variable> variables, String name) {
        this.variables = variables;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * @param variable variable to add to list
     * @return this RandomData with added variable
     * */
    public RandomData addVariable(Variable variable) {
        variables.add(variable);
        return this;
    }

    /**
     * @return list of variables' names
     */
    public ArrayList<String> getVariablesNames() {
        ArrayList<String> res = new ArrayList<>();
        for(Variable v: variables) {
            res.add(v.getName());
        }
        return res;
    }

    /**
     * @return list of random values: one value for each of the variables
     * */
    public ArrayList<Object> getTableRow() {
        ArrayList<Object> res = new ArrayList<>();
        for(Variable v: variables) {
            res.add(v.generateValue());
        }
        return res;
    }

    /**
     * @return list of variables
     * */
    public ArrayList<Variable> getVariables() {
        return variables;
    }
}
