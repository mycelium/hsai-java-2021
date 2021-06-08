package hw1;

import hw1.distribution.NormalDistribution;
import hw1.distribution.PoissonDistribution;
import hw1.distribution.UniformDistribution;
import org.junit.Test;
import com.linuxense.javadbf.DBFReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.Assert.*;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    String fileNameCsv = "C:/User/Idea_labs/Java/Lab1/results/result.csv";
    String fileNameBdf = "C:/User/Idea_labs/Java/Lab1/results/result.dbf";
    int size = 10;
    /**
     * Rigorous Test :-)
     */

    @Test
    public void testNormalDistribution () throws IOException {
        NormalDistribution normal = new NormalDistribution(2, 3);
        ArrayList<Double> result = normal.generate(size);
        assertEquals(result.size(), size);
    }

    @Test
    public void testUniformDistribution () throws IOException {
        UniformDistribution  uniform = new UniformDistribution(2, 3);
        ArrayList<Double> result = uniform.generate(size);
        assertEquals(result.size(), size);
    }

//    @Test
//    public void testPoissonDistribution () throws IOException {
//        PoissonDistribution poisson = new PoissonDistribution(1);
//        ArrayList<Double> result = poisson.generate(size);
//        assertEquals(result.size(), size);
//    }

    @Test
    public void testStorageCsv () throws IOException {
        Сontroller controller = new Сontroller(10);
        String result = controller.create("normal 2 3 uniform 5 3 poisson 8 CSV");
        assertTrue(result.equals(fileNameCsv));
    }

    @Test
    public void testSaveCsv () throws IOException {
        Сontroller controller = new Сontroller(10);
        String result = controller.create("normal 2 3 uniform 5 3 poisson 8 CSV");
        File f = new File(fileNameCsv);
        assertTrue(f.exists());
    }

    @Test
    public void testStorageBdf () throws IOException {
        Сontroller controller = new Сontroller(10);
        String result = controller.create("normal 2 3 uniform 5 3 poisson 8 DBF");
        assertEquals(result, fileNameBdf);
    }

    @Test
    public void testSaveBdf () throws IOException {
        Сontroller controller = new Сontroller(10);
        String result = controller.create("normal 2 3 uniform 5 3 poisson 8 DBF");
        File f = new File(fileNameBdf);
        assertTrue(f.exists());
    }
}
