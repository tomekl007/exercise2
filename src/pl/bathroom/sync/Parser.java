package pl.bathroom.sync;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Tomasz Lelek
 * Parsing input file 
 */
final public class Parser {
	
	private static int sequence;
	static boolean firstLine=true;
	static boolean didFirstEmployee=true;
	
	private static int nrOfThreads;
	
	private static List<String> employees=new LinkedList<>();
	
	private static String firstEmployee;
	
	/**
	 * Static method that parse file
	 * @param inputPath - path of file being parsed
	 */
	static void parse(String inputPath){
			
			
	
	  try{
		 
		  FileInputStream fstream = new FileInputStream(inputPath);
		  // Get the object of DataInputStream
		  DataInputStream in = new DataInputStream(fstream);
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  //Read File Line By Line
		  while ((strLine = br.readLine()) != null)   {
			  if(firstLine){
				  sequence=Integer.parseInt(strLine);
				  firstLine=false;
			  }else{
			    if(didFirstEmployee){
			    	firstEmployee=strLine;
			        didFirstEmployee=false;
			    }
				  
			
				  employees.add(strLine);
	
		  
		  	nrOfThreads++;
			  }
		  
		  }
		  //Close the input stream
		  in.close();
		    }catch (Exception e){//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
		  }
	
	}
	
	public static String getFirstEmployee(){
		return firstEmployee;
	}
	
	public static int getNrOfThreads(){
		return nrOfThreads;
	}
	
	public static List<String> getEmplyeesList(){
		return employees;
	}
	
	public static int getSequence(){
		return sequence;
	}
}
