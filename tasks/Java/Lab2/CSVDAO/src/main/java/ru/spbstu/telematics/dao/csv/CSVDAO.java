package ru.spbstu.telematics.dao.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.spbstu.telematics.dao.DAO;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class CSVDAO implements DAO{
    Logger logger = LogManager.getLogger(CSVDAO.class);

    private final String filename;
    String[] names;
    double[][] rows;

    public CSVDAO(String filename) throws IOException {
        this.filename = filename;
        logger.info("Opening " + filename + "...");
        try {
            CSVParser parser = CSVParser.parse(new File(filename), Charset.defaultCharset(), CSVFormat.DEFAULT);
            names = parser.getHeaderNames().toArray(new String[0]);
            rows = new double[(int) parser.getRecordNumber()][names.length];
            int i = 0;
            for(CSVRecord record: parser.getRecords()) {
                for (int j = 0; j < record.size(); j++) {
                    rows[i][j] = Double.parseDouble(record.get(j));
                }
                i += 1;
            }
        } catch (IOException e) {
            logger.error("Error with parsing " + filename + ":\n" + e.getMessage());
            throw e;
        }
    }

    @Override
    public double[] getColumn(int i) {
        double[] res = new double[rows.length];
        for (int j = 0; j < res.length; j++) {
            res[j] = rows[j][i];
        }
        return res;
    }

    @Override
    public String[] getNames() {
        return names;
    }

    @Override
    public double[][] getAll() {
        return rows;
    }

    @Override
    public String getTitle() {
        return filename;
    }
}
