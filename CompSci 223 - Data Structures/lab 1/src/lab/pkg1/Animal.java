/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab.pkg1;

/**
 *
 * @author Nicholas
 */
public abstract class Animal {
    static int numAnimal = 0;
    private String name;
    Animal(String name){
        numAnimal++;
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public abstract String speak();
}
