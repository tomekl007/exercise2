package pl.bathroom.sync;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

final public class Parser {
	
	static int sequence;
	static boolean firstLine=true;
	static List<String> women=new LinkedList<>();
	static List<String> men=new LinkedList<>();
	static int nrOfThreads;
	
	static List<String> employees=new LinkedList<>();
	
	static void parse(String inputPath){
			
			
	
	  try{
		  // Open the file that is the first 
		  // command line parameter
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
			
			 // if(strLine.startsWith("m"))
			//	  men.add(strLine);
			//  else if(strLine.startsWith("k"))
			//	  women.add(strLine);
			  
		  // Print the content on the console
				  employees.add(strLine);
		  System.out.println (strLine);
		  
		  	nrOfThreads++;
			  }
		  
		  }
		  //Close the input stream
		  in.close();
		    }catch (Exception e){//Catch exception if any
		  System.err.println("Error: " + e.getMessage());
		  }
	
	}
}
