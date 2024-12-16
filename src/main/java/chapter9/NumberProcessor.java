package chapter9;

import java.util.Optional;

public class NumberProcessor {
    private Double sum;
    private Double average;

    public NumberProcessor(Optional<Double[]> numbers) {
        if (numbers.isPresent()) {
            Double[] nums = numbers.get();
            this.sum = 0.0;
            for (Double num : nums) {
                this.sum += num;
            }
            this.average = this.sum / nums.length;
        } else {
            this.sum = 0.0;
            this.average = 0.0;
        }
    }

    public Double getSum() {
        return sum;
    }

    public Double getAverage() {
        return average;
    }
}
