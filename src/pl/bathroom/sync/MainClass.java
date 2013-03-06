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
	
	
	public static void main(String[] args){
	// throws InterruptedException{
		
		//List<String> mens = new LinkedList<>();
		//List<String> womemens = new LinkedList<>();
		
		
		Parser.parse("E:\\EcplipseWorkspace\\workspace\\Zadanie2\\src\\pl\\bathroom\\sync\\input.txt");
		
		//System.out.println(Parser.men);
		//System.out.println(Parser.women);
		
		System.out.println(Parser.employees);
		
		final int MAX_AVAILABLE=Parser.sequence;
		//final Semaphore bathroom = new Semaphore(MAX_AVAILABLE, true);
	//	final Lock
		
		final List<String> employees = Parser.employees;
		
		
		String output;//String is immutable so no need to synchronization
		
		 final int NTHREADS = Parser.nrOfThreads;
		 System.out.println(NTHREADS);
		    final ExecutorService exec
		            = Executors.newFixedThreadPool(NTHREADS);
		    
		  // int i = 0;
	BoundedHashSet<String> bathroom=new BoundedHashSet<>(MAX_AVAILABLE);
		    
	int timeout = 10;
		    
		for(int i = 0;i < NTHREADS; i++){
		    exec.execute(new Employee(i,bathroom,employees.get(i)));
			// new Thread(new Employee(i,bathroom,employees.get(i))).start();
		}
	try {
			exec.shutdown();
			exec.awaitTermination(timeout, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(exec.isTerminated()){
	   
		//Thread.sleep(5000);
		System.out.println("->>>>>>>>>>");	
		System.out.println(bathroom.getHistory());
		
		}
				
				//@Override
				//public void run() {
					
				//System.out.println(employees.get(i));
			//	synchronized (this) {
					//i++;
				//	}
				//}
			//});
		
	
	
	
	}
}
	


