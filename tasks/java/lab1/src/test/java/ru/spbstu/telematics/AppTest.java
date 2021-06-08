package ru.spbstu.telematics;

import org.testng.annotations.Test;
import ru.spbstu.telematics.samples.AutoSamplesFactory;
import ru.spbstu.telematics.table.Table;
import ru.spbstu.telematics.writers.CSVTableWriter;

import java.io.IOException;

public class AppTest {

    String path = "src/test/resources/AppTest";

    @Test
    void SystemTest() throws IOException {
        Table table = new Table("Table1");
        table
                .add(AutoSamplesFactory.getNormal("Normal", 1., -10.))
                .add(AutoSamplesFactory.getPoisson("Poisson", 10))
                .add(AutoSamplesFactory.getUniform("Uniform", -50., -40.));
        table.generate(10);
        CSVTableWriter writer = new CSVTableWriter(path + "/out.csv");
        writer.write(table);
    }


}
