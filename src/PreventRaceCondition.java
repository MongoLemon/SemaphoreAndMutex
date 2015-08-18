import java.util.concurrent.Semaphore;

/**
 * Created by anthony.su on 8/17/15.
 */
public class PreventRaceCondition {

	private static final int MAX_CONCURRENT_THREADS = 2;
	private final Semaphore ADMINLOCK = new Semaphore(MAX_CONCURRENT_THREADS,true);

	public void startTest(){
		for(int i=0;i<10;i++){
			Person person = new Person();
			person.start();
		}
	}

	public class Person extends Thread
	{
		@Override
		public void run(){
			try{

				// acquire lock
				ADMINLOCK.acquire();
			}catch (InterruptedException e){
				System.out.println("Here we have InterruptedException !");
				return;
			}
			System.out.println("Thread "+ this.getId()
					+ " start using Semaphore -- Acquire !");
			try{
				sleep(5000);
			}catch (Exception e){
				System.out.println("Fail to sleep 1000");
			}finally {

				// release lock
				ADMINLOCK.release();
			}
			System.out.println("Thread "+ this.getId()
					+" releasing Semaphore -- Release !");
		}
	}

	public static void main(String args[]){
		PreventRaceCondition testObject = new PreventRaceCondition();
		testObject.startTest();
	}
}
