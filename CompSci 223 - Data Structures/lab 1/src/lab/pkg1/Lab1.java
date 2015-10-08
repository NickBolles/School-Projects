/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab.pkg1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Nicholas
 */
public class Lab1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the zoo!");
        ArrayList animals = new ArrayList();
        populateAnimals(animals);
        boolean quit = false;
        while (!quit){
            System.out.println("=================================");
            System.out.println("What would you like to do?");
            System.out.println("1) Add an Animal to the zoo");
            System.out.println("2) See how many Animals there are in the zoo");
            System.out.println("3) Make each Animal Speak");
            System.out.println("4) Make animals Crawl");
            System.out.println("5) Make animals Walk");
            System.out.println("6) Make animals Walk and Crawl");
            System.out.println("7) Remove an Animal");
            int input = promptForInt();
            boolean valid = false;
            while(!valid){
                switch(input){
                    case 1:
                        createNewAnimal(animals);
                        valid = true;
                        break;
                    case 2:
                        System.out.println("There are " + Animal.numAnimal + " Animals in the zoo");
                        valid = true;
                        break;
                    case 3:
                        speak(animals);
                        valid = true;
                        break;
                    case 4:
                        crawlAnimals(animals);
                        valid = true;
                        break;
                    case 5:
                        walkAnimals(animals);
                        valid = true;
                        break;
                    case 6:
                        crawlAndWalkAnimals(animals);
                        valid = true;
                        break;
                    case 7:
                        deleteAnimal(animals);
                        valid = true;
                        break;
                    default:
                        valid = false;
                        System.out.println("Error: Invalid Entry. Try Again");
                    break;

                }
            }
            
        }
        
        
    }
    public static void populateAnimals(ArrayList animals){
        animals.add(new Dog("Dog 1"));
        animals.add(new Dog("Dog 2"));
        animals.add(new Cat("Cat 1"));
        animals.add(new Cat("Cat 2"));        
        animals.add(new Snake("Snake 1"));    
        animals.add(new Snake("Snake 2"));    
        animals.add(new Lizard("Lizard 1"));  
        animals.add(new Lizard("Lizard 2"));
    }
    public static void createNewAnimal(ArrayList animals){
        System.out.println("What Type of Animal Would you Like to create?");
        System.out.println("1) A Dog");
        System.out.println("2) A Cat");
        System.out.println("3) A Lizard");        
        System.out.println("4) A Snake");
        
        int selection = promptForInt();
        Scanner s=new Scanner(System.in);
        System.out.println("Enter The Name of the Animal");
        String name = s.nextLine();
        
        boolean valid = false;
        while (!valid){
            switch(selection){
                case 1:
                    animals.add(new Dog(name));
                    valid = true;
                    break;
                case 2:
                    animals.add(new Cat(name));
                    valid = true;
                    break;
                case 3:
                    animals.add(new Lizard(name));
                    valid = true;
                    break;
                case 4:
                    animals.add(new Snake(name));
                    valid = true;
                    break;
                default:
                    valid = false;
                    System.out.println("Error: Invalid Entry. Try Again");

                    break;
            }
        }

    }
    public static void deleteAnimal(ArrayList animals){
        System.out.println("Which Animal Would you like to delete?");
        Iterator it = animals.iterator();
        int count = 0;
        while(it.hasNext()){
            count++;
            Animal animal = (Animal) it.next();
            System.out.println(count + ") " + animal.getName());
        }
        
        boolean v = false;
        while (!v){
            int p =promptForInt();
            if (p < count){
                v = true;
                animals.remove(p-1);
                System.out.println("Deleting animal " + p);
            }
        }

    }
    public static void speak(ArrayList animals){
        Iterator it = animals.iterator();
        while(it.hasNext()){
            Animal animal = (Animal) it.next();
            System.out.println(animal.speak());
        }
    }
    public static void walkAnimals(ArrayList animals){
        Iterator it = animals.iterator();
        while(it.hasNext()){
            Animal animal = (Animal) it.next();
            if (animal instanceof Walks){
                Walks w = (Walks) animal;
                w.walk();
            }                
        }
    }
    public static void crawlAnimals(ArrayList animals){
        Iterator it = animals.iterator();
        while(it.hasNext()){
            Animal animal = (Animal) it.next();
            if (animal instanceof Crawls){
                Crawls c = (Crawls) animal;
                c.crawl();
            }                
        }
    }
    public static void crawlAndWalkAnimals(ArrayList animals){
        Iterator it = animals.iterator();
        while(it.hasNext()){
            Animal animal = (Animal) it.next();
            if (animal instanceof Walks){
                Walks w = (Walks) animal;
                if (w instanceof Crawls){
                    Crawls c = (Crawls) w;
                    c.crawl();
                    w.walk();
                } 
            }                
        }
    }
    public static int promptForInt(){
        boolean inputValid=false;
        int num=0;
        do{ //Loop to make sure input is valid
            Scanner numScanner=new Scanner(System.in);
            try{                   
                num = numScanner.nextInt();
                inputValid = true;
            //catch the error if the input isn't an Int
            }catch(java.util.InputMismatchException e){
            	//provide an error message to the user and DONT 
            	//modify inputValid so that they have a chance to start again
                System.out.println("Please Enter Only Numbers");
            }
        }while (inputValid==false); //End Valid Input do-while Loop
        return num;
    }
    
}
