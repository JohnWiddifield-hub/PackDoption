package edu.ncsu.csc216.packdoption.model.io;

import java.io.File;
import java.io.PrintStream;

import edu.ncsu.csc216.packdoption.model.rescue.Rescue;
import edu.ncsu.csc216.packdoption.model.rescue.RescueList;

/**
 * This class gives the ability to write a list of rescues with details of each rescue to a file
 * 
 * @author John Widdifield
 *
 */
public class PackDoptionWriter {
	
	/**
	 * Writes a Rescue List to a specified file
	 * @param fileName name of the file you would like to write to
	 * @param list List of the Rescues you would like to save to a file
	 * @throws IllegalArgumentException if any error or incorrect formatting problems arise
	 */
	public static void writeRescueFile(String fileName, RescueList list) {
		try {
			 PrintStream fileWriter = new PrintStream(new File(fileName));
			 if(list.size() == 0) {
				 fileWriter.close();
				 return;
			 }
			 for (int i = 0; i < list.size(); i++) {
		        	Rescue r = list.getRescue(i);
		        	if(i > 0) {
		        		fileWriter.println("");
		        	}
		            fileWriter.println("# " + r.toString());
		            
		            for(int j = 0; j < r.numAnimals(); j++) {
		            	fileWriter.println(r.getAnimal(j).getIOString());
		            }
		            if(i < list.size() - 1) {
		            	fileWriter.println(r.getAppointmentsIOString());
		            } else {
		            	fileWriter.print(r.getAppointmentsIOString());
		            }
		        }
			 fileWriter.println("");
			 fileWriter.println("");
		        fileWriter.close();
			} catch (Exception e) {
				throw new IllegalArgumentException("Unable to save file.");
			}
	}
}