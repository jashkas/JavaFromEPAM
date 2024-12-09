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
    public void setSizeGB(int sizeGB) {
        this.sizeGB = sizeGB;
    }

    @Override
    public String toString() {
        return "HardDrive{" +
                "sizeGB=" + sizeGB +
                ", " + super.toString() +
                '}';
    }
}
