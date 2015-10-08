/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

/**
 *
 * @author Nicholas
 */

class animal{
    private String speech;
    public String name;
    //constructor
    public animal(String name, String speech){
        this.name = name;
        this.speech = speech;
    }
        
    public void speak(){
        System.out.println("The " + getName() + "goes: " + getSpeech());
    }
    public String getName(){
        return name;
    }
    public String getSpeech(){
        return speech;
    }

    

}
public class Lab9 {
    public String response;
    
    
    public static void main(String[] args) throws java.io.IOException{
           boolean quit=false;
           animal[] animals = new animal[3];
           animals[0] = new animal("Dog", "woof");
           animals[1] = new animal("cat", "meow");
           int i =0;
       while (quit=true){
        System.out.println("Select which animal you would like to visit");
        for (i=0;i<2;i++){
           System.out.println(i + ") " + animals[i].getName());
        }
            
            
       int selection=(int) System.in.read(); System.in.read();
       switch (selection >1){
           case '1':
               animals[selection].speak();
               break;
           default:
               System.out.println("invalid entry please enter 0 or 1");
               break;
       }
       
           
        }
    }    

}