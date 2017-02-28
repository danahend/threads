package UsingSyncronizedAsMutex.SyncronizedMethod;

/**
 * to see the failure run it several times, you will see that the output is not all the time 20K
 * this is because two threads trying to access and modify shared memory.
 * Created by danahend on 23/02/2017.
 */
public class TheProblem {
    public int var = 0;

    public static void main(String[] args) {
        TheProblem app = new TheProblem();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10000; i++){
                    app.var++;
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10000; i++){
                    app.var++;
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
