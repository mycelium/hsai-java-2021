package ru.spbstu.telematics.lab1;

import ru.spbstu.telematics.lab1.random.Distribution;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for generating rows of random data of given {@link Variable}s.
 * In some sense, it is a table of data with undetermined number of rows,
 * since values are random.
 */
public class RandomData {
    private final ArrayList<Variable> variables;
    /**
     * Name of table.
     */
    private String name = "Random_data";

    public RandomData() {
        variables = new ArrayList<>();
    }

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

    /**
     * @return list containing values for one row in tables.
     */
    public List<?> generateRow() {
        return getVariables().stream().map(Variable::getDistribution)
                .map((Distribution d) -> (d.isDiscrete())?
                        d.generateInt() : d.generateFloat()).
                collect(Collectors.toList());
    }

    /**
     * @return list of column names.
     */
    public List<String> columnNames() {
        return getVariables().stream().
                map(Variable::getName).
                collect(Collectors.toList());
    }

    public RandomData addVariable(Variable variable) {
        variables.add(variable);
        return this;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }
}
