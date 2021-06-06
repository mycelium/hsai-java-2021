package ru.spbstu.telematics.normalchecking;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import ru.spbstu.telematics.normalcheking.NormalChecker;
import ru.spbstu.telematics.utils.TestSelections;
/**
 * Unit test for simple App.
 */
public class NormalCheckerTest {
    @Test
    public void testNormalChecking()
    {
        assertTrue(NormalChecker.isNormal(TestSelections.normal));
        assertFalse(NormalChecker.isNormal(TestSelections.uniform));
        assertFalse(NormalChecker.isNormal(TestSelections.poisson));
    }
}
