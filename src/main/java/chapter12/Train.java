package chapter12;

public class Train extends Thread {
    private final Tunnel tunnel;
    private final String name;

    public Train(Tunnel tunnel, String name) {
        this.tunnel = tunnel;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            tunnel.passTunnel(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Поезд " + name;
    }
}