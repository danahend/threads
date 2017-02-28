package ThreadsCreation.ThreadsImplementRunnable;

/**
 * Created by danahend on 23/02/2017.
 */

class ThreadCreator implements Runnable{
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
public class ThreadDemo2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadCreator());
        t1.start();
        Thread t2 = new Thread(new ThreadCreator());
        t2.start();
    }
}
