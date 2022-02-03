package project10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class that takes in sources from a .txt file to turn into a bibliography
 * Makes an ArrayList of AbstractBibEntry objects (including child objects BookBibEntry
 * and JournalBibEntry) then sorts the ArrayList and prints them out in order and in APA 
 * and MLA format
 * @author Justin Homme
 * @version 04/23/21
 */
public class FileReading {

	public static void main(String[] args) {
		//File format:
		//First line says Book or Journal
		//Then: title, author 1 first name, author 1 last name, author 2 first name, 
		//author 2 last name, all as Strings, and all on their own lines.
		//authors that don't exist have 2 blank lines
		//then a line with either true or false on it (for etal)
		//If the item is a book, the next four lines will be a publisher (as a String), 
		//the year (as an int), the city (as a String) and the state (as a String)
		//If the item is a journal article, the next 7 lines will be the journal name (as a String), 
		//the year, the issue, volume, start page and end page (as ints) and the DOI (as a String)
		//the end of the file is the end of the bibliography
		ArrayList<AbstractBibEntry> bibliographyAL = new ArrayList<AbstractBibEntry>();

		try {  //in a try block to catch the possible exceptions thrown
			Scanner input = new Scanner(new File("BibData.txt"));		
			
			while(input.hasNext()) {  //goes until there is nothing left to read in
				String temp = input.nextLine(); //used to determine if the following is a Book or journal
				if(temp.equals("Book")) {
					String title = input.nextLine();
					String author1_fname = input.nextLine();
					String author1_lname = input.nextLine();
					String author2_fname = input.nextLine();
					String author2_lname = input.nextLine();
					boolean etal = input.nextBoolean();
					input.nextLine(); //helps to get from boolean value to the next line
					String publisher = input.nextLine();
					int year = input.nextInt();
					input.nextLine(); //helps to get from int to next line
					String city = input.nextLine();
					String state = input.nextLine();
					
					try {
						BookBibEntry bookEntry = new BookBibEntry(title, author1_fname, author1_lname, author2_fname, author2_lname, etal,
								publisher, year, city, state);  //constructs a BookBibEntry object with the information read in
					
						bibliographyAL.add(bookEntry);  //adds the book object to the ArrayList
					}catch(IllegalArgumentException ex) {
						System.out.println(ex.getMessage() + "... skipping item");
					}
					
				}else if(temp.equals("Journal")) {
					String title = input.nextLine();
					String author1_fname = input.nextLine();
					String author1_lname = input.nextLine();
					String author2_fname = input.nextLine();
					String author2_lname = input.nextLine();
					boolean etal = input.nextBoolean();
					input.nextLine(); //helps to get from boolean to next line
					String jName = input.nextLine();
					int year = input.nextInt();
					input.nextLine(); //helps to get from int to next line
					int issue = input.nextInt();
					input.nextLine(); //helps to get from int to next line
					int volume = input.nextInt();
					input.nextLine(); //helps to get from int to next line
					int pageStart = input.nextInt();
					input.nextLine(); //helps to get from int to next line
					int pageEnd = input.nextInt();
					input.nextLine(); //helps to get from int to next line
					String dOI = input.nextLine();
					try {
						JournalBibEntry journalEntry = new JournalBibEntry(title, author1_fname, author1_lname, author2_fname, author2_lname, etal,
								jName, year, issue, volume, pageStart, pageEnd, dOI);  //constructs a JournalBibEntry with the information read in
						bibliographyAL.add(journalEntry);  //adds the journal object to the ArrayList
					}catch(IllegalArgumentException ex) {
						System.out.println(ex.getMessage() + "... skipping item");
					}
					
				}else {
					throw new RuntimeException("The entry type has to be either Book or Journal");
				}
			}
			
			Collections.sort(bibliographyAL);  //Sorts the ArrayList into alphabetical order
			
			System.out.println("APA bibliography:");
			for(int i = 0; i < bibliographyAL.size(); i++) {
				System.out.println(bibliographyAL.get(i).getAPAString());
			} //prints out all the citations in APA format
			
			System.out.println("MLA bibliography:");
			for(int i = 0; i < bibliographyAL.size(); i++) {
				System.out.println(bibliographyAL.get(i).getMLAString());
			} //prints out all the citaitons in MLA format
			
		}catch(FileNotFoundException e) {
			System.out.println("Couldn't find that file. Quitting Program");
		}catch(InputMismatchException e) {
			System.out.println("The file was not formatted as expected. Quitting program");
		}catch(RuntimeException e) {
			System.out.println("The entry type has to be either Book or Journal. Quitting program");
		}
	}
}
