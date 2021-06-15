package ru.spbstu.telematics;

import java.io.*;
import java.util.ArrayList;

import java.sql.*;
import java.util.Objects;
import java.util.stream.Collectors;

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
            writer.write(arrayList.stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(",")));
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
        Statement statement;
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
            sb.append(arrayList.stream()
                    .map(s -> "(" + s + ")")
                    .collect(Collectors.joining(",")));
            statement.executeUpdate(sb.append(";").toString());
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
