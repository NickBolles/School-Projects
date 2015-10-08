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
public class Lizard extends Animal implements Crawls, Walks{
    Lizard(String name){
        super(name);
    }
    @Override
    public String speak() {
        return "hiss?";
    }
    public void crawl(){
        System.out.println(this.getName() + " is crawling");
    }

    @Override
    public void walk() {
        System.out.println(this.getName() + " is Walking");
    }
}
