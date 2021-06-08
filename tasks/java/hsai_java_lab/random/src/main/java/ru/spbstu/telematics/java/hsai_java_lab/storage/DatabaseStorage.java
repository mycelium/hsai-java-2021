package ru.spbstu.telematics.java.hsai_java_lab.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValue;

public class DatabaseStorage implements Storage {
    private static Connection connection;
    private static Statement statement;

    private final String sqlTableName = "random_values_table";
    private final String propertyPath = "../resources/storage.properties";

    private static final Logger logger = LoggerFactory.getLogger(DatabaseStorage.class);
    
    @Override
    public String saveTable(ArrayList<RandomValue> table, String name, int rowNumber) throws StorageException {
        if (table == null) {
            logger.error("Random Value Table is null");
            throw new NullPointerException("Random Value Table is null");
        }

        if (rowNumber < 0) {
            logger.error("Table row number is less then 0");
            throw new IllegalArgumentException("Table row number is less then 0");
        }
        
        String tableName = (name == null) ? "RandomValueTable" : name;
        int columnNumber = table.size();

        /* Get DB path from property file */
        String filePath;

        try (InputStream istream = new FileInputStream(propertyPath)) {
            Properties storageProp = new Properties();
            storageProp.load(istream);
            filePath = storageProp.getProperty("db.folder") + "/" + tableName + ".db";
        }
        catch (IOException e) {
            logger.error("Failed to configure DB file path" + e.getMessage());
            throw new StorageException("Failed to open Storage Properties file: " + e.getMessage(), StorageType.DATABASE);
        }

        logger.info("CVS file path configured");

        /* Connect to the DB */
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
        }
        catch (SQLException e) {
            logger.error("Failed to connect to DB");
            throw new StorageException("Failed to connect do DB: " + e.getMessage(), StorageType.DATABASE);
        }
        logger.info("Connection to DB established");

        /* Create table in the DB */
        try {
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + sqlTableName);
            statement.execute(
                "CREATE TABLE " + sqlTableName + "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "value REAL NOT NULL" +
                ")"
            );
        }
        catch (SQLException e) {
            logger.error("Failed to create DB table");
            throw new StorageException("Failed to create table in DB: " + e.getMessage(), StorageType.DATABASE);
        }

        logger.info("DB table created");
        
        /* Populate DB with data */
        try {
            StringBuilder sqlInsert = new StringBuilder("INSERT INTO '" + sqlTableName + "' ('name', 'value') VALUES ");

            for (int i = 0; i < columnNumber; i++) {
                for (int j = 0; j < rowNumber; j++) {
                    RandomValue randomValue = table.get(i);
                    String valueName = randomValue.getName();
                    if (valueName == null) {
                        valueName = "Distribution" + Integer.toString(i);
                    }
                    String value = String.format("%10.5f", randomValue.generate()).replace(",", ".");

                    sqlInsert.append(" ('" + valueName + "'," + value + "),");
                }
            }

            sqlInsert.deleteCharAt(sqlInsert.length() - 1);
            sqlInsert.append(";");
            statement.execute(sqlInsert.toString());
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            logger.error("Failed to insert values into DB table");
            throw new StorageException("Failed to insert values into DB: " + e.getMessage(), StorageType.DATABASE);
        }

        filePath = new File(filePath).getAbsolutePath();
        logger.info("Table is stored to the DB file " + filePath);
        
        return filePath;
    }
}
