package chapter4.VariableA;

public class CPU extends Component {
    private double speedGHz;

    public CPU(String manufacturer, String model, double speedGHz) {
        super(manufacturer, model);
        this.speedGHz = speedGHz;
    }

    @Override
    public String toString() {
        return "CPU{" +
                "speedGHz=" + speedGHz +
                ", " + super.toString() +
                '}';
    }
}
