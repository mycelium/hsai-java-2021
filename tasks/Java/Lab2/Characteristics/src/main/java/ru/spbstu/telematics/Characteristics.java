package ru.spbstu.telematics;
import org.apache.logging.log4j.LogManager;
import ru.spbstu.telematics.utils.StatsUtils;
import org.apache.logging.log4j.Logger;


public class Characteristics {
    static Logger logger = LogManager.getLogger(Characteristics.class);

    private String name = "no name";
    private double mean;
    private double median;
    private double variation;
    private double min;
    private double max;

    public Characteristics(double[] values) {
        initChars(values);
    }

    public Characteristics(double[] values, String name) {
        initChars(values);
        this.name = name;
    }

    private void initChars(double[] values) {
        double[] temp = values.clone();
        double[] mv = StatsUtils.meanVar(temp);
        mean = mv[0];
        variation = mv[1];
        median = StatsUtils.medianSort(temp);
        min = temp[0];
        max = temp[temp.length - 1];
        logger.info("Name: " + name + " processed.");
    }

    public double[] allChars(String order) {
        double[] res = new double[order.length()];
        for (int i = 0; i < order.length(); i++) {
            char x = order.charAt(i);
            if (x == 'm') res[i] = mean;
            else if (x == 'v') res[i] = variation;
            else if (x == 'd') res[i] = median;
            else if (x == 'n') res[i] = min;
            else if (x == 'x') res[i] = max;
            else throw new IllegalArgumentException("Unknown char: " + x);
        }
        return res;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getMean() {
        return mean;
    }

    public double getMedian() {
        return median;
    }

    public double getVariation() {
        return variation;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
