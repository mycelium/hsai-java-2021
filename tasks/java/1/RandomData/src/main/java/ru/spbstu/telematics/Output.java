package ru.spbstu.telematics;

import java.io.*;
import java.util.ArrayList;

import java.sql.*;

public class Output {
    File file;
    String fileName;
    String filePath;
    ArrayList<Double> arrayList;

    public Output(String fileName, ArrayList<Double> arrayList) {
        this.fileName = fileName;
        this.arrayList = arrayList;
    }

    public String OutputCSV() {
        filePath = "./Output/" + fileName + ".csv";
        file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            int i = 0;
            for (; i < arrayList.size() - 1; i++) {
                writer.write(arrayList.get(i) + ",");
            }
            writer.write(String.valueOf(arrayList.get(i)));
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        } catch (IOException e) {
            throw new RuntimeException("IOException found.");
        }
        return filePath;
    }

    public String OutputDBF() {
        Connection connection = null;
        Statement statement = null;
        String sql;
        filePath = "./Output/" + fileName + ".db";
        // connect sqlite
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + filePath);
        } catch (Exception e) {
            System.out.println("Database open failed.");
            System.exit(0);
        }
        try {
            statement = connection.createStatement();
            statement.executeUpdate("drop table if exists " + "DATA");
            sql = "CREATE TABLE DATA (" +
                    "ID     INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "VALUE  REAL            NOT NULL)";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Crate table failed.");
        }
        try {
            statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("INSERT INTO DATA(VALUE) VALUES");
            for (int i = 0; i < arrayList.size(); i++) {
                if (i < arrayList.size() - 1) {
                    sb.append("(").append(arrayList.get(i)).append("),");
                } else {
                    sb.append("(").append(arrayList.get(i)).append(");");
                }
            }
            statement.executeUpdate(sb.toString());
            statement.close();
        } catch (Exception e) {
            System.out.println("Insert data failed.");
        }
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("Database close failed.");
        }
        return filePath;
    }
}
