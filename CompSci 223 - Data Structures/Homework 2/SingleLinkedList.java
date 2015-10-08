import java.util.Iterator;
import java.util.NoSuchElementException;
class SingleLinkedList<E> implements Iterable<E> {

   private static class Node<E> {
      public E data;
      public Node<E> next;
      public Node(E item) {
         data = item;
      }
   }
   private Node<E> head;
   private int size;
   public boolean add(E item) {
      //case 1: empty list
      if (head == null) {
         head = new Node<>(item);
      } else {
         //case 2: list is not empty
         Node<E> n = null;
         for (n = head; n.next != null; n = n.next);
         n.next = new Node<E>(item);
      }
      size++;
      return true;
   }
   public void add(int index, E item) {
      if (index < 0 || index >= size) {
         throw new IndexOutOfBoundsException("" + index);
      }
      //adding to the front
      if (index == 0) {
         Node<E> t = head;
         head = new Node<E>(item);
         head.next = t;
      } else if (index == size){
         //insert at end of list
         Node<E> n = head;
         for (int i = 0; i < index - 1; i++) {
            n = n.next;
         }
         Node<E> t = n.next;
         n.next = new Node<E>(item);
      } else {
         //adding between two nodes 
         Node<E> n = head;
         for (int i = 0; i < index - 1; i++) {
            n = n.next;
         }
         Node<E> t = n.next;
         n.next = new Node<E>(item);
         n.next.next = t;
      }
      size++;
   }
   public E get(int index) {
      //check for out of bound index
      if (index < 0 || index >= size) {
         throw new IndexOutOfBoundsException("" + index);
      }
      //find the node and return the data
      Node<E> n = head;
      for (int i = 0; i < index; i++) {
         n = n.next;
      }
      return n.data;
   }
   public int size() {
      return size;
   }
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      //walk through the list, append data to buffer
      for (Node<E> n = head; n != null; n = n.next) {
         sb.append(n.data);
      }
      return sb.toString();
   }
   //--------------------------------------------------------
   //Exercise: implement this
   public E set(int index, E newValue) {
       if (index < 0 || index >= size) {
         throw new IndexOutOfBoundsException("" + index);
      }
        Node<E> n = head;
        for (int i = 0; i < index; i++) {
           n = n.next;
        }
        n.data = newValue;
      
      return newValue;
   }
   //Exercise: implement this
   public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("" + index);
        }
        Node<E> n = head;
        //Get the element before the one we want to remove
        for (int i = 0; i < index-1; i++) {
           n = n.next;
        }   
        //Set the element before the element we want to remove to the one after the one we want to remove
        
        n.next = n.next.next;
        size--;
   
   }
   public SingleLinkedList reverse(){       
       SingleLinkedList<E> newl = this;
       SingleLinkedList<E> newList = new SingleLinkedList<E>();
       while(this.head != null){
           Node<E> temp = this.head.next; //this will be the new head for this
           this.head.next = newList.head; //set the next property of the new head
           newList.head = this.head; //replace the head of the newList, this will 
                                     //point to the next element which was the old head
           this.head = temp; //set the head of this list to the temp node
       }
       return newList;
   }
   //Exercise: implement this
   public SingleLinkedList copy() {
       SingleLinkedList<E> newList = new SingleLinkedList<E>();
      Node<E> n = head;
        for (int i = 0; i < size; i++) {
           newList.add(n.data); //Wont this be a reference if E is an object? How can we avoid that?
            n = n.next;
        }
        return newList;
   }
   class SingleLinkedListIterator implements Iterator<E>{
      private Node<E> lastReturnedItem;
      private Node<E> nextItem = SingleLinkedList.this.head; 
      int index;
      @Override
      public boolean hasNext() {
         return nextItem != null;
      }
      @Override
      public E next() {
         if (nextItem == null) throw new NoSuchElementException();
         lastReturnedItem = nextItem;
         nextItem = nextItem.next; 
         index++;
         return lastReturnedItem.data;
      }
      @Override
      public void remove() {
         if (lastReturnedItem== null) throw new IllegalStateException();
         SingleLinkedList.this.remove(--index);
         lastReturnedItem = null;
      }
      
   }
   
   @Override
   public Iterator<E> iterator() {
      return new SingleLinkedListIterator();
   }
   //--------------------------------------------------------
   public static void main(String[] args) {
        SingleLinkedList<Integer> l = new SingleLinkedList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);
        System.out.println("print content of list using toString");
        System.out.println(l);
        l.remove(3);
        System.out.println("Remove item at index 3 " + l);
        l.set(3, 9);
        System.out.println("Set item at index 3 to 9 " + l);
        SingleLinkedList<Integer> t = l.copy();
        System.out.println("Copy of List " + t);
        SingleLinkedList<Integer> j = new SingleLinkedList<>();
        j.add(1);
        j.add(2);
        j.add(3);
        j.add(4);
        j.add(5);
        j.reverse();
        System.out.println("Reverse of list " + j.toString());
        System.out.println("Destroyed List" + j.toString());
   }
}

