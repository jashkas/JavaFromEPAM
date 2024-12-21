package chapter12;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Tunnel tunnel = new Tunnel(6000);

        Train train1 = new Train(tunnel, "1");
        Train train2 = new Train(tunnel, "2");
        Train train3 = new Train(tunnel, "3");
        Train train4 = new Train(tunnel, "4");
        Train train5 = new Train(tunnel, "5");
        Train train6 = new Train(tunnel, "6");

        train1.start();
        train2.start();
        train3.start();
        train4.start();
        train5.start();
        train6.start();
    }
}
