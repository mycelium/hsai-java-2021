package hsai_java_2021.random.storage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import hsai_java_2021.random.RandomValue;

public class DatabaseStorage implements Storage {
    private static Connection connection;
    private static Statement statement;

    private final String tableName = "random_values_table";
    private final String fileName = "random_values.db";
    
    @Override
    public String saveTable(ArrayList<RandomValue> table, int rowNumber) {
        if (table == null) {
            System.err.println("Table is null");
            return null;
        }

        if (rowNumber < 0) {
            System.err.println("Table row number is < 0");
            return null;
        }
        
        int columnNumber = table.size();

        /* Get DB path from property file */
        String filePath = null;

        try (InputStream istream = new FileInputStream("src/main/resources/storage.properties")) {
            Properties storageProp = new Properties();
            storageProp.load(istream);
            filePath = storageProp.getProperty("db.folder") + "/" + fileName;
        }
        catch (IOException e) {
            System.err.println("Failed to open Storage Properties file");
            e.printStackTrace();
            return null;
        }

        /* Connect to the DB */
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
        catch (SQLException e) {
            System.err.println("Failed to connect to DB");
            return null;
        }

        /* Create table in the DB */
        try {
            statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + tableName);
            statement.execute(
                "CREATE TABLE " + tableName + "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "value REAL NOT NULL" +
                ")"
            );
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        
        /* Populate DB with data */
        try {
            StringBuilder sqlInsert = new StringBuilder("INSERT INTO '" + tableName + "' ('name', 'value') VALUES ");

            for (int i = 0; i < columnNumber; i++) {
                for (int j = 0; j < rowNumber; j++) {
                    RandomValue randomValue = table.get(i);
                    String name = randomValue.getName();
                    if (name == null) {
                        name = "Distribution" + Integer.toString(i);
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
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

        return filePath;
    }
}