package CreatorAndConsumer;

/**
 * Created by anthony.su on 8/19/15.
 */
public class Consumer implements Runnable {

	/**
	 * the resource threads compete
	 */

	private Plate plate;

	/**
	 * Consumer Lock: make sure there is only one consumer in critical section
	 * if we have two consumers in the section, it may wake up a consumer instead of a creator
	 */
	private static Object consumerLock = new Object();

	public Consumer(Plate plate){
		this.plate = plate;
	}

	@Override
	public void run(){
		// creation must happen after creator lock acquired
		synchronized (consumerLock)
		{
			synchronized (plate)
			{
				while(plate.getEggNum()<1){
					try{
						plate.wait();
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}

				// make sure the number of eggs in plate is bigger than 0
				plate.getEgg();
				plate.notify();
			}
		}
	}
}
