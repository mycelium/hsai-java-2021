package ru.spbstu.telematics;

import org.testng.annotations.Test;
import ru.spbstu.telematics.samples.AutoSamplesFactory;
import ru.spbstu.telematics.table.Table;
import ru.spbstu.telematics.writers.CSVTableWriter;
import ru.spbstu.telematics.writers.DataBaseTableWriter;

import java.io.IOException;
import java.sql.SQLException;

public class AppTest {

    final String DIR = "src/test/resources/AppTest";
    final String CSV_FILE = DIR + "/" + "outCSV.csv";
    final String DB_FILE = DIR + "/" + "outDB.db";

    @Test
    void SystemTestCSV() throws IOException {
        Table table = new Table("Table1");
        table
                .add(AutoSamplesFactory.getNormal("Normal", 1., -10.))
                .add(AutoSamplesFactory.getPoisson("Poisson", 10))
                .add(AutoSamplesFactory.getUniform("Uniform", -50., -40.));
        table.generate(30);
        CSVTableWriter writer = new CSVTableWriter(CSV_FILE);
        writer.write(table);
    }

    @Test
    void SystemTestDB() throws IOException, SQLException {
        Table table = new Table("Table1");
        table
                .add(AutoSamplesFactory.getNormal("Normal", 1., -10.))
                .add(AutoSamplesFactory.getPoisson("Poisson", 10))
                .add(AutoSamplesFactory.getUniform("Uniform", -50., -40.));
        table.generate(30);
        DataBaseTableWriter writer = new DataBaseTableWriter(DB_FILE);
        writer.write(table);
    }

}
