package com.spbstu.main;

import com.spbstu.data.Calculator;
import com.spbstu.data.Output;
import com.spbstu.data.Reader;
import com.spbstu.data.TableData;
import com.spbstu.db.DBReader;
import com.spbstu.json.JSONOutput;
import com.spbstu.normal.NormalCalculator;
import com.spbstu.plot.PlotOutput;
import com.spbstu.reader.CSVReader;
import org.apache.commons.cli.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws ParseException {
        Reader reader;
        Calculator calculator;
        Output output;
        ArrayList<Double> data = null;
        TableData tableData = null;
        String filenameIn ="g";
        String filenameOut = "out";
        Options options = new Options();
        options.addOption(Option.builder()
                .longOpt("input-source")
                .hasArg()
                .argName("source")
                .required()
                .build());
        options.addOption(Option.builder()
                .longOpt("calc-source")
                .hasArg()
                .argName("calc")
                .required()
                .build());
        options.addOption(Option.builder()
                .longOpt("output-source")
                .hasArg()
                .argName("out")
                .required()
                .build());
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        String optionValue = cmd.getOptionValue("input-source");
        if ("database".equals(optionValue)) {
            filenameIn="jdbc:h2:mem:db1";
            reader = new DBReader(filenameIn);
            tableData = reader.read();
        } else {
            filenameIn="csvoutput";
            reader = new CSVReader(filenameIn);
            tableData = reader.read();
        }
        optionValue = cmd.getOptionValue("calc-source");
        if ("all".equals(optionValue)) {
            calculator = new com.spbstu.calc.Calculator();
            data=calculator.calculate(tableData);
        } else {
            calculator = new NormalCalculator();
            data=calculator.calculate(tableData);
        }
        optionValue = cmd.getOptionValue("output-source");
        if ("JSON".equals(optionValue)) {
            output = new JSONOutput(data, filenameOut);
            output.output();
        } else {
            output = new PlotOutput(data, filenameOut);
            output.output();
        }


    }
}
