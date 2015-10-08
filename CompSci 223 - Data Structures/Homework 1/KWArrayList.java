/*<listing chapter="2" section="3" sequence="1">*/
package KW.CH02;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * This class implements some of the methods of the Java
 *  ArrayList class.
 *  @author Koffman & Wolfgang
 */
public class KWArrayList<E>
        /*<exercise chapter="2" section="9" type="programming" number="1">*/
        extends AbstractList<E>
        /*</exercise>*/
{
    // Data Fields

    /** The default initial capacity */
    private static final int INITIAL_CAPACITY = 10;
    /** The underlying data array */
    private E[] theData;
    /** The current size */
    private int size = 0;
    /** The current capacity */
    private int capacity = 0;

    /**
     * Construct an empty KWArrayList with the default
     * initial capacity
     */
    public KWArrayList() {
        super();
        capacity = INITIAL_CAPACITY;
        theData = (E[]) new Object[capacity];
    }

 

    /**
     * Add an entry to the data inserting it before the
     * item at the specified index.
     * @param index - The index of the time that the new
     *        value it to be inserted in front of.
     * @param theValue - The value to be inserted
     * @throws ArrayIndexOUtOfBoundsException if index is
     *         less than zero or greater than size
     */
    public boolean add(E anEntry) {
        if (size == capacity) {
            reallocate();
        }
        theData[size] = anEntry;
        size++;
        return true;
    }

    /**
     * Get a value in the array based on its index.
     * @param index - The index of the item desired
     * @return The contents of the array at that index
     * @throws ArrayIndexOutOfBoundsException - if the index
     *         is negative or if it is greater than or equal to the
     *         current size
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return theData[index];
    }

    /**
     * Set the value in the array based on its index.
     * @param index - The index of the item desired
     * @param newValue - The new value to store at this position
     * @return The old value at this position
     * @throws ArrayIndexOutOfBoundsException - if the index
     *         is negative or if it is greater than or equal to the
     *         current size
     */
    public E set(int index, E newValue) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E oldValue = theData[index];
        theData[index] = newValue;
        return oldValue;
    }

    /**
     * Remove an entry based on its index
     * @param index - The index of the entry to be removed
     * @return The value removed
     * @throws ArrayIndexOutOfBoundsException - if the index
     *         is negative or if it is greater than or equal to the
     *         current size
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E returnValue = theData[index];
        for (int i = index + 1; i < size; i++) {
            theData[i - 1] = theData[i];
        }
        size--;
        return returnValue;
    }

    /**
     * Allocate a new array to hold the directory
     */
    private void reallocate() {
        capacity = 2 * capacity;
        theData = Arrays.copyOf(theData, capacity);
    }

    /**
     * Get the current size of the array
     * @return The current size of the array
     */
    public int size() {
        return size;
    }
    
    //HW #1 STARTS HERE =======================================================
    
    /**
     * Construct an empty KWArrayList with a specified initial capacity
     * @param capacity The initial capacity
     */
    public KWArrayList(int capacity) {
        super();
        this.capacity = capacity;
        this.theData = (E[]) new Object[this.capacity];
    }
  

    /**
     * Returns the index of the first occurence of the specified element
     * in this list, or -1 if this list does not contain the element
     * @param item The object to search for
     * @returns The index of the first occurence of the specified item
     *          or -1 if this list does not contain the element
     */
    public int indexOf(Object item) {
        for(int i=0;i<this.size()-1;i++){
            if (this.get(i).equals(item)){
                return i;
            }
        }
        return -1;
    }
    
    public static ArrayList reverse(ArrayList l){
        ArrayList r = new ArrayList(l.size());
        for (int i = l.size()-1; i >=0; i--) {
            r.add(l.get(i));
        }	
        return r;                
    }
    
    public static void remove(ArrayList l, Object target){
        Iterator i = l.iterator();
        while(i.hasNext()){
            if (i.next().equals(target)){
                l.remove(i);
            }
        }
    }
    
    public static ArrayList<Integer> arrayDouble(ArrayList<Integer> l){
        ArrayList<Integer> j = new ArrayList<Integer>();
        for(int i=0;i<l.size()-1;i++){
            j.add(l.get(i*2));
        }
        return j;
    }
    
    public static void main(String[] args){
        //test your methods here to make sure they work.
        KWArrayList<Integer> l = new KWArrayList();
        l.add(0);
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);
        l.add(6);
        
        l.indexOf(0);
        l.indexOf(4);
        
        
        
    }
}
/*</listing>*/


