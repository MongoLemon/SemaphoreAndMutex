import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * Created by anthony.su on 8/5/15.
 */
public class SemaphoreMutexTestCase {

    static Object lock = new Object();
    static LinkedList<String> list = new LinkedList<String>();

    /**
     * Semaphore maintains a set of permits
     * Each acquire blocks if necessary until a permit is available, then acquire takes that permit
     * Each release adds a permit and releases a blocking acquirer as well
     */
    static Semaphore semaphore = new Semaphore(0);
    static Semaphore mutex = new Semaphore(1);

    // produce Integer every time
    static class Producer extends Thread{
        public void run(){

            int counter = 1;
            try{
                while(true){
                    String threadName = Thread.currentThread().getName() + counter++;

                    mutex.acquire();
                    list.add(threadName);
                    System.out.println("Producer is producing new value: " + threadName);
                    mutex.release();

                    // release lock
                    semaphore.release();
                    Thread.sleep(500);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    // consume Integer every time
    static class Consumer extends Thread{

        String consumerName;

        public Consumer(String name){
            this.consumerName = name;
        }

        public void run(){
            try{
                while(true){
                    /**
                     * Acquire lock. Acquires the given number of permits from this semaphore,
                     * blocking until all are available
                     * process stops here until producer releases the lock
                     */
                    semaphore.acquire();

                    // Acquires a permit from this semaphore, blocking until one is available
                    mutex.acquire();
                    String result = "";
                    for(String value : list){
                        result = value + ",";
                    }
                    System.out.println(consumerName + "consumes value: " + result + "list.size():" + list.size());
                    mutex.release();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        new Producer().start();
        new Consumer("Apple").start();
        new Consumer("Google").start();
        new Consumer("Facebook").start();
    }

    /**
     *  This process is like below:
     *  Producer: Add an object to list --> Semaphore.release(1)
     *  Consumer someone: Semaphore.acquire(1) -->  get access to read what is inside the list
     */
}
