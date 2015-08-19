package CreatorAndConsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anthony.su on 8/19/15.
 */
public class Plate {
	private List<Object> eggs = new ArrayList<Object>();

	public Object getEgg()
	{
		System.out.println("Get an egg !");
		Object egg = eggs.get(0);
		eggs.remove(0);
		return egg;
	}

	public void addEgg(Object egg){
		System.out.println("Add an egg !");
		eggs.add(egg);
	}

	public int getEggNum(){
		return eggs.size();
	}
}
