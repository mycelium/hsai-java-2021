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

import ru.spbstu.telematics.java.hsai_java_lab.statistics.data.SampleStatisticsData;

public class SampleStatisticsPresentation {
    private final String propertyPath = "../resources/presentation.properties";

    String filePath;
    ArrayList<SampleStatisticsData> statistics;
    
    public SampleStatisticsPresentation(ArrayList<SampleStatisticsData> statistics) {
        if (statistics == null) {
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
            //TODO Добавить логгер
        }
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
            throw e;
        } 

    }
}
