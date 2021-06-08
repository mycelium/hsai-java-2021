package ru.spbstu.telematics.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONOutput {
    private final CharsContainer data;
    private final String name;
    private Logger logger = LoggerFactory.getLogger(JSONOutput.class);

    public JSONOutput(CharsContainer data, String name) {
        this.data = data;
        this.name = name;
    }

    private String constructTableRow(String name, HashMap<String, Double> variable) {
        logger.info("Making row for variable " + name + " ...");
        StringBuilder sb = new StringBuilder("{\"name\":\"").
            append(name).append("\",").
            append("\"mean\":").append(variable.get("Mean")).append(",").
            append("\"median\":").append(variable.get("Median")).append(",").
            append("\"min\":").append(variable.get("Min")).append(",").
            append("\"max\":").append(variable.get("Max")).append("}");
        return sb.toString();
    }

    /**
     * Writes data to a file with a specified name.
     *
     * @return path to a newly generated file.
     * @throws IOException If an I/O error occurred
     * @throws SecurityException If a security manager exists and its {@linkSecurityManager}.checkWrite(String) method denies write access to the file
     * */
    public String writeToFile(String fileName) throws IOException {
        if(!fileName.endsWith(".json"))
            fileName += ".json";
        String filePath = new File("").getAbsolutePath() + "/Output/";
        logger.info("Creating directory " + filePath + " ...");
        File directory = new File(filePath);
        directory.mkdir();
        logger.info("Creating file " + fileName + " ...");
        File file = new File(filePath + fileName);
        FileWriter writer = null;
        try {
            file.createNewFile();
            writer = new FileWriter(file);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw e;
        }
        logger.info("Starting writing to file...");
        writer.write("{\"" + name +"\": [\n");

        HashMap<String, HashMap<String, Double>> variables = data.getData();
        ArrayList<String> names = data.getNames();
        int i = 0;
        for(String name: names) {
            writer.write(constructTableRow(name,variables.get(name)));
            if(i < names.size() - 1)
                writer.write(",");
            writer.write("\n");
            i++;
        }
        writer.write("]}");
        writer.flush();
        writer.close();
        logger.info("Data was successfully written");
        return filePath + fileName;
    }
}
