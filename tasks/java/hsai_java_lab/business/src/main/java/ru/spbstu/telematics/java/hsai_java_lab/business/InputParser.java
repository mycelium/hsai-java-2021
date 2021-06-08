package ru.spbstu.telematics.java.hsai_java_lab.business;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.telematics.java.hsai_java_lab.storage.Storage.StorageType;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValue;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueTable;

public class InputParser {
    CommandLineParser parser;
    Options randomValueOptions;

    private static final Logger logger = LoggerFactory.getLogger(InputParser.class);

    public InputParser() {
        randomValueOptions = new Options();
        randomValueOptions.addRequiredOption("n", "name", true, "Name of the random value");
        randomValueOptions.addRequiredOption("d", "distribution", true, "Distribution of the random value");

        parser = new DefaultParser();
        logger.info("Parser created");
    }

    public RandomValueTable interactiveMode() {
        Scanner in = new Scanner(System.in);

        int columnNumber;
        for(;;) {
            System.out.println("\nNumber of random valued: ");
            try {
                columnNumber = in.nextInt();

                if (columnNumber < 1) {
                    logger.warn("Invalid input format");
                    continue;
                }
            }
            catch (NoSuchElementException | IllegalStateException e) {
                logger.warn("Invalid input");
                continue;
            }
            break;
        }
        logger.info("Values number parsed");

        int rowNumber;
        for(;;) {
            System.out.println("\nSize of samples: ");
            try {
                rowNumber = in.nextInt();

                if (rowNumber <= 0) {
                    logger.warn("Invalid input format");
                    continue;
                }
            }
            catch (NoSuchElementException | IllegalStateException e) {
                logger.warn("Invalid input");
                continue;
            }
            break;
        }
        logger.info("Sample size parsed");

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
                    logger.warn("Invalid input format");
                    continue;
                }
            }
            catch (NoSuchElementException | IllegalStateException e) {
                logger.warn("Invalid input");
                continue;
            }
            break;
        }
        logger.info("Storage type parsed");

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
                        logger.warn("Invalid input format");
                        continue;
                    }
                }
                catch (NoSuchElementException | IllegalStateException e) {
                    logger.warn("Invalid input");
                    continue;
                }
                catch (org.apache.commons.cli.ParseException e) {
                    System.out.println("Parsing failed: " + e.getMessage());
                    logger.warn("Invalid input");
                    continue;
                }
                break;
            }
        }
        logger.info("Values parameters parsed");

        logger.info("Input is parsed");
        return new RandomValueTable(tableValues, null, rowNumber, storageType);
    }
}
