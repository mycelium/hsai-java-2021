package ru.spbstu.telematics.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.telematics.java.utils.Sample;

import java.sql.*;
import java.util.ArrayList;

public class DBReader {
    private final String fileName;
    private final ArrayList<Sample> variables;

    private Logger logger = LoggerFactory.getLogger(DBReader.class);

    /**
     * Reads CSV data from specified file. Data is stored as {@link Sample} list.
     *
     * @param fileName path to file with CSV data
     * @param tableName name of the DB table that stores data (equals to {@link ru.spbstu.telematics.java.data.RandomData}'s name)
     * */
    public DBReader(String fileName, String tableName) throws ClassNotFoundException, SQLException{
        this.fileName = fileName;
        logger.info("Opening file " + fileName + " ...");
        try {
            Class.forName("org.sqlite.JDBC");
            logger.info("Establishing connection...");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
            logger.info("Connection has been successfully established");
            Statement statement = connection.createStatement();
            ResultSet header = statement.executeQuery("SELECT DISTINCT Variable_name FROM " + tableName + ";");
            ArrayList<String> names = new ArrayList<>();
            while(header.next()) {
                names.add(header.getString("Variable_name"));
            }
            variables = new ArrayList<>(names.size());
            for(int i = 0; i < names.size(); i++) {
                ResultSet values = statement.executeQuery("SELECT Value FROM " + tableName +
                                                             " WHERE Variable_name = '" + names.get(i) + "';");
                ArrayList<Double> valuesList = new ArrayList<>();
                while(values.next()) {
                    valuesList.add(values.getDouble("Value"));
                }
                variables.add(new Sample(names.get(i), valuesList));
            }
            statement.close();
            connection.close();
            logger.info("File " + fileName + " has been read successfully");
        } catch (ClassNotFoundException | SQLException e) {
            logger.error(e.getMessage());
            throw e;
        }

    }

    public String getFileName() {
        return fileName;
    }

    public ArrayList<Sample> getVariables() {
        return variables;
    }
}
