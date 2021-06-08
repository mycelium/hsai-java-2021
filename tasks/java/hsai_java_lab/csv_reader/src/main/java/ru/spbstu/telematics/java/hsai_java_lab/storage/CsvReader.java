package ru.spbstu.telematics.java.hsai_java_lab.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueSample;

public class CsvReader {
    private File cvsFile;
    private ArrayList<RandomValueSample> samples;

    private static final Logger logger = LoggerFactory.getLogger(CsvReader.class);

    /**
     * Creates new instance of the CVS reader class.
     * By this time CVS file must exist and contain correct data.
     * 
     * @param path Path to CVS file with random values samples
     * @throws NullPointerException if {@code path} is {@code null}
     * @throws FileNotFoundException if method failed to find or open CSV file
     */
    public CsvReader(String path) throws FileNotFoundException {
        if (path == null) {
            logger.error("File path is null");
            throw new NullPointerException("File path is null");
        }

        cvsFile = new File(path);

        if (!cvsFile.exists()) {
            logger.error("CSV file not found");
            throw new FileNotFoundException("File " + path + " not found");
        }
        logger.info("CSV file configured");
    }

    /**
     * Reads the data from CVS file and returns an array of random value samples
     * 
     * @return array of random value samples
     * @throws IllegalArgumentException if CSV data is incorrect
     * @throws NumberFormatException if method failed to convert CSV data to double
     * @throws FileNotFoundException if method failed to find or open CSV file
     * @throws IOException if method failed to read CSV file
     */
    public ArrayList<RandomValueSample> readSamples() throws IOException {
        /* Read CSV header */
        BufferedReader finReader;
        try {
            finReader = new BufferedReader(new FileReader(cvsFile));
        }
        catch (FileNotFoundException e) {
            logger.error("CSV file not found");
            throw e;
        }

        String[] valueNames;

        try {
            String valueNamesString = finReader.readLine();
            
            if ((valueNamesString == null) || (valueNamesString.isEmpty())) {
                finReader.close();
                logger.error("CSV header is incorrect");
                throw new IllegalArgumentException("CSV data is incorrect");
            }

            valueNames = valueNamesString.split(",");
        }
        catch (IOException e) {
            logger.error("Failed to read CSV file: " + e.getMessage());
            throw e;
        }
        logger.info("CSV header is pulled from file");

        /* Create array of samples */
        int valuesNumber = valueNames.length;
        samples = new ArrayList<RandomValueSample>(valuesNumber);

        for (int i = 0; i < valuesNumber; i++) {
            samples.add(new RandomValueSample(valueNames[i]));
        }

        /* Populate samples with values */
        try {
            String valuesRow;

            while ((valuesRow = finReader.readLine()) != null) {
                String[] valuesString = valuesRow.split(",");

                if (valuesString.length != valuesNumber) {
                    finReader.close();
                    logger.error("CSV data is incorrect: Inconsistent number of columns");
                    throw new IllegalArgumentException("CSV data is incorrect: Inconsistent number of columns");
                }

                for (int i = 0; i < valuesNumber; i++) {
                    samples.get(i).addValue(Double.valueOf(valuesString[i]));
                }
            }
        }
        catch (IOException e) {
            finReader.close();
            logger.error("Failed to read CSV file: " + e.getMessage());
            throw e;
        }
        catch (NumberFormatException e) {
            finReader.close();
            logger.error("Incorrect format of data");
            throw e;
        }

        finReader.close();
        logger.info("Data is pulled from CSV file");
        
        return samples;
    }
}
