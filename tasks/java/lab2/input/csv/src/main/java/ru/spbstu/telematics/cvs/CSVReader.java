package ru.spbstu.telematics.cvs;

import ru.spbstu.telematics.reader.TableReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CSVReader implements TableReader {

    private Path file;

    public CSVReader(String file) {
        this.file = Path.of(file);
    }

    @Override
    public List<List<Double>> readAllDistribution() throws IOException {
        BufferedReader inputStream = Files.newBufferedReader(file);
        List<List<Double>> data = new ArrayList<>();
        List<Double> line;
        while ((line = readLine(inputStream)) != null) {
            for (int i = data.size(); i < line.size(); i++) {
                data.add(new ArrayList<>());
            }
            for (int i = 0; i < line.size(); i++) {
                data.get(i).add(line.get(i));
            }
        }
        inputStream.close();
        return data;
    }

    private List<Double> readLine(BufferedReader inputStream) throws IOException {
        String str = inputStream.readLine();
        if (str == null) {
            return null;
        }
        return Arrays.stream(str.split(","))
                .mapToDouble(Double::parseDouble)
                .boxed()
                .collect(Collectors.toList());
    }
}
