package projectFour;
/**
 * RecLinkedList.java
 * COSC 310 project 4
 * 
 * This is a custom linked List class with some recursive implementation of some functionalities
 * 
 * @author Nathaniel Churchill
 * Professor: Waleed Farag 
 * Date: 4/11/2017
 */


import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class RecLinkedList<E> implements Iterable<E> {
	
	private int n;        // number of elements on list
    private Node pre;     // sentinel before first item
    private Node post;    // sentinel after last item

    public RecLinkedList() {
        pre  = new Node();
        post = new Node();
        pre.next = post;
        post.prev = pre;
        post.next = null;
    }

    // linked list node helper data type
    public class Node {
        private E item;
        private Node next;
        private Node prev;
    }

    public boolean isEmpty()    { return n == 0; }
    public int size()           { return n;      }

    // add the item to the list
    public void add(E item) {
        Node last = post.prev;
        Node x = new Node();
        x.item = item;
        x.next = post;
        x.prev = last;
        post.prev = x;
        last.next = x;
        n++;
    }
    
    
    
    
    //************************************************************************Recursive Methods*************************************************************************
    
    /**
     * This is a recursive add method for adding an element in the middle of the list simply provide the Node paremeter with null
     * @param index the index at which to add the data
     * @param data
     * @param currentNode must always supply null, necessary for recursion
     */
	public void recAdd(int index, E data, Node currentNode){
		//a test for the range
		if (index < 0 || index > n-1){
			throw new IndexOutOfBoundsException();
		}
    	if(currentNode == null){
    		currentNode =pre;
    	}
    	
    	//base case
    	if ( index == 0) {
    		Node newNode = new Node();
    		newNode.item = data;
    		newNode.next = currentNode.next;
    		newNode.prev = currentNode;
    		currentNode.next.prev = newNode;
    		currentNode.next = newNode;
    		n++;
    		return;
    		//else do more recursion
    	} else {
    		
    		recAdd(index -1, data, currentNode.next);
    	}
    }
	
    
    /**
     * This is a recursive size method for finding the size of the list because of the way my linkedlist class is made 
     * it may seem a little different but it works about the same. Must pass the method null in order to work properly.
     * @param currentNode must always supply null, necessary for recursion
     * @return the size of the linked list class
     */
    public int recSize(Node currentNode){
    	if(currentNode == null){
    		currentNode = pre.next;
    	}
    	//base case
    	if (currentNode.equals(post)){
    		return 0;
    	}else {
    		//doing more recursion
    		return 1 + recSize(currentNode.next);
    	}
    }
    
    
    /**
     * A Recursive get method 
     * @param index the index of the wanted element
     * @param currentNode must always supply null, necessary for recursion
     * @return the data
     */
    public E recGet(int index, Node currentNode){
    	if (index < 0 || index > n-1){
			throw new IndexOutOfBoundsException();
		}
    	
    	if(currentNode == null){
    		currentNode = pre.next;//setting current to the head
    	}
    	//base case
    	if ( index == 0) {
    		return currentNode.item;
    
    	} else{//do more recursion
    		return recGet(index -1, currentNode.next);
    	}    	
    }
    /**
     *  Recursive remove method with index
     * @param index
     * @param data
     * @param currentNode must leave null for input.  necessary for recursion
     */
    public void recRemove(int index, Node currentNode){
		//a test for the range
		if (index < 0 || index > n-1){
			throw new IndexOutOfBoundsException();
		}
    	if(currentNode == null){
    		currentNode =pre;
    	}
    	
    	//base case
    	if ( index == 0) {
    		
    		currentNode.next.prev = currentNode.prev;
    		currentNode.prev.next = currentNode.next;
    		n--;
    		return;
    		//else do more recursion
    	} else {
    		
    		recRemove(index -1, currentNode.next);
    	}
    }
    
    
    //************************************************************end Recursive methods****************************************************************************************
    

    /**
     * Iterator
     */
    public ListIterator<E> iterator()  { return new RecLinkedListIterator(); }

    // assumes no calls to RecLinkedList.add() during iteration
    private class RecLinkedListIterator implements ListIterator<E> {
    	public RecLinkedListIterator(){
    		
    	}
    
    	
        private Node current      = pre.next;  // the node that is returned by next()
        private Node lastAccessed = null;      // the last node to be returned by prev() or next()
                                               // reset to null upon intervening remove() or add()
        private int index = 0;

        public boolean hasNext()      { return index < n; }
        public boolean hasPrevious()  { return index > 0; }
        public int previousIndex()    { return index - 1; }
        public int nextIndex()        { return index;     }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastAccessed = current;
            E item = current.item;
            current = current.next; 
            index++;
            return item;
        }
        
        /**
         * Previous method
         */
        public E previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            current = current.prev;
            index--;
            lastAccessed = current;
            return current.item;
        }

        // replace the item of the element that was last accessed by next() or previous()
        // condition: no calls to remove() or add() after last call to next() or previous()
        public void set(E item) {
            if (lastAccessed == null) throw new IllegalStateException();
            lastAccessed.item = item;
        }

        // remove the element that was last accessed by next() or previous()
        // condition: no calls to remove() or add() after last call to next() or previous()
        public void remove() { 
            if (lastAccessed == null) throw new IllegalStateException();
            Node x = lastAccessed.prev;
            Node y = lastAccessed.next;
            x.next = y;
            y.prev = x;
            n--;
            if (current == lastAccessed)
                current = y;
            else
                index--;
            lastAccessed = null;
        }

        /**
         * regular add method for adding at the end of the list
         */
        public void add(E item) {
            Node x = current.prev;
            Node y = new Node();
            Node z = current;
            y.item = item;
            x.next = y;
            y.next = z;
            z.prev = y;
            y.prev = x;
            n++;
            index++;
            lastAccessed = null;
        }

    }
    
    
    /**
     * Custom to string method for printing the list
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (E item : this)
            s.append(item + " ");
        return s.toString();
    }
	
}
