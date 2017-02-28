package UsingSyncronizedAsMutex.SyncronizedMethod;

/**
 * run several times and see that the output is stable and all the time we will get 20K.
 * the synchronized function lock the shared memory for one thread only at a time.
 * also called MUTEX.
 * it's important the reveil the critical area at the code and to use the synchronized function there
 * Created by danahend on 23/02/2017.
 */
public class SolvingByUsingSyncronized {
    public int var = 0;

    public synchronized void increment(){
        var++;
    }

    public static void main(String[] args) {
        SolvingByUsingSyncronized app = new SolvingByUsingSyncronized();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    app.increment();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    app.increment();
                }
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();// try without the join the output will be 0. and thats because the printing
            t2.join();// preformed before the threads starts to work. the join will say to the threads finish to work
            // and only after that it will print
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(app.var);
    }
}
