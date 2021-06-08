package ru.spbstu.telematics.java;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.spbstu.telematics.java.utils.Sample;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class CSVReader {
    private final String fileName;
    private final ArrayList<Sample> variables;

    private Logger logger = LoggerFactory.getLogger(CSVReader.class);

    /**
     * Reads CSV data from specified file. Data is stored as {@link Sample} list.
     *
     * @param fileName path to file with CSV data.
     * @throws NullPointerException if the pathname argument is null
     * */
    public CSVReader(String fileName) throws IOException, NullPointerException{
        this.fileName = fileName;
        logger.info("Opening file " + fileName + " ...");
        try {
            File file = new File(fileName);
            CSVParser parser = CSVParser.parse(file, Charset.defaultCharset(), CSVFormat.DEFAULT);
            ArrayList<CSVRecord> records = (ArrayList<CSVRecord>) parser.getRecords();
            CSVRecord names = records.get(0);
            variables = new ArrayList<>(names.size());
            ArrayList<ArrayList<Double>> rows = new ArrayList<>();
            for(int i = 0; i < names.size(); i++) {
                rows.add(new ArrayList<>());
            }
            for(int i = 1; i < records.size(); i++) {
                CSVRecord record = records.get(i);
                for (int j = 0; j < record.size(); j++) {
                    rows.get(j).add(Double.parseDouble(record.get(j)));
                }
            }
            for(int j = 0; j < rows.size(); j++) {
                variables.add(new Sample(names.get(j), rows.get(j)));
            }
            logger.info("File " + fileName + " has been read successfully");
        } catch (IOException | NullPointerException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public ArrayList<Sample> getVariables() {
        return variables;
    }

    public String getFileName() {
        return fileName;
    }
}
