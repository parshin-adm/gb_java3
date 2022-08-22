import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
        public static final int CARS_COUNT = 4;
        public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
            Race race = new Race(new Road(60), new Tunnel(), new Road(40));
            Car[] cars = new Car[CARS_COUNT];
            Semaphore semaphore = new Semaphore(2);
            CyclicBarrier cb = new CyclicBarrier(4);
            CountDownLatch countDownLatch1 = new CountDownLatch(4);
            CountDownLatch countDownLatch2 = new CountDownLatch(4);
            for (int i = 0; i < cars.length; i++) {
                cars[i] = new Car(race, 20 + (int) (Math.random() * 10), semaphore,  cb, countDownLatch1, countDownLatch2);
            }


                for (int i = 0; i < cars.length; i++) {
                    new Thread(cars[i]).start();
                }


            countDownLatch1.await();
           // cb.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            countDownLatch2.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        }
}
