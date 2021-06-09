package ru.spbstu.telematics.app;

import parameters.ResultParameters;
import reader.TableReader;
import reader.csv.CSVReader;
import reader.db.DBReader;

public class App {

    public static void main(String[] args) throws Exception {
        TableReader reader = new CSVReader("");
        TableReader reader1 = new DBReader("", "");
        var r = reader.readAllDistribution();
        ResultParameters resultParameters = new ResultParameters(null);
        resultParameters.toJSON();
    }

}
