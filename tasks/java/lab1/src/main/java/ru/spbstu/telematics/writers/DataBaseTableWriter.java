package ru.spbstu.telematics.writers;

import ru.spbstu.telematics.table.Table;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DataBaseTableWriter extends FileTableWriter {

    @Override
    public void write(Table table, String filePath) throws IOException, SQLException {
        Path out = createFileWithExtension(filePath, ".db");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + out.toAbsolutePath().toString());
        Statement statement = connection.createStatement();
        String creation = generateCreationStatement(table.getTableName(), table.getSamplesName());
        statement.execute(creation);
        String insertion = generateInsertionStatement(table.getTableName(), table.getSamplesName(), table.getTableValues());
        statement.executeUpdate(insertion);
    }

    private String generateCreationStatement(String tableName, String[] columnsName) {
        String statement = "CREATE TABLE " + tableName + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT";
        for (int i = 0; i < columnsName.length; i++) {
            statement += ", " + columnsName[i] + "_" + i + " REAL NOT NULL";
        }
        return statement += ");";
    }

    private String generateInsertionStatement(String tableName, String[] columnsName, List<String[]> tableValues) {
        StringBuilder statement = new StringBuilder("INSERT INTO " + tableName + " (");
        for (int i = 0; i < columnsName.length; i++) {
            statement.append(columnsName[i]).append("_").append(i).append(",");
        }
        statement.setLength(statement.length() - 1);
        statement.append(") VALUES \n");
        for (String[] line : tableValues) {
            statement.append("(");
            String prefix = "";
            for (String elem : line) {
                statement.append(prefix);
                prefix = ",";
                statement.append(elem);
            }
            statement.append("),");
        }
        statement.setLength(statement.length() - 1);
        statement.append(";");
        return statement.toString();
    }

}
