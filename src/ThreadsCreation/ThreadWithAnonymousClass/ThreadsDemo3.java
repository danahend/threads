package ThreadsCreation.ThreadWithAnonymousClass;

/**
 * this demo shows that if u dont want to  create new class with all that code
 * you can create anonymous class (class without the name)
 * Created by danahend on 23/02/2017.
 */
public class ThreadsDemo3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0 ; i<10; i++){
                    System.out.println("Hello " + i);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
    }
}
