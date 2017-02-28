package UsingSyncronizedAsMutex.SyncronizedCodeBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * here we can have two threads inside the class SolvingWithSyncCodeBlockAndLocks
 * one will run on stageOne at the same time that another thread will run on stageTwo.
 * (at SyncMethod solution only one thread could run inside the class in a given moment)
 * Created by danahend on 27/02/2017.
 */
public class SolvingWithSyncCodeBlockAndLocks {
    Random random = new Random();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    private void stageOne(){
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    private void stageTwo(){
        synchronized (lock2){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));

        }
    }

    public void proccess(){
        for (int i=0; i<1000; i++){
            stageOne();
            stageTwo();
        }
    }

    public static void main(String[] args) {
        SolvingWithSyncCodeBlockAndLocks app = new SolvingWithSyncCodeBlockAndLocks();
        System.out.println("starting...");
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                app.proccess();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                app.proccess();
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("time:" + (end-start));
        System.out.println("list1 size:" + app.list1.size() + ", list2 size:" + app.list2.size());
    }
}
