package ru.spbstu.telematics.java.hsai_java_lab.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValueSample;

public class DatabaseReader {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet result;
    private final String sqlTableName = "random_values_table";

    private File dbFile;
    private ArrayList<RandomValueSample> samples;

    private static final Logger logger = LoggerFactory.getLogger(DatabaseReader.class);

    /**
     * Creates new instance of the database reader class.
     * By this time database must exist and contain correct data.
     * 
     * @param path Path to database file with random values samples
     * @throws NullPointerException if {@code path} is {@code null}
     * @throws FileNotFoundException if method failed to find or open database file
     */
    public DatabaseReader(String path) throws FileNotFoundException {
        if (path == null) {
            logger.error("DB file is null");
            throw new NullPointerException("File path is null");
        }

        dbFile = new File(path);

        if (!dbFile.exists()) {
            logger.error("DB file not found");
            throw new FileNotFoundException("File " + path + " not found");
        }
        logger.info("DB file configured");
    }

    /**
     * Reads the data from CVS file and returns an array of random value samples
     * 
     * @return array of random value samples
     * @throws IllegalArgumentException if CSV data is incorrect
     * @throws NumberFormatException if method failed to convert CSV data to double
     * @throws SQLException if database operation failed
     */
    public ArrayList<RandomValueSample> readSamples() throws SQLException{
        /* Connect to the DB */
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
            statement = connection.createStatement();
        }
        catch (SQLException e) {
            logger.error("Failed to connect to DB");
            throw e;
        }
        logger.info("Connection to DB established");

        /* Collect information about values' names */
        try {
            result = statement.executeQuery("SELECT DISTINCT name " + 
                                            "FROM " + sqlTableName + ";");
        }
        catch (SQLException e) {
            logger.error("Failed to execute DB SELECT");
            throw e;
        }

        ArrayList<String> valueNames = new ArrayList<String>();
        while (result.next()) {
            valueNames.add(result.getString("name"));
        }

        if (valueNames.isEmpty()) {
            logger.error("Database table is empty");
            throw new IllegalArgumentException("Database table is empty");
        }

        /* Create array of samples */
        int valuesNumber = valueNames.size();
        samples = new ArrayList<RandomValueSample>(valuesNumber);

        for (int i = 0; i < valuesNumber; i++) {
            samples.add(new RandomValueSample(valueNames.get(i)));
        }

        /* Populate samples with values */
        for (int i = 0; i < valuesNumber; i++) {
            String valueName = valueNames.get(i);

            try {
                result = statement.executeQuery(" SELECT value" + 
                                                " FROM " + sqlTableName + 
                                                " WHERE name = '" + valueName + "';");
            }
            catch (SQLException e) {
                logger.error("Failed to execute DB SELECT");
                throw e;
            }

            while (result.next()) {
                samples.get(i).addValue(result.getDouble("value"));
            }
        }
        logger.info("Table is pulled from DB");

        result.close();
        statement.close();
        connection.close();
        logger.info("Connection to DB is closed");

        return samples;
    }
}
