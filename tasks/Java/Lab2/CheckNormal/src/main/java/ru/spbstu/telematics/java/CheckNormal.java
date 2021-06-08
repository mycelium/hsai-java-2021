package ru.spbstu.telematics.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbstu.telematics.java.utils.CharacteristicsCalculator;
import ru.spbstu.telematics.java.utils.Sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CheckNormal {
    static private Logger logger = LoggerFactory.getLogger(CheckNormal.class);

    /**
     * Finds out whether sample is distributed normally or not.
     * */
    static public boolean isNormal (Sample sample) {
        logger.info("Checking if variable " + sample.getName() + " is normal...");
        ArrayList<Double> tmp = new ArrayList<>(sample.getValues());
        Collections.sort(tmp);
        HashMap<String, Double> quartiles = CharacteristicsCalculator.quartiles(sample);
        double d = quartiles.get("Upper") - quartiles.get("Lower");
        double x1 = Math.max(tmp.get(0), quartiles.get("Lower") - 3 * d / 2);
        double x2 = Math.min(tmp.get(tmp.size() - 1), quartiles.get("Upper") + 3 * d / 2);
        int n = 0;
        for(Double val: tmp)
            if((val < x1) || (val > x2))
                n++;
        double spikeRate = (double) n / tmp.size();
        logger.info("Quartiles: " + quartiles.get("Lower") + ", " + quartiles.get("Upper") + "; " + "Spikes rate: " + spikeRate);
        boolean isNormal = (0.0065 < spikeRate && spikeRate < 0.0075);
        if (isNormal) {
            logger.info("Variable " + sample.getName() + " is distributed normally");
        } else {
            logger.info("Variable " + sample.getName() + " is not distributed normally");
        }
        return isNormal;
    }
}
