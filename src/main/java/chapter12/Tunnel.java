package chapter12;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Tunnel {
    // Создаем семафоры для двух туннелей, ограничиваем количество потоков
    private final Semaphore tunnel1 = new Semaphore(1);
    private final Semaphore tunnel2 = new Semaphore(1);

    // Максимальное время ожидания
    private final long maxWaitTime;

    public Tunnel(long maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    // Метод, позволяющий поезду проходить через туннели
    public void passTunnel(Train train) throws InterruptedException {
        // Пытаемся захватить доступ к первому туннелю
        if (tunnel1.tryAcquire(maxWaitTime, TimeUnit.MILLISECONDS)) {
            System.out.println(train + " вошел в Туннель 1");
            Thread.sleep(1000); // Поезд проходит через туннель
            // Освобождаем туннель после прохода
            tunnel1.release();
        } else if (tunnel2.tryAcquire(maxWaitTime, TimeUnit.MILLISECONDS)) {
            System.out.println(train + " вошел в Туннель 2");
            Thread.sleep(1000);
            tunnel2.release();
        } else {
            System.out.println(train + " превысил максимальное время ожидания и был перенаправлен");
        }
    }
}
