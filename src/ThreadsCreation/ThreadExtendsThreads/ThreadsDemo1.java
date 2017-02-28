package ThreadsCreation.ThreadExtendsThreads;

/**
 * threads can be created by extending Thread class or implementing Runnable class
 * this example is using extends Thread
 * Created by danahend on 23/02/2017.
 */

class ThreadCreator extends Thread{
    public void run(){
        for (int i=0; i<10; i++){
            System.out.println("Hello " + i);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}

public class ThreadsDemo1 {
    public static void main(String[] args) {
        ThreadCreator t1 = new ThreadCreator();
        t1.start(); //NOTE! if i use run here it will take the thread from the main class
        //start will go to run() method and will create new thread

        ThreadCreator t2 = new ThreadCreator();
        t2.start();
        //NOTE! it not printing in a syncronize way 1-9 and then 1-9, it prints in parallel and that the proof
        //for the multithreading
    }
}
