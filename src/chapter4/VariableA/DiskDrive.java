package chapter4.VariableA;

public class DiskDrive extends Component {
    public DiskDrive(String manufacturer, String model) {
        super(manufacturer, model);
    }

    @Override
    public String toString() {
        return "DiskDrive{" + super.toString() + '}';
    }
}
