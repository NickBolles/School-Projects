/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab6;

import java.util.*;

class Lab6{
        public static void main(String[]args){
                Scanner scan= new Scanner(System.in);
                int add = 0;
                int numinline = 0;

                System.out.print("How many Numbers would you like to be generated?  ");
                int ammount = scan.nextInt();


                //initiallizeing array to number wanted
                int[] data = new int[ammount];
               
                
                
                
                for (int i=0; i<ammount; i++){
                        data[i] = (int) (Math.random()*100);
                        //Troubleshooting purposes
                                             
                }
                
                for(int i=0;i<ammount; i++){
                        int value = data[i];
                        System.out.print(value + "\t");
                        numinline++;
                        int nextline = numinline%10;
                        
                        add = value + add;
//                        
                        if (nextline == 0 & i!=0){
                            System.out.println(" ");
                                
                          }
                }
                System.out.println(" ");
                System.out.println("The Sum of all of the numbers is : " + add);
        }   
}