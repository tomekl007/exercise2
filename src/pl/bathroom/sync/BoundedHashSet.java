package pl.bathroom.sync;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class BoundedHashSet <T> {
    private final Set<T> set;
    private final Semaphore sem;
    private String history = "";
    
   // private static Lock man;
   // private static Lock woman;
    
    //1-man -1-woman
    //final int[] threeLastPersons = {0,0,0};
    int lastPerson = 1;//first go man
    int counter;
    
  //  final Lock lock = new ReentrantLock();
  //  final Condition womanInside  = lock.newCondition(); 
  //  final Condition manInside = lock.newCondition();
    
    

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
    	//lock.lock();
        
    	//while(checkDidMen(o.toString())){
    	//	 manInside.await();
    	//}
        
    	synchronized (this) {
			
		
    	while(lastPerson==1 && !checkDidMen(o.toString())){
    	//try{
    		wait();
    	//}catch(InterruptedException e){
    	//	System.out.println("catch" );    
    	//	e.printStackTrace();
    	//}
    	}
    	}
    	
    	sem.acquire();
    	synchronized (this) {
    		counter++;	
		}
    	
    //	if(lastPerson==1 && checkDidMen(o.toString()))
    //			manInside.notify();
    //	else if(lastPerson==-1 && !checkDidMen(o.toString()))
    //		womanInside.notify();
    	
    //	if(lastPerson==1)
    	//	womanInside.await();
    	//else if(lastPerson==-1)
    		//manInside.await();
    	
    //	if(checkDidMen(o.toString()))
    //		lastPerson=1;
   // 	else
   // 		lastPerson=-1;
    	
    	
    

    //	 if(lastPerson==-1 || lastPerson==0 && !checkDidMen(o.toString()))
    	//	this.notify();
    		
    	 
    	 
    	
        //Thread.sleep(3000);
        System.out.println("add : " + o.toString());
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            printToHistory(o.toString() + " in ");
            return wasAdded;
        } finally {
        	
            if (!wasAdded)
                sem.release();
        //   lock.unlock();
        }
    }

    public boolean remove(Object o) throws InterruptedException {
	//lock.lock();
        
    //	while(checkDidMen(o.toString())){
    //		manInside.notify();
    	   
    //	}
    	
    //	try{
        boolean wasRemoved = set.remove(o);
        System.out.println("remove : " + o.toString());
        if (wasRemoved){
           
            printToHistory(o.toString() + " out ");
            synchronized (this) {
				
			
            sem.release();
            System.out.println(" counter = "+  counter);
            counter--;
            if(counter==0){
            	lastPerson=0;
            	//counter=0;
            	System.out.println("notifyAll");
            	notifyAll();
            	}
            }
        }
        return wasRemoved;
    //	}finally{
      //  lock.unlock();
    //	}
    }
    
    synchronized
    private void printToHistory(String text){
    	System.out.println(text);
    	history += text+"\n";
    	
    }
    
    synchronized 
    private boolean checkDidMen(String name){
    	if(name.startsWith("m"))
    		return true;
    	else 
    		return false;
    }
    
    
    
    
    public String getHistory(){
    	return history;
    }
}
