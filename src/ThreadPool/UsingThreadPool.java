package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * At this tutorial id will use 2 threads and 5 tasks. the thread pool will be responsible
 * for managing those threads.
 * Created by danahend on 28/02/2017.
 */
public class UsingThreadPool implements Runnable{

    private int id;

    public UsingThreadPool(int i){
        this.id = i;
    }
    @Override
    public void run() {
        System.out.println("starting: " + id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("completed: " + id);
    }

    public static void main(String[] args) {
        ExecutorService executer = Executors.newFixedThreadPool(2); // now we have two threads to perform the 5 tasks
        for (int i=0; i<10 ; i++){
            executer.submit(new UsingThreadPool(i));// we are submitting all tasks to the executer
        }
        executer.shutdown();// will wait until all tasks submit.

        System.out.println("all tasks submited.");

        try {
            executer.awaitTermination(1, TimeUnit.DAYS);//threads start to work on their tasks.
            //if it will not finish after 1 day we will get InterruptedException
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("all tasks completed.");
    }
}
