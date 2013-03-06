package pl.bathroom.sync;

import java.util.Set;


public class Employee implements Runnable {

	
	final static int timeOfBeeingInBathroom=1000;
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
		System.out.println(number);
		try {
			b.add(id);
			//simulation of beeing in bathroom
			Thread.sleep(timeOfBeeingInBathroom);
			b.remove(id);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}