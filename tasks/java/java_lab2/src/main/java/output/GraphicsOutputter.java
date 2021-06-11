package output;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicsOutputter {
    final static int IMAGE_WIDTH = 1200, IMAGE_HEIGHT = 1000;
    public static void output(String fname, double[][] vals, int numVar) {
        // "Вывод графика значений" -- это что? график X(n)?
        XYSeries series = new XYSeries("Distribution");

        for(int i = 0; i < vals.length; i += 1){
            series.add(i, vals[i][numVar]);
        }

        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory
                .createXYLineChart("y = sin(x)", "x", "y",
                        xyDataset,
                        PlotOrientation.VERTICAL,
                        true, true, true);

        BufferedImage img = chart.createBufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT);

        try {
            ImageIO.write(img, "png", new File(fname));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}