package ru.spbstu.telematics.distributions;

public class Poisson extends Distribution<Integer> {

    private Integer mean;

    public Poisson(Integer mean) {
        this.mean = mean;
    }

    @Override
    public Integer getRandom() {
        int x = 0;
        double p = Math.exp(-mean);
        double r = random.nextDouble() - p;
        while (r > 0) {
            x ++;
            p *= ((double) mean) / x;
            r -= p;
        }
        return x;
    }

}
