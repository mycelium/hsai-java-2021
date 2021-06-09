package ru.spbstu.telematics.db;

import ru.spbstu.telematics.list.Variable;
import ru.spbstu.telematics.reader.TableReader;

import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReader implements TableReader {

    private Path file;
    private String table;

    public DBReader(String file, String table) {
        this.file = Path.of(file);
        this.table = table;
    }

    @Override
    public List<Variable<Double>> readAllDistribution() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + file.toAbsolutePath().toString());
        Statement statement = connection.createStatement();
        var rs = statement.executeQuery("SELECT * FROM " + table);
        var metaData = rs.getMetaData();
        int columns = metaData.getColumnCount();
        List<Variable<Double>> result = new ArrayList<>(columns - 1);
        for (int i = 2; i <= columns; i++) {
            result.add(new Variable<>(metaData.getColumnName(i)));
        }
        while(rs.next()){
            for (int i = 2; i <= columns; i++){
                result.get(i - 2).add(rs.getDouble(i));
            }
        }
        connection.close();
        return result;
    }
}
