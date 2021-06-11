package ru.spbstu.hsai.outputter;

import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;
import au.com.bytecode.opencsv.CSVWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CSVOutputter {

    public static String output(ArrayList<Double> vals, String fname) {
        String[] res = new String[vals.size()];
        for (int i = 0; i < vals.size(); i++)
            res [i] = vals.get(i).toString();

        try {
            CSVWriter out = new CSVWriter(new FileWriter(Paths.get("output") + fname));
            out.writeNext(res);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Paths.get(fname).toAbsolutePath().toString();
    }
}
