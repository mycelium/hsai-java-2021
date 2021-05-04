package ru.spbstu.telematics.lab1.saving;

import org.junit.Test;
import ru.spbstu.telematics.lab1.RandomData;
import ru.spbstu.telematics.lab1.RandomDataProvider;
import ru.spbstu.telematics.lab1.Variable;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class CSVSaverTest {
    @Test
    public void testDoNotCrash() {
        RandomData data = RandomDataProvider.buildData();
        String filename = Saving.buildSaver(SavingType.CSV).save(data, 50);
        System.out.println(filename);
    }
}
