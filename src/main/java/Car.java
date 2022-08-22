import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private Semaphore semaphore;
    private CountDownLatch countDownLatch1;
    private CountDownLatch countDownLatch2;
    private CyclicBarrier cb;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, Semaphore semaphore, CyclicBarrier cb, CountDownLatch countDownLatch1, CountDownLatch countDownLatch2) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.semaphore = semaphore;
        this.countDownLatch1 = countDownLatch1;
        this.countDownLatch2 = countDownLatch2;
        this.cb = cb;
    }
    @Override
    public void run() {
        try {
                System.out.println(this.name + " готовится");
                Thread.sleep(500 + (int) (Math.random() * 800));
                cb.await();
                System.out.println(this.name + " готов");
                countDownLatch1.countDown();




        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            countDownLatch1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            if(race.getStages().get(i) instanceof Tunnel) {
                try {
                    semaphore.acquire();
                    race.getStages().get(i).go(this);
                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else {
                race.getStages().get(i).go(this);
            }
        }
        countDownLatch2.countDown();
    }
}
