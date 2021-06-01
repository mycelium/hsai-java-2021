package com.spbstu.db;

import com.spbstu.data.RowData;
import com.spbstu.data.TableData;

import java.sql.*;
import java.util.ArrayList;

public class DBReader implements com.spbstu.data.Reader {
    public DBReader(String filename) {
        this.filename = filename;
    }

    String filename;

    @Override
    public TableData read() {
        try {
            ArrayList<RowData> table = new ArrayList<>();
            String myDriver = "org.h2.Driver";
            String myUrl = filename;
            Class.forName(myDriver);
            try (Connection connection = DriverManager.getConnection(myUrl, "root", "")) {
                String query = "SELECT * FROM data";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    Double value = rs.getDouble("value");
                    ArrayList<Double> list = new ArrayList<>();
                    list.add(value);
                    RowData rowData = new RowData();
                    rowData.setRow(list);
                    table.add(rowData);
                }
                st.close();
            }
            ArrayList<String> nameList = new ArrayList<>();
            nameList.add("value");
            TableData tableData = new TableData(nameList, table);
            return tableData;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException();
        }

    }
}
