import java.util.Iterator;
import java.util.NoSuchElementException;
class DoubleLinkedList<E> implements Iterable<E> {

   private static class Node<E> {
      public E data;
      public Node<E> next;
      public Node<E> prev;
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
         Node<E> newNode = new Node<E>(item);
         newNode.prev = n;
         n.next = newNode;
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
         t.prev = head;
      } else if (index == size){
         //insert at end of list
         Node<E> n = head;
         for (int i = 0; i < index - 1; i++) {
            n = n.next;
         }
         Node<E> newNode = new Node<E>(item);
         newNode.prev = n;
         n.next = newNode;
      } else {
         //adding between two nodes 
         Node<E> n = head;
         for (int i = 0; i < index - 1; i++) {
            n = n.next;
         }
         Node<E> t = n.next;
         Node<E> newNode = new Node<E>(item);
         newNode.prev = n;
         newNode.next = t;
         n.next = newNode;
         t.prev = newNode;
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
        //Set the node after the one being removed prev to the node before
        n.next.next.prev = n;
        //Set the element before the element we want to remove to the one after the one we want to remove
        n.next = n.next.next;
        
        size--;
   
   }
   //Exercise: implement this
   public DoubleLinkedList copy() {
       DoubleLinkedList<E> newList = new DoubleLinkedList<E>();
      Node<E> n = head;
        for (int i = 0; i < size; i++) {
           newList.add(n.data); //Wont this be a reference if E is an object? How can we avoid that?
            n = n.next;
        }
        return newList;
   }
   class DoubleLinkedListIterator implements Iterator<E>{
      private Node<E> lastReturnedItem;
      private Node<E> nextItem = DoubleLinkedList.this.head; 
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
         DoubleLinkedList.this.remove(--index);
         lastReturnedItem = null;
      }
      
   }
   
   @Override
   public Iterator<E> iterator() {
      return new DoubleLinkedListIterator();
   }
   //--------------------------------------------------------
   public static void main(String[] args) {
        DoubleLinkedList<Integer> l = new DoubleLinkedList<>();
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
        DoubleLinkedList<Integer> t = l.copy();
        System.out.println("Copy of List " + t);
   }
}

