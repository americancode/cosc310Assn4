package projectFour;

import java.io.File;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * COSC 310
 * Driver.java
 * 
 * 
 * This is the driver class for assignment 4 which is mainly for testing the other classes
 * 
 * @author Nathaniel Churchill
 * Professor: Waleed Farag
 * Date: 4/10/2017
 *
 */

public class Driver {
	public static void main(String[] args){
		//creating the console scanner 
		Scanner in = new Scanner(System.in);
		//creating the linked list
		RecLinkedList<String> studentList = new RecLinkedList<>();
		int counter = 0;
		
		//scanning the file on the disk for starting input data
		try {
			Scanner fileIn = new Scanner(new File("StudentData.txt"));
			
			//loop for getting the data from the file
			while (fileIn.hasNextLine()){
				studentList.add(fileIn.next());// adding the string to the list
				counter++;
			}
			
			
		}catch(Exception e){
			System.out.println(e);
		}
		
		
		
		//Output
		System.out.println("                  ################################################################################");
		System.out.println("                  ###############This program was written by Nathaniel Churchill##################");
		System.out.println("                  ################################################################################");

		System.out.println("       Displaying list content using traditional loop in reverse order");
		for (int i = studentList.size() - 1; i >= 0; i--){
			System.out.println("Name of student #" + i + " is " + studentList.recGet(i,null)); // USING RECURSIVE GET
		}
		
		//printing the last element in the list 
		System.out.println("\n\nThe last element in the list is: " + studentList.recGet(studentList.recSize(null) -1, null)); // USING RECURSIVE SIZE
		//printing the first element in the list 
		System.out.println("The first element in the list is: " + studentList.recGet(0,null));
		
		
		
		
		//removing the last element
		System.out.println("\n\nRemoving the last element");
		studentList.recRemove(studentList.recSize(null) -1, null);//RECURSIVEE REMOVE METHOD
		//printing the size of the student list
		System.out.println("The size of the list is: " + studentList.recSize(null) + "\n");
		
		//printing with enhanced for loop
		System.out.println("Printing with enhanced for loop");
		for(String element : studentList){
			System.out.println(element);
		}
		
		//creating the list iterator object
		ListIterator iter = studentList.iterator();
		String temp = null;
		for(int i =0; i < 5; i++){
		      temp = (String)(iter.next());
		}
		System.out.println("\nPrinting the element after advancing the iterator :  " + temp);
	    //calling iter.add
		iter.add("Kelly");
		System.out.println("\nPrinting the list after advancing the iterator and adding kelly");
		System.out.println(studentList.toString());
		
		
		// executing iter.previous
		iter.previous();
		String tempOne = "Nancy";
		iter.set(tempOne);
		System.out.println("\nPrinting the list after advancing the iterator back one and setting Nancy");
		System.out.println(studentList.toString());
		
		//iter.previous
		iter.previous();
		iter.remove();
		System.out.println("\nPrinting the list after advancing the iterator back one and removeing an element");
		System.out.println(studentList.toString());
		
		//making HelpLists object
		HelpLists hl = new HelpLists();
		System.out.println("\nprinting from the student list after executing the reverse method\n");
		hl.reverse(studentList);
		System.out.println("\nprinting from the student after executing the FIFO method\n");
		hl.FIFO(studentList);
		
	}

}
