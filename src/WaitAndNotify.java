import java.util.Objects;

/**
 * in this example i will demonstrate what is wait and notify.
 * wait and notify, both, are Object methods and the must be inside synchronized block.
 * wait says to the monitor: i'm letting the lock free for other threads and i will wait until another thread
 * will perform notify.
 * they must work with the same lock object.
 *
 * notifyAll is when you have more than one thread blocked.
 *
 * the mechanism of changing threads with wait and notify calls "context switch"
 * (also changing process calls "context switch"...)
 * Created by danahend on 28/02/2017.
 */
public class WaitAndNotify{

    Object lock = new Object();
    int var = 0;

    public void increment(){
        synchronized (lock){
            System.out.println("in increment()");
            for (int i=0; i<10000; i++){
                var++;
            }
            System.out.println("increment() finish");
            lock.notify();
        }
    }

    public void thePrinter(){
        synchronized (lock){
            System.out.println("in thePrinter()");
            try {
                System.out.println("waiting to increment() finish");
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thePrinter() finish");
            System.out.println("this is the var " + var);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        WaitAndNotify wan = new WaitAndNotify();
        Thread increment = new Thread(new Runnable() {
            @Override
            public void run() {
                wan.increment();
            }
        });
        Thread print = new Thread(new Runnable() {
            @Override
            public void run() {
                wan.thePrinter();
            }
        });
        print.start();
        increment.start();

        increment.join();
        print.join();

    }
}
