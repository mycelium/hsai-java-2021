package ru.spbstu.telematics.java.hsai_java_lab.statistics.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class SampleStatisticsData {
    private String name;
    private HashMap<String, Object> data;

    private static final Logger logger = LoggerFactory.getLogger(SampleStatisticsData.class);

    public SampleStatisticsData(String name, Map<String, Object> data) {
        if ((name == null) || (data == null)) {
            logger.error("Sample name or data is null");
            throw new NullPointerException();
        }

        this.name = name;
        this.data = new HashMap<String, Object>(data);
        logger.info("Statistics data of sample " + name + " created");
    }

    public SampleStatisticsData(String name) {
        if (name == null) {
            logger.error("Sample name is null");
            throw new NullPointerException();
        }

        this.name = name;
        this.data = new HashMap<String, Object>();
        logger.info("Empty statistics data of sample " + name + " created");
    }

    public void addStatistic(String key, Object value) {
        if (key == null) {
            throw new NullPointerException("Key is null");
        }

        Object checkedValue = (value == null) ? "" : value;

        data.put(key, checkedValue);
    }

    public void addAllStatistics(Map<String, Object> statistics) {
        if (statistics != null) {
            data.putAll(statistics);
        }
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Object> getStatistics() {
        return data;
    }
}
