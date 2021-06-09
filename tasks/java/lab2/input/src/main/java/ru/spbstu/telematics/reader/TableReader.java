package ru.spbstu.telematics.reader;

import ru.spbstu.telematics.list.Variable;

import java.util.List;

public interface TableReader {

    public List<Variable<Double>> readAllDistribution() throws Exception;

}
