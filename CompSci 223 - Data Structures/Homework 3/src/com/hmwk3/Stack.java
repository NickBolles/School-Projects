package com.hmwk3;

import java.util.ArrayList;

public class Stack<E> {

    ArrayList<E> data = new ArrayList<E>();
    Stack(){
    }
    Stack(E[] array){
        for (E item: array){
            this.data.add(item);
        }
    }

    public E pop(){
        //Remove the element from the ArrayList. The Arraylist
        // remove function returns the element removed so just return that
        if (this.data.size() ==0){
            throw new EmptyStackException();
        }
        return data.remove(data.size()-1);
    }
    public E peek(){
        //make sure that the size isnt 0
        if (this.data.size() ==0){
            throw new EmptyStackException();
        }
        //get the last element in the arraylist
        return data.get(data.size() - 1);
    }
    public void push(E n){
        //Add the element to the end of the Arraylist
        data.add(n);
    }
    public String toString(){
        String str = data.toString();
        return str;
    }


    public static void main(String[] args) {
	    // write your code here
        Integer[] i = {1,2,3,4,5,6,7,8,9};
        Stack<Integer> s = new Stack<Integer>(i);
        System.out.println("Original Stack");
        System.out.println(s.toString() + " \n");
        System.out.println("Peek the top element");
        System.out.println(s.peek() + " \n");
        System.out.println("Push 10 to the stack");
        s.push(10);
        System.out.println(s.toString() + " \n");
        System.out.println("Pop the last item from the stack");
        System.out.println(s.pop() + " \n");
        System.out.println(s.toString());

        System.out.println("=======================================");
        System.out.println("                Queue");
        // write your code here
        Integer[] ii = {1,2,3,4,5,6,7,8,9};
        Queue<Integer> q = new Queue<Integer>(ii);
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
        System.out.println(q.toString() + " \n");
        System.out.println("Remove: Retrieve and remove the head of the queue, throws EmptyQueueException if empty");
        try{
            System.out.println(q.remove() + " \n");
            System.out.println(q.toString() + " \n");
        }catch(EmptyQueueException e){
            System.out.println(e.getMessage());
        }
    }
}
