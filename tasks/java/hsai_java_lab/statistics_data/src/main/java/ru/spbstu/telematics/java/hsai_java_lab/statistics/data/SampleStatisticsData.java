package ru.spbstu.telematics.java.hsai_java_lab.statistics.data;

import java.util.HashMap;
import java.util.Map;

public class SampleStatisticsData {
    private String name;
    private HashMap<String, Object> data;

    public SampleStatisticsData(String name, Map<String, Object> data) {
        if ((name == null) || (data == null)) {
            throw new NullPointerException();
        }

        this.name = name;
        this.data = new HashMap<String, Object>(data);
    }

    public SampleStatisticsData(String name) {
        if (name == null) {
            throw new NullPointerException();
        }

        this.name = name;
        this.data = new HashMap<String, Object>();
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
