package ru.spbstu.telematics.table;

import ru.spbstu.telematics.samples.AutoSample;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<AutoSample<?>> table;
    private String name;

    public Table(String name) {
        this.name = name;
        this.table = new ArrayList<>();
    }

    public Table add(AutoSample<?> sample) {
        addSizeCheck(sample);
        table.add(sample);
        return this;
    }

    public Table generate(int numberOfValues) {
        for (AutoSample<?> sample : table) {
            sample.generate(numberOfValues);
        }
        return this;
    }

    /**
     * @return Лист строк, где каждая строка это набор случайных значений, по 1-му из каждого распределения
     */
    public List<String[]> getTableValues() {
        List<String[]> lines = new ArrayList<>(getNumberOfValues());
        for (int i = 0; i < getNumberOfValues(); i++) {
            String[] line = new String[table.size()];
            for (int j = 0; j < table.size(); j++) {
                line[j] = table.get(j).get(i).toString();
            }
            lines.set(i, line);
        }
        return lines;
    }

    public String[] getSamplesName() {
        return (String[]) table.stream()
                .map(AutoSample::getName)
                .toArray();
    }

    public String getTableName() {
        return name;
    }

    public Integer getNumberOfValues() {
        var founded = table.stream().findAny();
        if (founded.isPresent()) {
            return founded.get().size();
        }
        return null;
    }

    private void addSizeCheck(AutoSample<?> addedSample) {
        if (getNumberOfValues() != null && getNumberOfValues() != addedSample.size()) {
            throw new IllegalArgumentException("All samples must have same size for table");
        }
    }
}
