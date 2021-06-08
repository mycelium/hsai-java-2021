package ru.spbstu.telematics.java.hsai_java_lab.business;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public MainBusinessProcess(RandomValueTable table) {
        if (table == null) {
            throw new NullPointerException("Table is null");
        }

        this.table = table;
    }

    public int runProcess() {
        /* Save table to the storage file */
        try {
            storageFilePath = table.save();
        }
        catch (StorageException e) {
            StorageType type = e.getStorageType();
            //TODO логгирование
            return 1;
        }

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
            //TODO логгирование
            return 1;
        }
        catch (IOException e) {
            //TODO логгирование
            return 1;
        }
        catch (NumberFormatException e) {
            //TODO логгирование
            return 1;
        }
        catch (IllegalArgumentException e) {
            //TODO логгирование
            return 1;
        }
        catch (SQLException e) {
            //TODO логгирование
            return 1;
        }

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

        /* Write samples statistics to the JSON */
        SampleStatisticsPresentation presentation = new SampleStatisticsPresentation(samplesStatistics);
        try {
            presentation.writeToJson(table.getName());
        }
        catch (IOException e) {
            //TODO логгирование
            return 1;
        }
        
        /* Plot the samples data */
        SamplePlot plot = new SamplePlot(samples);
        plot.plotSamples();

        return 0;
    }
}
