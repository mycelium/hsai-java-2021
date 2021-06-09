package ru.spbstu.telematics.reader;

import java.io.IOException;
import java.util.List;

public interface TableReader {

    public List<List<Double>> readAllDistribution() throws Exception;

}
