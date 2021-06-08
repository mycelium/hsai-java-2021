package ru.spbstu.telematics.java.hsai_java_lab.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.telematics.java.hsai_java_lab.statistics.SampleNormality;
import ru.spbstu.telematics.java.hsai_java_lab.statistics.SampleStatistics;
import ru.spbstu.telematics.java.hsai_java_lab.statistics.data.SampleStatisticsData;
import ru.spbstu.telematics.java.hsai_java_lab.statistics.presentation.SamplePlot;
import ru.spbstu.telematics.java.hsai_java_lab.statistics.presentation.SampleStatisticsPresentation;
import ru.spbstu.telematics.java.hsai_java_lab.storage.CsvReader;
import ru.spbstu.telematics.java.hsai_java_lab.storage.DatabaseReader;
import ru.spbstu.telematics.java.hsai_java_lab.storage.Storage.StorageType;
import ru.spbstu.telematics.java.hsai_java_lab.storage.StorageException;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueSample;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueTable;

public class MainBusinessProcess {
    private RandomValueTable table;
    private String storageFilePath;
    private ArrayList<RandomValueSample> samples;
    private ArrayList<SampleStatisticsData> samplesStatistics;
    private ArrayList<SampleStatisticsData> samplesNormality;

    private static final Logger logger = LoggerFactory.getLogger(MainBusinessProcess.class);

    public MainBusinessProcess(RandomValueTable table) {
        if (table == null) {
            logger.error("Table is null");
            throw new NullPointerException("Table is null");
        }

        this.table = table;
        logger.info("Main process is ready to execute");
    }

    public int runProcess() {
        /* Save table to the storage file */
        try {
            storageFilePath = table.save();
        }
        catch (StorageException e) {
            StorageType type = e.getStorageType();
            logger.error("Failed to store table");
            return 1;
        }
        logger.info("Table is stores successfully");

        /* Read random values samples from storage file */
        try {
            switch (table.getStorageType()) {
                case CSV:
                    CsvReader csvReader = new CsvReader(storageFilePath);
                    samples = csvReader.readSamples();
                    break;
                case DATABASE:
                    DatabaseReader dbReader = new DatabaseReader(storageFilePath);
                    samples = dbReader.readSamples();
                    break;
                default:
                    break;
            }
        }
        catch (FileNotFoundException e) {
            logger.error("Failed to pull samples from file: " + e.getMessage());
            return 1;
        }
        catch (IOException e) {
            logger.error("Failed to pull samples from file: " + e.getMessage());
            return 1;
        }
        catch (NumberFormatException e) {
            logger.error("Failed to pull samples from file: Invalid number format" + e.getMessage());
            return 1;
        }
        catch (IllegalArgumentException e) {
            logger.error("Pulling samples from DB failed");
            return 1;
        }
        catch (SQLException e) {
            logger.error("Pulling samples from DB failed");
            return 1;
        }
        logger.info("Samples pulled from storage successfully");

        /* Calculate samples statistics */
        samplesStatistics = SampleStatistics.getStatistics(samples);
        SampleNormality normality = new SampleNormality(0.05);
        samplesNormality = normality.getStatistics(samples);

        /* Merge sample statistics from modules */
        for (SampleStatisticsData s1 : samplesStatistics) {
            for (SampleStatisticsData s2 : samplesNormality) {
                if (s1.getName().equals(s2.getName())) {
                    s1.addAllStatistics(s2.getStatistics());
                    break;
                }
            }
        }
        logger.info("Samples statistics calculated successfully");

        /* Write samples statistics to the JSON */
        SampleStatisticsPresentation presentation = new SampleStatisticsPresentation(samplesStatistics);
        try {
            presentation.writeToJson(table.getName());
        }
        catch (IOException e) {
            logger.error("Failed to write statistics to JSON file");
            return 1;
        }
        logger.info("Samples statistics are written to JSON file successfully");
        
        /* Plot the samples data */
        SamplePlot plot = new SamplePlot(samples);
        plot.plotSamples();
        logger.info("Samples plotted");

        logger.info("Main process finished");
        return 0;
    }
}
