package ru.spbstu.telematics.db;

import ru.spbstu.telematics.reader.TableReader;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReader implements TableReader {

    private Path file;

    public DBReader(String file) {
        this.file = Path.of(file);
    }

    @Override
    public List<List<Double>> readAllDistribution() throws IOException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + file.toAbsolutePath().toString());
        Statement statement = connection.createStatement();
        var rs = statement.executeQuery("SELECT * FROM Table1");
        int columns = rs.getMetaData().getColumnCount();
        List<List<Double>> result = new ArrayList<>(columns - 1);
        for (int i = 0; i < columns - 1; i++) {
            result.add(new ArrayList<>());
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
