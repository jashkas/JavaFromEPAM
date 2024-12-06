package chapter4.VariableA;

public class Computer {
    private HardDrive hardDrive;
    private DiskDrive diskDrive;
    private RAM ram;
    private CPU cpu;
    private boolean isOn = false;

    public Computer(HardDrive hardDrive, DiskDrive diskDrive, RAM ram, CPU cpu) {
        this.hardDrive = hardDrive;
        this.diskDrive = diskDrive;
        this.ram = ram;
        this.cpu = cpu;
    }

    // Метод включения компьютера
    public void turnOn() {
        if (!isOn) {
            isOn = true;
            System.out.println("Компьютер включен.");
        } else {
            System.out.println("Компьютер уже включен.");
        }
    }

    // Метод выключения компьютера
    public void turnOff() {
        if (isOn) {
            isOn = false;
            System.out.println("Компьютер выключен.");
        } else {
            System.out.println("Компьютер уже выключен.");
        }
    }

    // Метод проверки на вирусы
    public void checkForViruses() {
        if (isOn) {
            System.out.println("Проверка компьютера на вирусы...");
            System.out.println("Проверка завершена. Вирусы не обнаружены.");
        } else {
            System.out.println("Компьютер выключен. Включите его для проверки на вирусы.");
        }
    }

    // Метод для вывода на консоль размера винчестера
    public void printHardDriveSize() {
        System.out.println("Размер винчестера: " + hardDrive.getSizeGB() + " GB");
    }

    @Override
    public String toString() {
        return "Computer{" +
                "hardDrive=" + hardDrive +
                ", diskDrive=" + diskDrive +
                ", ram=" + ram +
                ", cpu=" + cpu +
                ", isOn=" + isOn +
                '}';
    }
}
