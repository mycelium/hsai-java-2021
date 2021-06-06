package ru.spbstu.telematics.normalcheking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.spbstu.telematics.utils.StatsUtils;

public class NormalChecker {
    static Logger logger = LogManager.getLogger(NormalChecker.class);

    static public boolean isNormal(double[] values) {
        double[] temp = values.clone();
        double[] quartiles = StatsUtils.quartilesSort(temp);
        double d = quartiles[1] - quartiles[0];
        double x1 = Math.max(temp[0], quartiles[0] - 3 * d / 2);
        double x2 = Math.min(temp[temp.length - 1], quartiles[1] + 3 * d / 2);
        int n = 0;
        for (double v: temp) if (v < x1 || v > x2) n += 1;
        double rate = (double) n / temp.length;
        logger.info("Quartiles: " + quartiles[0] + ", " + quartiles[1] + "; Spikes rate: " + rate + ".");
        return 0.0069 < rate && rate < 0.0071;
    }

}
