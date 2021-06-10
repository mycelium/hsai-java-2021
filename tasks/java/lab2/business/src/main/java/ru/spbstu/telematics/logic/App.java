package ru.spbstu.telematics.logic;

import ru.spbstu.telematics.analyzer.normality.NormalityAnalyzer;
import ru.spbstu.telematics.parameters.ResultParameters;
import ru.spbstu.telematics.reader.csv.CSVReader;
import ru.spbstu.telematics.reader.db.DBReader;
import ru.spbstu.telematics.variables.Variable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) {
        System.out.println("Start");
        var csvReader = new CSVReader("");
        var dbReader = new DBReader("", "");
        Variable<Double> rs = new Variable<>("dss");
        System.out.println(rs.getName());
        NormalityAnalyzer.isNormal(null);
        try {
            csvReader.readAllDistribution();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dbReader.readAllDistribution();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        List<ResultParameters> resultParameters = ResultParameters.getResultParameters(null);
    }

}
