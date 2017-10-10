package projectFour;

import java.util.LinkedList;

/**
 * COSC 310 
 * HelpLists.java
 * 
 * this a helper class for linked list
 * @author Nathaniel Churchill
 * Professor: Waleed Farag
 * Date 4/11/2017
 *
 */
public class HelpLists {
	/**
	 * The methed uses a stack as implemented in the java LinkedList class USES DEQUE IN LINKED LIST 
	 * @param strings a linked list with strings
	 */
	public void reverse(RecLinkedList<String> strings){
		LinkedList<String> stringStack = new LinkedList<>();
		for(String element : strings){
			stringStack.push(element);
		}
		System.out.println("Printing in reverse order from the stack implemented in the reverse method");
		
		while(!(stringStack.isEmpty())){
			System.out.println(stringStack.pop());
		}
	}
	
	/**
	 * the method uses a QUEUE to as implement in the java LinkedList class  USES DEQUEUE IN LINKED LIST
	 * @param strings
	 */
	public void FIFO(RecLinkedList<String> strings){
		LinkedList<String> stringQueue = new LinkedList<>();
		for(String element : strings){
			stringQueue.addLast(element);
		}
		System.out.println("Printing in order from the QUEUE.");
		while(!(stringQueue.isEmpty())){
			System.out.println(stringQueue.removeFirst());
		}
		
	}
	

}
