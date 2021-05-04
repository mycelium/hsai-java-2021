package ru.spbstu.telematics.lab1;


import ru.spbstu.telematics.lab1.saving.Saving;
import ru.spbstu.telematics.lab1.saving.SavingType;

/**
 * Module API example :)
 */
public class Main {
    public static void main(String[] args) {
        RandomData data = new RandomData("data1").
                addVariable(Variable.buildNormal("normal", 2, 3)).
                addVariable(Variable.buildUniform("uniform", 1, 3)).
                addVariable(Variable.buildPoisson("poisson", 6));
        String filename1 = Saving.save(data, 10000, SavingType.Database);
        String filename2 = Saving.save(data, 10000, SavingType.CSV);
        System.out.println("DBF filename = " + filename1);
        System.out.println("CVS filename = " + filename2);
    }
}
