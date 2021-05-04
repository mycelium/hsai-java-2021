package ru.spbstu.telematics.lab1.saving;

import org.junit.Test;
import ru.spbstu.telematics.lab1.RandomData;
import ru.spbstu.telematics.lab1.RandomDataProvider;

public class DBFSaverTest {
    @Test
    public void testDoNotCrash() {
        RandomData data = RandomDataProvider.buildData();
        String filename = Saving.buildSaver(SavingType.Database).save(data, 50);
        System.out.println(filename);
    }
}
