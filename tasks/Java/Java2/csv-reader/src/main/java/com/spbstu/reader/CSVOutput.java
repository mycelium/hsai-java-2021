package com.spbstu.reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVOutput {
    public CSVOutput(String filename) {
        this.filename = filename;
    }

    String filename;

    public String save(ArrayList<Double> list) throws IOException {
        FileWriter out = new FileWriter(filename+".csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT)) {
            printer.printRecord("value");
            for (Double value : list) {
                printer.printRecord(value);
            }
        }
        return filename+"csv";
    }
}
