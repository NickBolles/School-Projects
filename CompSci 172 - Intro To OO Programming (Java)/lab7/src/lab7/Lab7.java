/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7;
import java.io.*;
import java.util.*;

public class Lab7 {


    public static void main(String[] args) {
 
        
        Scanner howmany = new Scanner(System.in);
        
        System.out.print("how many sets do you want to compare?: ");
        int h = howmany.nextInt();
        
        //declaring arrays and variables
        int set1[][]= new int[h][2];
        int set2[][]= new int[h][2];
        double distance[]=new double[h];
        int i = 0;
        
        
        for (i=0; i<h; i++){
            set1[i][0]= (int) (Math.random() * 20);
            set1[i][1]= (int) (Math.random() * 20);
            //troubleshooting
            //System.out.println("Set 1 [" + i + "] [1] is: " + set1[i][0]);
            //System.out.println("Set 1 [" + i + "] [2] is: " + set1[i][1]);
            set2[i][0]= (int) (Math.random() * 20);
            set2[i][1]= (int) (Math.random() * 20);
            //troubleshooting
            //System.out.println("Set 2 [" + i + "] [1] is: " + set2[i][0]);
            //System.out.println("Set 2 [" + i + "] [2] is: " + set2[i][1]);
            double x = set2[i][0] - set1[i][0];
            double y = set2[i][1] - set1[i][1];
            
            //finding distance
            distance[i]=Math.sqrt((Math.pow(x,2)) + (Math.pow(y,2)));
            
            
        }
        System.out.println("set 1 \t set2 \t \t distance");
        for (i=0; i<h; i++){
            System.out.println("(" + set1[i][0] + "," + set1[i][1] + ") \t (" + set2[i][0] + "," + set2[i][1] + ") \t" + distance[i]);
        }
    }       
}
