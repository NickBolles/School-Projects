/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab8;

import java.io.*;
import java.util.*;

public class Lab8 {

    public static void main(String[] args) throws java.io.IOException{
        System.out.println("Welcome to iCalculator.");
        Scanner s=new Scanner(System.in);
        boolean quit = false;
        double input1, input2;
        int stats[]=new int[5];
        int numadd=0;
        int numsub=0;
        int nummul=0;
        int numdiv=0;
        int numabs=0;
        double answer=0;
         while (!quit){
             System.out.print(">");
             String cmd=s.next();
             switch (cmd){
                 case "add":
                     answer = s.nextDouble()+ s.nextDouble();
                     System.out.println(answer);
                     numadd++;
                     break;
                 case "sub":
                     answer = s.nextDouble()- s.nextDouble();
                     System.out.println(answer);
                     numsub++;
                     break;
                 case "mul":
                     answer = s.nextDouble()* s.nextDouble();
                     System.out.println(answer);
                     nummul++;
                     break;
                 case "div":
                     input1 = s.nextDouble();
                     input2 = s.nextDouble();
                     if(input2 != 0){
                     answer = input1/input2;
                     System.out.println(answer);}
                     else{System.out.println("ERROR: trying to divide by 0");}
                     numdiv++;
                     break;
                 case "abs":
                     answer = Math.abs(s.nextDouble());
                     System.out.println(answer);
                     numabs++;
                     break;
                 case "quit":
                     System.out.println("===================================");
                     System.out.println("Function usage count");
                     System.out.println("add function : " + numadd);
                     System.out.println("sub function : " + numsub);
                     System.out.println("mul function : " + nummul);
                     System.out.println("div function : " + numdiv);
                     System.out.println("abs function : " + numabs);
                     System.out.println("--------------------------------");
                     System.out.println("Thank You For Using iCalculator");

                     quit=true;
                     break;
                 default:
                     System.out.println("unknown command : \" " + cmd + " \" ");
                     break;
             }
         }
    }
}
