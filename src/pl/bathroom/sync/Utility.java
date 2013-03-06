package pl.bathroom.sync;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * saving output to file
 * @author Tomek
 *
 */
public class Utility {

	public static void saveOutput(String path, String text){
		try{
			  // Create file 
			  FileWriter fstream = new FileWriter(path);
			  BufferedWriter out = new BufferedWriter(fstream);
			  String[] toSave = text.split("  ");
			  for(String s : toSave){
				  out.write(s);
				  out.newLine();
			  }
			  
			  
			  //Close the output stream
			  out.close();
			  }catch (Exception e){//Catch exception if any
			  System.err.println("Error: " + e.getMessage());
			  }
		
	}
	
}
