/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab10;

/**
 *
 * @author Nicholas
 */


class Zoo{

   //max capacity of zoo. the number of animals must not exceed it.  

   public final static int CAPACITY = 30; 

   //name of the zoo

   private String name;

   //set of animals in the zoo

   private Animal animals[];

   //current number of animals in the zoo

   private int animalCount;


   Zoo(String name){

      this.name=name;

   }

   void setName(String name){
       this.name = name;

   }

   String getName(){

      return name;

   }

          
   void setAnimalCount(){
       animalCount= (int) Math.random()*CAPACITY;
       
   }       
   int getAnimalCount(){

      return animalCount;

   }

   //add animal to the zoo, do nothing if capacity is reached.

   void addAnimal(Animal animal){

      //TODO: fill in body of this method

   }

   //return animal at given array index 

   Animal getAnimal(int index){

      //TODO: fill in body of this method

   }

   //visit animal at index, have it speak

   // e.g. the cat says "meow."

   void visitAnimal(int index){

      //TODO: fill in body of this method

   }

   //visit all animals in the zoo, have each speak

   void visitAllAnimals(){

      //TODO: fill in body of this method

   }

}

