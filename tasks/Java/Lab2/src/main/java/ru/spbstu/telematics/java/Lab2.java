package ru.spbstu.telematics.java;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.telematics.java.utils.Sample;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Lab2
{
    private static Logger logger = LoggerFactory.getLogger(Lab2.class);
    public static void main( String[] args ) {
        Options options = new Options();
        Option csv = OptionBuilder.withArgName("filename").
                hasArg().
                withDescription("Use csv file <filename>").
                create("csv");
        options.addOption(csv);
        Option db = OptionBuilder.withArgName("filename").
                hasArgs(2).
                withValueSeparator(',').
                withDescription("Use db file <filename>").
                create("db");
        options.addOption(db);
        Option normal = OptionBuilder.withArgName("variable number").
                hasArg().
                withDescription("Find out whether the variable <variable number> is normally distributed or not (<all> for all the variables)").
                create("normal");
        options.addOption(normal);
        options.addOption("json", false, "Present variables' characteristics in JSON formatted table");
        Option graph = OptionBuilder.withArgName("variable number").
                hasArg().
                withDescription("Plot the graph for the variable's <variable number> values (<all> for all the variables)").
                create("graph");
        options.addOption(graph);
        Option histo = OptionBuilder.withArgName("variable number").
                hasArg().
                withDescription("Plot the histo for the variable <variable number> (<all> for all the variables)").
                create("histo");
        options.addOption(histo);
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Lab2", options);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        ArrayList<Sample> variables = new ArrayList<>();
        if(cmd.hasOption("csv")) {
            try {
                CSVReader reader = new CSVReader(cmd.getOptionValue("csv"));
                variables = reader.getVariables();
            } catch (IOException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        else if(cmd.hasOption("db")) {
            try {
                DBReader reader = new DBReader(cmd.getOptionValues("db")[0], cmd.getOptionValues("db")[1]);
                variables = reader.getVariables();
            } catch (ClassNotFoundException | SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        else {
            logger.error("No file was provided");
            System.out.println("No file was provided");
            return;
        }
        if(cmd.hasOption("normal")) {
            String param = cmd.getOptionValue("normal");
            if(param == "all") {
                for(Sample s: variables) {
                    boolean isNormal = CheckNormal.isNormal(s);
                    if(isNormal)
                        System.out.println("Variable " + s.getName() + " is distributed normally");
                    else
                        System.out.println("Variable " + s.getName() + " is not distributed normally");
                }
            }
            else {
                try {
                    int varNum = Integer.parseInt(param);
                    if(0 > varNum || varNum > variables.size() - 1) {
                        logger.error("Index " + param + " is out of range for list with " + variables.size() + " elements");
                        System.out.println("Index " + param + " is out of range for list with " + variables.size() + " elements");
                    }
                    boolean isNormal = CheckNormal.isNormal(variables.get(varNum));
                    if(isNormal)
                        System.out.println("Variable " + variables.get(varNum).getName() + " is distributed normally");
                    else
                        System.out.println("Variable " + variables.get(varNum).getName() + " is not distributed normally");
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        if(cmd.hasOption("json")) {
            CharsContainer container = new CharsContainer(variables.get(0).getName(), new Characteristics(variables.get(0)).getCharacteristics());
            for(int i = 1; i < variables.size(); i++) {
                container.addVariable(variables.get(i).getName(), new Characteristics(variables.get(i)).getCharacteristics());
            }
            JSONOutput out = new JSONOutput(container, "RandomData");
            try {
                out.writeToFile("/JSONOutput/Table");
            } catch (IOException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        if(cmd.hasOption("graph")) {
            GraphOutput out = new GraphOutput(variables, "RandomData");
            String param = cmd.getOptionValue("graph");
            if(param == "all") {
                System.out.println("Graph is located at: " + out.plotGraph("RandomDataGraph"));
            }
            else {
                try {
                    int varNum = Integer.parseInt(param);
                    System.out.println("Graphs are located at: " + out.plotGraphForVariable(varNum, "RandomDataGraph"));
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        if(cmd.hasOption("histo")) {
            GraphOutput out = new GraphOutput(variables, "RandomData");
            String param = cmd.getOptionValue("histo");
            if(param == "all") {
                System.out.println("Histo is located at: " + out.plotHisto("RandomDataHisto"));
            }
            else {
                try {
                    int varNum = Integer.parseInt(param);
                    System.out.println("Histograms are located at: " + out.plotGraphForVariable(varNum, "RandomDataHisto"));
                } catch (NumberFormatException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
