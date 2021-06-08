package ru.spbstu.telematics.java.hsai_java_lab.business;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import ru.spbstu.telematics.java.hsai_java_lab.storage.Storage.StorageType;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValue;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueTable;

public class InputParser {
    CommandLineParser parser;
    Options randomValueOptions;

    public InputParser() {
        randomValueOptions = new Options();
        randomValueOptions.addRequiredOption("n", "name", true, "Name of the random value");
        randomValueOptions.addRequiredOption("d", "distribution", true, "Distribution of the random value");

        parser = new DefaultParser();
    }

    public RandomValueTable interactiveMode() {
        Scanner in = new Scanner(System.in);

        int columnNumber;
        for(;;) {
            System.out.println("\nNumber of random valued: ");
            try {
                columnNumber = in.nextInt();

                if (columnNumber < 1) {
                    continue;
                }
            }
            catch (NoSuchElementException | IllegalStateException e) {
                continue;
            }
            break;
        }

        int rowNumber;
        for(;;) {
            System.out.println("\nSize of samples: ");
            try {
                rowNumber = in.nextInt();

                if (rowNumber <= 0) {
                    continue;
                }
            }
            catch (NoSuchElementException | IllegalStateException e) {
                continue;
            }
            break;
        }

        StorageType storageType;
        for(;;) {
            System.out.println("\nType of storage (db/csv): ");
            try {
                String storageTypeString = in.next().trim();
                
                if (storageTypeString.equals("db")) {
                    storageType = StorageType.DATABASE;
                }
                else if (storageTypeString.equals("csv")) {
                    storageType = StorageType.CSV;
                }
                else {
                    continue;
                }
            }
            catch (NoSuchElementException | IllegalStateException e) {
                continue;
            }
            break;
        }

        ArrayList<RandomValue> tableValues = new ArrayList<RandomValue>(columnNumber);
        for(int i = 0; i < columnNumber; i++) {
            for(;;) {
                System.out.print("\nRandom value #" + (i + 1) + " parameters: ");
                try {
                    String[] valueParams = in.next().trim().split("\\s*");
                    CommandLine cmd = parser.parse(randomValueOptions, valueParams);
                    
                    String valueName = cmd.getOptionValue("n");
                    String valueDistrib = cmd.getOptionValue("d");
                    if (valueDistrib.equals("N")) {
                        tableValues.add(RandomValue.normalDistribution(valueName, 0., 1.));
                    }
                    else if (valueDistrib.equals("U")) {
                        tableValues.add(RandomValue.uniformDistribution(valueName, -10, 10));
                    }
                    else if (valueDistrib.equals("P")) {
                        tableValues.add(RandomValue.poissonDistribution(valueName, 10));
                    }
                    else {
                        System.out.println("Undefined distribution type: " + valueDistrib);
                        System.out.println("Possible vlues: N -- Normal; U -- Uniform; P -- Poisson");
                        continue;
                    }
                }
                catch (NoSuchElementException | IllegalStateException e) {
                    continue;
                }
                catch (org.apache.commons.cli.ParseException e) {
                    System.out.println("Parsing failed: " + e.getMessage());
                    continue;
                }
                break;
            }
        }

        return new RandomValueTable(tableValues, null, rowNumber, storageType);
    }
}
