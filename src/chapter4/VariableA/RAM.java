package chapter4.VariableA;

public class RAM extends Component {
    private int capacityGB;

    public RAM(String manufacturer, String model, int capacityGB) {
        super(manufacturer, model);
        this.capacityGB = capacityGB;
    }

    public int getCapacityGB() {
        return capacityGB;
    }

    public void setCapacityGB(int capacityGB) {
        this.capacityGB = capacityGB;
    }

    @Override
    public String toString() {
        return "RAM{" +
                "capacityGB=" + capacityGB +
                ", " + super.toString() +
                '}';
    }
}
