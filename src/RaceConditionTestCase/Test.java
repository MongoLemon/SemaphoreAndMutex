package RaceConditionTestCase;

/**
 * Created by anthony.su on 8/18/15.
 */
public class Test {

	public static int num = 1000000;

	public static void main(String args[]){
		Thread a = new Thread(new MyThread(1));
		Thread b = new Thread(new MyThread(2));

		a.start();
		b.start();

		/**
		 *  main thread prints after sub-thread's completion
		 */
		try{
			a.join();
			b.join();
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(num);
	}
}
