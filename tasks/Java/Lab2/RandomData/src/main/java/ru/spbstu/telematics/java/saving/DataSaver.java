package ru.spbstu.telematics.java.saving;

import ru.spbstu.telematics.java.data.RandomData;
import ru.spbstu.telematics.java.data.Variable;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DataSaver {

    private DataSaver() {}

    /**
     * Creates directory and file if not exists
     * @param fileName file name with an extension
     * @throws IOException if an I/O error occurred
     * @throws SecurityException If a security manager exists and its {@link SecurityManager}.checkWrite(String)}
     * method denies write access to the file
     * */
    private static File makeFile(String fileName)
            throws IOException, SecurityException {
        String filePath = new File("").getAbsolutePath() + "/Output/";
        File fileDirectory = new File(filePath);
        String filePathWithFileName = filePath + fileName;
        File file = new File(filePathWithFileName);
        fileDirectory.mkdir();
        file.createNewFile();
        return file;
    }

    /**
     * Writes one row of data in CSV format. The data is written to the file specified by writer.
     * */
    private static void writeCSVRow(FileWriter writer, ArrayList<?> data) throws IOException {
        for (int i = 0; i < data.size() - 1; i++) {
            writer.write(data.get(i) + ",");
        }
        writer.write(data.get(data.size() - 1) + "\r\n");
    }

    /**
     * Saves data into table in CSV format. Header of the table is data's variables names.
     * The table will contain size+1 rows (one header row).
     * @param data random data that may contain variables that should be saved
     * @param size amount of values for each variable
     * @param fileName name of .csv file (may be without extension)
     * @return path to a file
     * */
    public static String saveCSV(RandomData data, int size, String fileName)
            throws IOException, NullPointerException, IllegalArgumentException {
        if(size < 0)
            throw new IllegalArgumentException("Size of the sample cannot be a negative number");
        if (!fileName.endsWith(".csv"))
            fileName += ".csv";
        File file = makeFile(fileName);
        ArrayList<String> headers = data.getVariablesNames();
        if(headers.size() > 0 && size > 0) {
            FileWriter writer = new FileWriter(file);
            writeCSVRow(writer, headers);
            for(int i = 0; i < size; i++) {
                writeCSVRow(writer, data.getTableRow());
            }
            writer.flush();
            writer.close();
        }
    return file.getAbsolutePath();
    }

    /**
     * Saves data into table in Database. Header of the table is "id", "Variable_name" and "Value".
     * The table will contain (variables amount * size)+1 rows (one header row).
     * @param data random data that may contain variables that should be saved
     * @param size amount of values for each variable
     * @param fileName name of .db file (may be without extension)
     * @return path to a file
     * */
    public static String saveDB (RandomData data, int size, String fileName)
            throws ClassNotFoundException, IOException, SQLException,
                   NullPointerException, IllegalArgumentException {
        if(size < 0)
            throw new IllegalArgumentException("Size of the sample cannot be a negative number");
        if (!fileName.endsWith(".db"))
            fileName += ".db";
        File file = makeFile(fileName);
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS " + data.getName());
        String sql = "CREATE TABLE " + data.getName() + " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "Variable_name VARCHAR(50) NOT NULL," +
                        "Value REAL NOT NULL" +
                     ");";
        statement.executeUpdate(sql);
        if(!data.getVariables().isEmpty() && size > 0) {
            StringBuilder sb = new StringBuilder("INSERT INTO " + data.getName() + "(Variable_name, Value) VALUES");
            for (Variable v : data.getVariables()) {
                for (int i = 0; i < size; i++) {
                    sb.append("('" + v.getName() + "', " + v.generateValue() + "),");
                }
            }
            sb.replace(sb.length() - 1, sb.length(), ";");
            statement.executeUpdate(sb.toString());
            statement.close();
        }
        return file.getAbsolutePath();
    }
}
