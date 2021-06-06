package ru.spbstu.telematics.table;

import ru.spbstu.telematics.distributions.Distributed;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<Distributed<?>> table;
    private String name;
    private int numberOfLines;

    public Table(String name, int size) {
        this(name, new ArrayList<>(), size);
    }

    public Table(String name, List<Distributed<?>> distributions, int numberOfLines) {
        this.name = name;
        this.table = distributions;
        this.numberOfLines = numberOfLines;
    }

    public void add(Distributed<?> distributed) {
        table.add(distributed);
    }

    public int setSize(int newNumberOfLines) {
        if (newNumberOfLines < 0) {
            throw new IllegalArgumentException();
        }
        int oldNumberOfLines = numberOfLines;
        numberOfLines = newNumberOfLines;
        return oldNumberOfLines;
    }
}
