package pl.bathroom.sync;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MainClass {
	
	
	public static void main(String[] args) throws InterruptedException{
	
	
		
	
	
		String inputPath = args[0];
		Parser.parse(inputPath);
		
		
		
	
		
		final int MAX_AVAILABLE=Parser.getSequence();
		
		
		final List<String> employees = Parser.getEmplyeesList();
		
		final String firstEmployee = Parser.getFirstEmployee();
		
		
		
	
		 final int NTHREADS = Parser.getNrOfThreads();
		
		    final ExecutorService exec
		            = Executors.newFixedThreadPool(NTHREADS);
		    
		 
	BoundedHashSet<String> bathroom=new BoundedHashSet<>(MAX_AVAILABLE,firstEmployee);
		    
	int timeout = 10;
	
	int intervalOfEmployeeCome=500;
	
	//create threads, one for each employee
		for(int i = 0;i < NTHREADS; i++){
		    exec.execute(new Employee(i,bathroom,employees.get(i)));
		  //uncomment to simulation of arriving employees in time interval
		    Thread.sleep(intervalOfEmployeeCome);
			
		}
	try {//after all threads will finish work, shutdown them gently
			exec.shutdown();
			exec.awaitTermination(timeout, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//when all threads will be Terminated save record from bathroom occupy to file
		if(exec.isTerminated()){
			String outputPath = args[1];
				//"output.txt";	
			
		Utility.saveOutput(outputPath, bathroom.getHistory());
		
		}
				
			
		
	
	
	
	}
}
	


