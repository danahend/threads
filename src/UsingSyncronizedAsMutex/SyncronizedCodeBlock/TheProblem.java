package UsingSyncronizedAsMutex.SyncronizedCodeBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * in this example we will see two lists that get random numbers.
 * pay attention that list is non-primitive object and it requiered more than one operation to add new variable.
 * the problem here that two threads trying to get to the same list and trying to add variable at the same time
 * so we get the "ArrayIndexOutOfBoundsException"
 * we want to create list1 size:2000, list2 size:2000 at 2000milis
 * Created by danahend on 27/02/2017.
 */
public class TheProblem {

    Random random = new Random();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    private void stageOne(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }

    private void stageTwo(){
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
        TheProblem app = new TheProblem();
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
