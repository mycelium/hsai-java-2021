package ru.spbstu.telematics.logic;

import ru.spbstu.telematics.reader.csv.CSVReader;
import ru.spbstu.telematics.variables.Variable;

public class App {

    public static void main(String[] args) {
        System.out.println("Start");
        var r = new CSVReader("");
        Variable<Double> rs = new Variable<>("dss");
        System.out.println(rs.getName());
    }

}
