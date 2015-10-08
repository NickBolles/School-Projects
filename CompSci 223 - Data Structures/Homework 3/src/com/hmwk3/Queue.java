package com.hmwk3;

import java.util.ArrayList;

public class Queue<E> {

    ArrayList<E> data = new ArrayList<E>();
    Queue(){}
    Queue(E[] array){
        for (E item: array){
            this.data.add(item);
        }
    }

    public E element(){
        //if there arent any elements in the queue throw an emptyQueueException
        if (this.data.size() ==0){
            throw new EmptyQueueException();
        }
        //return but dont remove the first element
        return data.get(0);
    }
    public E peek(){
        //
        if (this.data.size() == 0){
            return null;
        }
        return data.get(0);
    }
    public E poll(){
        //Remove the element from the ArrayList. The Arraylist
        // remove function returns the element removed so just return that
        // if the queue is empty return null
        if (this.data.size() ==0){
            return null;
        }
        return data.remove(0);
    }
    public E remove(){
        //Remove the element from the ArrayList. The Arraylist
        // remove function returns the element removed so just return that
        // if the queue is empty throw an emptyQueueException
        if (this.data.size() ==0){
            throw new EmptyQueueException();
        }
        return data.remove(0);
    }
    public void add(E n){
        //Add the element to the end of the Arraylist
        data.add(n);
    }
    public String toString(){
        String str = data.toString();
        return str;
    }


    public static void main(String[] args) {
        Integer[] i = {1,2,3,4,5,6,7,8,9};
        Queue<Integer> q = new Queue<Integer>(i);
        System.out.println("Original Stack");
        System.out.println(q.toString() + " \n");
        System.out.println("Element: Retrieves, but does not remove, the head of this queue. Throws EmptyQueue Exception if empty");
        try{
            System.out.println(q.element() + " \n");
        }catch(EmptyQueueException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Peek: Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.");
        System.out.println(q.peek() + " \n");
        System.out.println("Add: Add 10 to the end of the queue");
        q.add(10);
        System.out.println(q.toString() + " \n");
        System.out.println("Poll: Retrieve and remove the head of the queue, returns null if empty");
        System.out.println(q.poll() + " \n");
        System.out.println("Remove: Retrieve and remove the head of the queue, throws EmptyQueueException if empty");
        try{
            System.out.println(q.remove() + " \n");
        }catch(EmptyQueueException e){
            System.out.println(e.getMessage());
        }
    }
}
