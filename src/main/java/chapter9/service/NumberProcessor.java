package chapter9.service;

public class NumberProcessor {
    private Double sum = null;
    private Double average = null;

    public NumberProcessor(Double[] numbers) {
            this.sum = 0.0;
            for (Double num : numbers) {
                this.sum += num;
            }
            this.average = this.sum / numbers.length;
    }

    public Double getSum() {
        return sum;
    }

    public Double getAverage() {
        return average;
    }
}
