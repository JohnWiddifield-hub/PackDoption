package edu.ncsu.csc216.packdoption.model.io;

import java.io.FileInputStream;
import java.util.Scanner;

import edu.ncsu.csc216.packdoption.model.animals.Animal.Size;
import edu.ncsu.csc216.packdoption.model.animals.Cat;
import edu.ncsu.csc216.packdoption.model.animals.Dog;
import edu.ncsu.csc216.packdoption.model.animals.Dog.Breed;
import edu.ncsu.csc216.packdoption.model.rescue.Rescue;
import edu.ncsu.csc216.packdoption.model.rescue.RescueList;
import edu.ncsu.csc216.packdoption.util.Date;
import edu.ncsu.csc216.packdoption.util.Note;
import edu.ncsu.csc216.packdoption.util.SortedLinkedList;

/**
 * This class allows you to read a formatted text file into a list of Rescues.
 * 
 * @author John Widdifield
 *
 */
public class PackDoptionReader {

	/**
	 * Reads a Rescue List from a specified file
	 * @param fileName name of the file you would like to read from
	 * @return List of Rescues which were included in the file
	 * @throws IllegalArgumentException if any error or incorrect formatting problems arise
	 */
	public static RescueList readRescueListFile(String fileName) {
		RescueList list = new RescueList();
		Rescue rescue = null;
		SortedLinkedList<Note> notes = null;
		String name = "";
		String type = "";
		Date birthday = null;
		Size size = null;
		boolean isHouseTrained;
		boolean isGoodWithKids;
		Date dateEntered = null;
		boolean adopted;
		Date adoptedDate = null;
		String owner = "";
		Breed breed = null;
		int rescueCntr = 0;
		int blankCntr = 0;
		
		try {
			Scanner fileReader = new Scanner(new FileInputStream(fileName));
			while(fileReader.hasNextLine()) {
				String line = fileReader.nextLine();
				if(line.startsWith("#")) {
					line = line.replace("# ", "");
					rescue = new Rescue(line);
					list.addRescue(rescue);
					if(rescueCntr > blankCntr) {
						throw new Exception();
					}
					rescueCntr++;
				} else if(line.startsWith("*")) {
					line = line.replace("* ", "");
					Scanner lineReader = new Scanner(line);
					lineReader.useDelimiter(",");
					type = lineReader.next();
					name = lineReader.next();
					birthday = new Date(lineReader.next());
					size = Size.valueOf(lineReader.next());
					isHouseTrained = Boolean.valueOf(lineReader.next());
					isGoodWithKids = Boolean.valueOf(lineReader.next());
					dateEntered = new Date(lineReader.next());
					if(type.contentEquals("Cat")) {
						String next = lineReader.next();
						if(next.contentEquals("NOTES")) {
							notes = new SortedLinkedList<Note>();
							while(lineReader.hasNext()) {
								String noteStr = lineReader.next();
								Date noteDate = new Date(noteStr.substring(0, noteStr.indexOf(" ")));
								Note animalNote = new Note(noteDate, noteStr.substring(noteStr.indexOf(" ") + 1, noteStr.length()));
								notes.add(animalNote);
							}
							rescue.addAnimal(new Cat(name, birthday, size, isHouseTrained, isGoodWithKids, notes, dateEntered));
						} else {
							adopted = true;
							adoptedDate = new Date(lineReader.next());
							owner = lineReader.next();
							@SuppressWarnings("unused")
							String notesToken = lineReader.next();
							notes = new SortedLinkedList<Note>();
							while(lineReader.hasNext()) {
								String noteStr = lineReader.next();
								Date noteDate = new Date(noteStr.substring(0, noteStr.indexOf(" ")));
								Note animalNote = new Note(noteDate, noteStr.substring(noteStr.indexOf(" ") + 1, noteStr.length()));
								notes.add(animalNote);
							}
							rescue.addAnimal(new Cat(name, birthday, size, isHouseTrained, isGoodWithKids, notes, dateEntered, adopted, adoptedDate, owner));
						}
					} else if(type.contentEquals("Dog")) {
						String next = lineReader.next();
						if(next.contentEquals("true")) {
							adopted = true;
							adoptedDate = new Date(lineReader.next());
							owner = lineReader.next();
							breed = Breed.valueOf(lineReader.next());
							@SuppressWarnings("unused")
							String notesToken = lineReader.next();
							notes = new SortedLinkedList<Note>();
							while(lineReader.hasNext()) {
								String noteStr = lineReader.next();
								Date noteDate = new Date(noteStr.substring(0, noteStr.indexOf(" ")));
								Note animalNote = new Note(noteDate, noteStr.substring(noteStr.indexOf(" ") + 1, noteStr.length()));
								notes.add(animalNote);
							}
							rescue.addAnimal(new Dog(name, birthday, size, isHouseTrained, isGoodWithKids, notes, dateEntered, adopted, adoptedDate, owner, breed));
							
						} else {
							breed = Breed.valueOf(next);
							@SuppressWarnings("unused")
							String notesToken = lineReader.next();
							notes = new SortedLinkedList<Note>();
							while(lineReader.hasNext()) {
								String noteStr = lineReader.next();
								Date noteDate = new Date(noteStr.substring(0, noteStr.indexOf(" ")));
								Note animalNote = new Note(noteDate, noteStr.substring(noteStr.indexOf(" ") + 1, noteStr.length()));
								notes.add(animalNote);
							}
							rescue.addAnimal(new Dog(name, birthday, size, isHouseTrained, isGoodWithKids, notes, dateEntered, breed));
						}
						
					} else {
						throw new Exception();
					}
					lineReader.close();
				} else if(line.startsWith("-")) {
					line = line.replace("- ", "");
					Scanner lineReader = new Scanner(line);
					lineReader.useDelimiter(",");
					String aptName = lineReader.next();
					Date aptBday = new Date(lineReader.next());
					rescue.addAppointment(rescue.getAnimal(aptName, aptBday));
					lineReader.close();
				} else if(line.isEmpty()) {
					blankCntr++;
				}
			}
			fileReader.close();
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		return list;
	}
}
