package ru.spbstu.telematics.writers;

import ru.spbstu.telematics.table.Table;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DataBaseTableWriter extends FileTableWriter {

    public DataBaseTableWriter(String file) throws IOException {
        super(file);
        createFileWithExtension(file, ".db");
    }

    @Override
    public void write(Table table) throws IOException, SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + outPath.toAbsolutePath().toString());
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS " + table.getTableName());
        String creation = generateCreationStatement(table.getTableName(), table.getSamplesName());
        statement.execute(creation);
        String insertion = generateInsertionStatement(table.getTableName(), table.getSamplesName(), table.getTableValues());
        statement.executeUpdate(insertion);
    }

    private String generateCreationStatement(String tableName, List<String> columnsName) {
        String statement = "CREATE TABLE " + tableName + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT";
        for (int i = 0; i < columnsName.size(); i++) {
            statement += ", " + columnsName.get(i) + "_" + i + " REAL NOT NULL";
        }
        statement += ");";
        return statement;
    }

    private String generateInsertionStatement(String tableName, List<String> columnsName, List<List<Object>> tableValues) {
        StringBuilder statement = new StringBuilder("INSERT INTO " + tableName + " (");
        for (int i = 0; i < columnsName.size(); i++) {
            statement.append(columnsName.get(i)).append("_").append(i).append(",");
        }
        statement.setLength(statement.length() - 1);
        statement.append(") VALUES \n");
        for (List<Object> line : tableValues) {
            statement.append("(");
            String prefix = "";
            for (Object elem : line) {
                statement.append(prefix);
                prefix = ",";
                statement.append(elem.toString());
            }
            statement.append("),");
        }
        statement.setLength(statement.length() - 1);
        statement.append(";");
        return statement.toString();
    }

}
