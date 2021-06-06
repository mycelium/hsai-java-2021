package ru.spbstu.telematics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import ru.spbstu.telematics.utils.TestSelections;

public class CharacteristicsTest
{
    @Test
    public void testCharacteristics()
    {
        double[][] arrays = {TestSelections.normal, TestSelections.poisson, TestSelections.uniform};
        double[][] chars = {TestSelections.normal_chars, TestSelections.poisson_chars, TestSelections.uniform_chars};
        for (int i = 0; i < 3; i++) {
            Characteristics t = new Characteristics(arrays[i]);
            double[] ch = t.allChars("mvdnx");
            for (int j = 0; j < ch.length; j++) {
                assertEquals(chars[i][j], ch[j], 0.01);
            }
        }
    }
}
