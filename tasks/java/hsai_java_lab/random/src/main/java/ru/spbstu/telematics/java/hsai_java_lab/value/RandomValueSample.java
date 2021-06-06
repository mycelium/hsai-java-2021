package ru.spbstu.telematics.java.hsai_java_lab.value;

public class RandomValueSample {
    private String name;
    private Double[] sample;

    public RandomValueSample(String name, Double[] sample) {
        this.name = (name == null) ? "RandomValueSample" : name;
        if (sample == null) {
            throw new NullPointerException("Sample is null");
        }
        else {
            this.sample = sample;
        }
    }

    public String getName() {
        return name;
    }

    public Double[] getSample() {
        return sample;
    }
}
