package ProducerConsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Created by danahend on 28/02/2017.
 */
public class LowLevelDesign {

    List<Integer> list = new LinkedList<Integer>();
    final int MAX_SIZE = 10;
    Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (list.size() == MAX_SIZE) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }



    public void consume() throws InterruptedException {
        Random random = new Random();
        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }
                System.out.print("List size is: "+ list.size());
                int value = list.remove(0);
                System.out.println("; value is: " + value);
                lock.notify();
            }
            Thread.sleep(random.nextInt(1000));
        }

    }


    public static void main(String[] args) throws InterruptedException {
        LowLevelDesign producerConsumer = new LowLevelDesign();
        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }
}
