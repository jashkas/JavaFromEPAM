package chapter4.VariableA;

public class HardDrive extends Component{
    private int sizeGB;

    public HardDrive(String manufacturer, String model, int sizeGB) {
        super(manufacturer, model);
        this.sizeGB = sizeGB;
    }

    public int getSizeGB() {
        return sizeGB;
    }

    @Override
    public String toString() {
        return "HardDrive{" +
                "sizeGB=" + sizeGB +
                ", " + super.toString() +
                '}';
    }
}
