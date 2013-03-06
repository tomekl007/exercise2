package pl.bathroom.sync;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author Tomasz Lelek
 *
 * @param <T>
 * Main logic of the program, using semaphores, and wait/notify 
 */
public class BoundedHashSet <T> {
    private final Set<T> set;
    private final Semaphore sem;
    private String history = "";
    
 
    int lastPerson = 1;//first go man
    int counter;
    boolean first = true;

    
    

    public BoundedHashSet(int bound, String firstEmployee) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
        
        if(!(firstEmployee.startsWith("k") || firstEmployee.startsWith("m")))
        	throw new IllegalArgumentException("firstEmployee must start with k or m");
        
        if(firstEmployee.startsWith("k"))
        	lastPerson=-1;
        else
        	lastPerson=1;
    }

    public boolean add(T o) throws InterruptedException {
        
    	synchronized (this) {
			
		
    	while(lastPerson==1 && !checkDidMen(o.toString())){
    	
    		wait();
    		lastPerson=-1;
    
    	}
    	}
    	
    	synchronized (this) {
			
    		
        	while(lastPerson==-1 && checkDidMen(o.toString())){
        
        		wait();
        		lastPerson=1;
        	
        	}
       }
    	
    	
    	
    	sem.acquire();
    	synchronized (this) {
    		counter++;	
		}
 
      
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            printToHistory(o.toString() + " in  ");
     
            
            return wasAdded;
        } finally {
        	
            if (!wasAdded)
                sem.release();
     
        }
    }

    public boolean remove(Object o) throws InterruptedException {
	
 
        boolean wasRemoved = set.remove(o);
      
        if (wasRemoved){
           
            printToHistory(o.toString() + " out  ");
            synchronized (this) {
				
			
            sem.release();
          
            counter--;
            if(counter==0){
            	lastPerson=0;
            	
           
            	notifyAll();
            	}
            }
        }
        return wasRemoved;
 
    }
    
    synchronized
    private void printToHistory(String text){
    	
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
