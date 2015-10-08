/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab10;

/**
 *
 * @author Nicholas
 */


class Lab10 {

   //data used to create random animals

   static String table[][] ={

      {"dog",  "woof"},

      {"cow",  "moo"},

      {"duck","quack"},

      {"owl", "whoooo"},

      {"cat",  "meow"},

      {"bird","tweet"},

      {"pig",  "oink"},

      {"rat", "squeak"},

      {"horse", "neigh"},

      {"sheep","ba"},

      {"person", "bla"},

      {"snake", "hiss"},

      {"chicken", "cluck"},

      {"frog", "ribbit"},

      {"donkey", "hee haw"},

      {"bear", "grr"},

      {"lion", "raaar"}

   };

   //create a random animal using the table above and return the created animal.

   static Animal makeRandomAnimal(){

      int index =(int)(Math.random()*17);

      return new Animal(table[index][0], table[index][1]);

   }

   public static void main(String[] args){
        

       Zoo[] zoos= new Zoo[3];
      //      Animal Haven Zoo, Milwaukee County Zoo, Bear Den Zoo

       zoos[1].setName("Animal Haven Zoo") ;
       zoos[2].setName("Milwaukee County Zoo") ;
       zoos[3].setName("Bear Den Zoo") ;
       
       
       
      		for (int i=0;i<3;i++){
			for(int j=0; j<Zoo.getAnimalCount;j++){
				zoos[i].addAnimal(makeRandomAnimal());
                        }
                }

       

      //      The number of animals in a zoo is between 1 and Zoo.CAPACITY

      //TODO: visit all animals in each zoo 

      //TODO: feed the first animal in each zoo

   }

}
