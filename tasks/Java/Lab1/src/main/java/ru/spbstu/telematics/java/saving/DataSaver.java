package ru.spbstu.telematics.java.saving;

import ru.spbstu.telematics.java.data.RandomData;
import ru.spbstu.telematics.java.data.Variable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataSaver {
    public static String saveCSV(RandomData data, int size, String fileName) {
        String filePath = "./Output/" + fileName + ".csv";
        File file = new File(filePath);
        ArrayList<String> header = data.getVariablesNames();
        try {
            if(header.size() > 0) {
                if(!file.exists()) {
                    file.createNewFile();
                }
                FileWriter writer = new FileWriter(file);
                for (int i = 0; i < header.size() - 1; i++) {
                    writer.write(header.get(i) + ",");
                }
                writer.write(header.get(header.size() - 1) + "\n");
                ArrayList<ArrayList<?>> values = new ArrayList<>();
                for(Variable v: data.getVariables()) {
                    values.add(v.generateSample(size));
                }
                for(int i = 0; i < size; i++) {
                    for(int j = 0; j < header.size() - 1; j++) {
                        writer.write(values.get(j).get(i) + ",");
                    }
                    writer.write(values.get(header.size() - 1).get(i) + "\n");
                }
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
