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

    static class Producer extends Thread{
        public void run(){

            int counter = 1;

        }
    }
}
