/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

/**
 *
 * @author Nicholas
 */
public class Die {
    private int sides, value;
    
    
    Die(){
        value=0;
        sides=6;
    }
    
    /**
     *
     * @return
     */
    public int roll(){
        value = (int) ((Math.random()*6) + 1);
        
        return value;
    }

    
    public void display(){
        String asciiArt[] = new String[6];
        asciiArt[0] = "  -----\n |     |\n |  o  |\n |     |\n  ----- Oh no! It's a pig!";
        asciiArt[1] = "  -----\n |   o |\n |     |\n | o   |\n  ----- It's a 2!";
        asciiArt[2] = "  -----\n |   o |\n |  o  |\n | o   |\n  ----- It's a 3!";
        asciiArt[3] = "  -----\n | o o |\n |     |\n | o o |\n  ----- It's a 4!";
        asciiArt[4] = "  -----\n | o o |\n |  o  |\n | o o |\n  ----- It's a 5!";
        asciiArt[5] = "  -----\n | o o |\n | o o |\n | o o |\n  ----- It's a 6!";
    
        System.out.println(asciiArt[value-1]);
    }
            
}
