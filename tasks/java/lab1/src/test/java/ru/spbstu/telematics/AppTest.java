package ru.spbstu.telematics;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.spbstu.telematics.samples.AutoSamplesFactory;
import ru.spbstu.telematics.table.Table;
import ru.spbstu.telematics.writers.CSVTableWriter;
import ru.spbstu.telematics.writers.DataBaseTableWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

public class AppTest {

    final String DIR = "src/test/resources/AppTest";
    final String CSV_FILE = DIR + "/" + "outCSV.csv";
    final String DB_FILE = DIR + "/" + "outDB.db";
    final Integer GENERATED = 30;

    @Test
    public void SystemTestCSV() throws IOException {
        Table table = new Table("Table1");
        table
                .add(AutoSamplesFactory.getNormal("Normal 1, -10", 1., -10.))
                .add(AutoSamplesFactory.getPoisson("Poisson 10", 10))
                .add(AutoSamplesFactory.getUniform("Uniform [-50; -40]", -50., -40.));
        table.generate(GENERATED);
        Assert.assertNotNull(table.getNumberOfValues());
        Assert.assertEquals(table.getNumberOfValues(), GENERATED);
        CSVTableWriter writer = new CSVTableWriter(CSV_FILE);
        writer.write(table);
        Assert.assertTrue(Files.isRegularFile(Path.of(CSV_FILE)));
        Assert.assertEquals(Files.readAllLines(Path.of(CSV_FILE)).size(), GENERATED + 1);
    }

    @Test
    public void SystemTestDB() throws IOException, SQLException {
        Table table = new Table("Table1");
        table
                .add(AutoSamplesFactory.getNormal("Normal", 1., -10.))
                .add(AutoSamplesFactory.getPoisson("Poisson", 10))
                .add(AutoSamplesFactory.getUniform("Uniform", -50., -40.));
        table.generate(GENERATED);
        Assert.assertNotNull(table.getNumberOfValues());
        Assert.assertEquals(table.getNumberOfValues(), GENERATED);
        DataBaseTableWriter writer = new DataBaseTableWriter(DB_FILE);
        writer.write(table);
        Assert.assertTrue(Files.isRegularFile(Path.of(DB_FILE)));
    }

}
