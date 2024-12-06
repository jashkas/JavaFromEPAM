package chapter4.VariableA;

public class Main {
    public static void main(String[] args) {
        HardDrive hd = new HardDrive("Seagate", "ST1000DM010", 1000);
        DiskDrive dd = new DiskDrive("LG", "GH24NSC0");
        RAM ram = new RAM("Corsair", "Vengeance LPX", 16);
        CPU cpu = new CPU("Intel", "Core i7-9700K", 3.6);

        Computer computer = new Computer(hd, dd, ram, cpu);
        computer.turnOn();
        computer.checkForViruses();
        computer.printHardDriveSize();
        computer.turnOff();

        System.out.println(computer);
    }
}
