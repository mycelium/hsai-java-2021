package ru.spbstu.telematics.java.hsai_java_lab.statistics.presentation;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.telematics.java.hsai_java_lab.statistics.data.SampleStatisticsData;

public class SampleStatisticsPresentation {
    private final String propertyPath = "../resources/presentation.properties";

    String filePath;
    ArrayList<SampleStatisticsData> statistics;

    private static final Logger logger = LoggerFactory.getLogger(SampleStatisticsPresentation.class);
    
    public SampleStatisticsPresentation(ArrayList<SampleStatisticsData> statistics) {
        if (statistics == null) {
            logger.error("Statistics Data array is null");
            throw new NullPointerException("Statistics is null");
        }

        this.statistics = statistics;

        try (InputStream istream = new FileInputStream(propertyPath)) {
            Properties storageProp = new Properties();
            storageProp.load(istream);
            filePath = storageProp.getProperty("json.folder");
        }
        catch (IOException e) {
            filePath = ".";
            logger.warn("Failed to set JSON output folder. JSON output folder is set to default");
        }
        logger.info("JSON presentation is configured");
    }

    public void writeToJson(String fileName) throws IOException{
        if (fileName == null) {
            throw new NullPointerException("File name is null");
        }

        filePath = filePath + "/" + fileName + ".json";

        try (FileWriter foutWriter = new FileWriter(filePath)) {
            JSONArray samplesArray = new JSONArray();

            for (SampleStatisticsData s : statistics) {
                JSONObject sampleJson = new JSONObject();
                JSONObject sampleDataJson = new JSONObject();
                JSONObject sampleStatisticsJson = new JSONObject();
                HashMap<String, Object> sampleStatistics = s.getStatistics();

                for (String key : sampleStatistics.keySet()) {
                    sampleStatisticsJson.put(key, sampleStatistics.get(key));
                }

                sampleDataJson.put("name", s.getName());
                sampleDataJson.put("statistics", sampleStatisticsJson);

                sampleJson.put("randomValueSample", sampleDataJson);
                samplesArray.add(sampleJson);
            }
            
            foutWriter.write(samplesArray.toJSONString());
        }
        catch (IOException e) {
            logger.error("Failed to write sample statistics to the JSON file " + e.getMessage());
            throw e;
        }
        logger.info("Samples statistics data is writted to JSON file " + filePath);
    }
}
