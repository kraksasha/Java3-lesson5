import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static int countThread;
    public static Semaphore semaphore;
    public static CountDownLatch cdl;
    public static void main(String[] args) throws InterruptedException {
        semaphore = new Semaphore(2);
        cdl = new CountDownLatch(CARS_COUNT);
        countThread = CARS_COUNT;
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        MainClass.cdl.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        while (true){
            Thread.sleep(10);
            if (countThread == 0){
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
                break;
            }
        }
    }

}
