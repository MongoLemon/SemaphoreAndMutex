package CreatorAndConsumer;

/**
 * Created by anthony.su on 8/19/15.
 */
public class Creator implements Runnable {

	private Plate plate;

	/**
	 * Creator Lock
	 */

	private static Object creatorLock = new Object();

	public Creator(Plate plate){
		this.plate = plate;
	}

	@Override
	public void run(){
		// get creatorLock before starting creator job
		synchronized (creatorLock){
			synchronized (plate){
				while(plate.getEggNum() >= 5){
					try{
						plate.wait();
					}catch (InterruptedException e){
						e.printStackTrace();
					}
				}

				Object newEgg = new Object();
				plate.addEgg(newEgg);
				plate.notify();
			}
		}
	}
}
