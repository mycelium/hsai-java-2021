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

import ru.spbstu.telematics.java.hsai_java_lab.value.RandomValue;

public class DatabaseStorage implements Storage {
    private static Connection connection;
    private static Statement statement;

    private final String sqlTableName = "random_values_table";
    
    @Override
    public String saveTable(ArrayList<RandomValue> table, String name, int rowNumber)
        throws NullPointerException, Exception
    {
        if (table == null) {
            throw new NullPointerException("Table is null");
        }

        if (rowNumber < 0) {
            throw new Exception("Table row number is < 0");
        }
        
        String tableName = (name == null) ? "RandomValueTable" : name;
        int columnNumber = table.size();

        /* Get DB path from property file */
        String filePath;

        try (InputStream istream = new FileInputStream("src/main/resources/storage.properties")) {
            Properties storageProp = new Properties();
            storageProp.load(istream);
            filePath = storageProp.getProperty("db.folder") + "/" + tableName + ".db";
        }
        catch (IOException e) {
            throw e;
        }

        /* Connect to the DB */
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
        } catch (ClassNotFoundException e) {
            throw e;
        }
        catch (SQLException e) {
            throw e;
        }

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
            throw e;
        }
        
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

                    sqlInsert.append(" ('" + name + "'," + value + "),");
                }
            }

            sqlInsert.deleteCharAt(sqlInsert.length() - 1);
            sqlInsert.append(";");
            statement.execute(sqlInsert.toString());
            statement.close();
        }
        catch (SQLException e) {
            throw e;
        }

        File fout = new File(filePath);

        return fout.getAbsolutePath();
    }
}
