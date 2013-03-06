package pl.bathroom.sync;

import java.util.Set;


public class Employee implements Runnable {

	
	final static int timeOfBeeingInBathroom=700;
	final int number;
	final String id;
	final BoundedHashSet<String> b ;
	
	public Employee(int n,BoundedHashSet<String> b, String id){
		this.number=n;
		this.b=b;
		this.id=id;
	}
	
	@Override
	public void run() {
	
		try {
			b.add(id);//employee enter bathroom
			//uncomment to simulation of employee being in bathroom by time = timeOfBeeingInBarhroom
			Thread.sleep(timeOfBeeingInBathroom);
			b.remove(id);//employee leave bathroom
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}