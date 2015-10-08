package com.project2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by Nicholas on 3/13/2015.
 */
public class Set<E extends Comparable> implements Comparable, Iterable {
    private String name;
    private ArrayList<E> data = new ArrayList<E>();

    @Override
    public Iterator iterator() {
        return new SetIterator();
    }
    private class SetIterator implements ListIterator {
        Integer index = 0;
        SetIterator(){
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return (data.size()>index);
        }
        @Override
        public Object next() {
            return data.get(index++);
        }
        @Override
        public int nextIndex() {
            if (index < data.size()-1){
                return index + 1;
            }
            return -1;
        }

        @Override
        public boolean hasPrevious() {
            return (index >0);
        }

        @Override
        public Object previous() {
            if (index>0){
                return data.get(index-1);
            }
            return null;
        }

        @Override
        public int previousIndex() {
            if (index>0){
                return index-1;
            }
            return -1;
        }

        @Override
        public void remove() {
            data.remove(index);
            index--;
        }

        @Override
        public void set(Object o) {
            data.set(index,(E) o);
        }

        @Override
        public void add(Object o) {
            data.add((E) o);
        }
    }
    /**
     *Constructor for Set
     *
     */
    Set(){}
    /**
     * Constructor for Set
     *
     * @param       name    The name of the Set to be created
     */
    Set(String name){
        this.name = name;
    }
    /**
     * Getter for Name
     *
     * @return              name of the Set
     */
    public String getName() {
        return name;
    }
    /**
     *  Sets the Set name
     * @param       name    Name of the Set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     *  Add an item to the Set
     *
     * @param       item    Item to remove from the Set
     * @return              1 if successful -1 if unsuccessful, if it is a duplicate item
     */
    public int add(E item){
        for(int i=0;i<this.data.size();i++){
            //compare this item to the item being inserted
            int c = this.data.get(i).compareTo(item);
            switch(c){
                case 1:
                    //this item is bigger then the item being inserted so add it at the index
                    this.data.add(i,item);
                    return 1;
                case 0:
                    //This is a duplicate item return -1, for a failure
                    return -1;
            }
        }
        //This is bigger then all elements in the arraylist, or there wasnt anything in the arraylist
        this.data.add(item);
        return 1;
    }
    /**
     *  Get an item at an index from the Set
     *  @param      i       Index of the item to get
     *  @return             E The item at index i
     */
    public E get(int i){
        return data.get(i);
    }
    /**
     *  Removes the item from the Set
     *  @param      item    The Item to be removed from the Set
     *  @return             The Item that is removed from the Set
     */
    public E remove(E item){
        //because ArrayList.remove(Object e) returns a boolean
        // get the index of the object and remove it
        return data.remove(data.indexOf(item));
    }
    /**
     *  Removed the item at the index from the Set
     *  @param      i       The index of the item to be removed
     *  @return             The Item that is removed from the Set
     */
    public E remove(int i){
        return data.remove(i);
    }
    /**
     *  Gets the index of the item in the Set
     *  @param      target  The item to look for in the Set
     *  @return             The index of the target
     */
    public int indexOf(E target){
        return data.indexOf(target);
    }
    /**
     *  Returns the Union of this Set and Set s
     *  @param      s       Set to join with this Set
     *  @return             The Union of this Set and S
     */
    public Set<E> union(Set<E> s){
        //Create the new set
        Set<E> newSet = new Set<E>("Union of " + this.getName() + " and " + s.getName());
        //Get iterators for each Set
        SetIterator i = (SetIterator) this.iterator();
        SetIterator ii = (SetIterator) s.iterator();
        //Add all of the elements in the first set
        while(i.hasNext()){
            newSet.add((E) i.next());
        }
        //Add all of the elements in the second set
        while(ii.hasNext()){
            newSet.add((E) ii.next());
        }
        return newSet;
    }
    /**
     *  Returns the Intersection of this Set and s
     *  @param      s       Set to intersect with this Set
     *  @return             The Intersect of this Set and Set s
     */
    public Set<E> intersect(Set<E> s){
        //Create the new Set
        Set<E> newSet = new Set<E>("Intersect of " + this.getName() + " and " + s.getName());
        //Get Iterator for the set
        SetIterator i = (SetIterator) this.iterator();
        while(i.hasNext()){
            E ti = (E) i.next();
            //Check if the current item is in the other Set
            if (s.indexOf(ti) != -1){
                //if it is (index of is not -1) then add it
                newSet.add(ti);
            }
        }
        return newSet;
    }
    /**
     *  Returns the Subtraction of this Set and s
     *  @param      s       Set to subtract with this Set
     *  @return             The Subtraction of this Set and Set s
     */
    public Set<E> subtract(Set<E> s){
        //Create the new set
        Set<E> newSet = new Set<E>("Subtraction of " + this.getName() + " and " + s.getName());
        //Get Iterator for the Set
        SetIterator i = (SetIterator) this.iterator();
        while(i.hasNext()){
            E ti = (E) i.next();
            //Check if the current item is in the other Set
            if (s.indexOf(ti) == -1){
                //If the item is not in the other set then add it
                newSet.add(ti);
            }
        }
        return newSet;
    }
    /**
     *  Returns the Difference of this Set and s, elements that are not in both
     *  @param      s       Set to compare with this Set
     *  @return             The Difference of this Set and Set s
     */
    public Set<E> difference(Set<E> s){
        //Create the new Set
        Set<E> newSet = new Set<E>("Difference of " + this.getName() + " and " + s.getName());
        //Get an Iterator for each set
        SetIterator i = (SetIterator) this.iterator();
        SetIterator ii = (SetIterator) s.iterator();
        //Check for the first set
        while(i.hasNext()){
            E ti = (E) i.next();
            //Check if this item is in the other set
            if (s.indexOf(ti) == -1){
                //If this item is not in the other set add it to the newSet
                newSet.add(ti);
            }
        }
        //Now check for the second set
        while(ii.hasNext()){
            E ti = (E) ii.next();
            //Check if this item is in the other set
            if (this.indexOf(ti) == -1){
                //If this item is not in the other set add it to the newSet
                newSet.add(ti);
            }
        }
        return newSet;
    }
    /**
     *  Clears this Set
     */
    public void clear(){
        this.data.clear();
    }
    /**
     *  Returns the size of this Set
     *  @return             The size of this Set
     */
    public int size(){
        return this.data.size();
    }

    /**
     *  Returns a String representation of the Set
     *  @return             A String representation of the Set
     */
    public String toString(){
        return this.name + ":" + this.data.toString();
    }

    /**
     *  Determines if this Set is equal to Set s. Does not compare the name
     *  @param      s       Set to compare to this Set
     *  @return             Boolean if this Set is equal to Set s
     */
    public boolean equals(Set<E> s){
        //Do a quick check on the size, This can help reduce wasting time on
        // Sets that are not even the same size, as well as avoid an out of bounds exception
        if (this.data.size() == s.size()){
            //Because we want both of these sets to be identical we can use one index and reduce On^2 to On
            for(int i = 0; i<this.data.size();i++){
                //compare the two items
                if (this.data.get(i).compareTo( s.get(i)) != 0){
                    //The items are not the same
                    return false;
                }
            }
            return true;
        }else{
            //These items can not be equal, they are not the same size.
            return false;
        }
    }
    /**
     *  Compare the Set to another, This will just compare the name alphabetically
     *  @return             -1 this precedes argument string,0 this is the same string, 1 this string is after the argument string
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof Set){
            Set<E> s = (Set<E>) o;
            return this.name.compareToIgnoreCase(s.getName());
        }else{
            return -1;
        }
    }

}
