package ru.spbstu.telematics.graphics;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import ru.spbstu.telematics.utils.DAOStub;

/**
 * Unit test for simple App.
 */
public class PlotterTest
{
    @Test
    public void testShowGraph()
    {
        Plotter plotter = new Plotter(new DAOStub());
        plotter.showGraph(1);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testShowHistogram() {
        Plotter plotter = new Plotter(new DAOStub());
        plotter.showHistogram(1);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveGraph() {
        Plotter plotter = new Plotter(new DAOStub());
        plotter.saveGraph(1, "graph.png");
    }

    @Test
    public void testSaveHistogram() {
        Plotter plotter = new Plotter(new DAOStub());
        plotter.saveHistogram(1, "hist.png");
    }

}
