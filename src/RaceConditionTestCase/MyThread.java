package RaceConditionTestCase;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by anthony.su on 8/18/15.
 */
public class MyThread implements Runnable{

	private int type;

	private static ReentrantLock lock = new ReentrantLock(); // make sure it is static, or there would be multiple ReentrantLock objects

	public MyThread(int type) {
		this.type = type;
	}

	@Override
	public void run() {
		if(type == 1){
			for (int i=0;i<10000;i++){
				lock.lock();
				Test.num--;
				lock.unlock();
			}
		}else{
			for (int i=0;i<10000;i++){
				lock.lock();
				Test.num++;
				lock.unlock();
			}
		}
	}
}
