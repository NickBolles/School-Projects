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
public class Dog extends Animal implements Walks{
    Dog(String name){
        super(name);
    }
    @Override
    public String speak() {
        return "Woof Woof";
    }
    public void walk(){
        System.out.println(this.getName() + " is Walking");
    }
}
