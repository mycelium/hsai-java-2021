package ru.spbstu.hsai.stats;

import ru.spbstu.hsai.outputter.CSVOutputter;
import ru.spbstu.hsai.outputter.DBOutputter;
import ru.spbstu.hsai.outputter.OutputType;

import java.util.ArrayList;

abstract class Distribution {
    static void output(OutputType outputType, ArrayList<Double> vals, String fname) {
        if (outputType == OutputType.CSV)
            CSVOutputter.output(vals, fname);
        else if (outputType == OutputType.database)
            DBOutputter.output(vals, fname);
    }
}
