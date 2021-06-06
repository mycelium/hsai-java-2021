package ru.spbstu.telematics.dao.db;

import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class mysqlDAO implements DBDAO {
    Logger logger = LogManager.getLogger(mysqlDAO.class);

    String address = "localhost";
    String dataBase = null;
    String tableName = null;
    Connection conn = null;

    String[] names;
    double[][] rows;

    @Override
    public DBDAO setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public DBDAO setDatabase(String database) {
        this.dataBase = database;
        return this;
    }

    @Override
    public DBDAO setTable(String table) {
        this.tableName = table;
        return this;
    }

    @Override
    public DBDAO login(String username, String password) {
        try {
            logger.info("Connecting to MySQL...");
            conn = DriverManager.getConnection("jdbc:mysql://" + address + "/" + dataBase,
                    username, password);
            logger.info("Connected successfully!");
            Statement statement = conn.createStatement();
            statement.execute("DESCRIBE " + tableName);
            ResultSet set = statement.getResultSet();
            //TODO: слегка не доделал :)
        } catch (SQLException throwables) {
            logger.error("Something went wrong: " + throwables.getMessage());
        }
        return this;
    }

    @Override
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException throwables) {
            logger.error("Something went wrong: " + throwables.getMessage());
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
        return tableName;
    }
}
