package ru.spbstu.telematics.lab1.random;

import java.util.ArrayList;
import java.util.List;
import java.math.*;

public class DistributionTestUtil {
    public static float[] findMeanVar(List<Float> selection) {
        float mean = 0;
        for(float val: selection) {
            mean += val;
        }
        mean /= selection.size();
        float var = 0;
        for(float val: selection) {
            var += (val - mean) * (val - mean);
        }
        var /= selection.size();
        return new float[]{mean, var};
    }

    public static class MVComparator {
        final int selectionSize;
        final int numberOfTakes;

        public MVComparator(int selectionSize, int numberOfTakes) {
            this.selectionSize = selectionSize;
            this.numberOfTakes = numberOfTakes;
        }

        public boolean compare(Distribution dst, float[] expectedMV) {
            ArrayList<Float> means = new ArrayList<>(numberOfTakes);
            ArrayList<Float> vars = new ArrayList<>(numberOfTakes);
            for (int i = 0; i < numberOfTakes; i++) {
                float[] mv = findMeanVar(dst.selectionFloat(selectionSize));
                means.add(mv[0]);
                vars.add(mv[1]);
            }
            float[] mmv = findMeanVar(means);
            float[] vmv = findMeanVar(vars);
            float meanSTD = (float) Math.sqrt(mmv[1]);
            float varSTD = (float) Math.sqrt(vmv[1]);
            if (mmv[0] - meanSTD > expectedMV[0] || mmv[0] + meanSTD < expectedMV[0]) {
                System.out.println("Mean of " + dst + " not in range: " + expectedMV[0] + " not in (" +
                        (mmv[0] - meanSTD) + "; " + (mmv[0] + meanSTD) + ")");
                return false;
            }
            float expectedVar = expectedMV[1];
            if (vmv[0] - varSTD > expectedVar || vmv[0] + varSTD < expectedVar) {
                System.out.println("Var of " + dst + " not in range: " + expectedVar + " not in (" +
                        (vmv[0] - varSTD) + "; " + (vmv[0] + varSTD) + ")");
                return false;
            }
            return true;
        }
    }
}
