package UsingSyncronizedAsMutex.SyncronizedCodeBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * here i added to stage1 and stage2 the synchronized method, run it and you will see that it will take too long...
 * thats because the app have only one object. while this object is at stage1 he can not perform stage2.
 * he must finish stage1 in order to start stage2.
 * so we are using the synchronized method but we have single object to perform it...
 * Created by danahend on 27/02/2017.
 */
public class SolvingWithSyncMethod {
    Random random = new Random();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    private synchronized void stageOne(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }

    private synchronized void stageTwo(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));
    }

    public void proccess(){
        for (int i=0; i<1000; i++){
            stageOne();
            stageTwo();
        }
    }

    public static void main(String[] args) {
        SolvingWithSyncMethod app = new SolvingWithSyncMethod();
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
