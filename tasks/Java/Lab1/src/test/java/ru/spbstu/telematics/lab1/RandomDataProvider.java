package ru.spbstu.telematics.lab1;

import java.util.ArrayList;

public class RandomDataProvider {
    public static RandomData buildData() {
        return new RandomData("test1").
                addVariable(Variable.buildNormal("normal", 4, 2)).
                addVariable(Variable.buildPoisson("poisson", 4)).
                addVariable(Variable.buildUniform("uniform, hmm", -2, 10));
    }
}
