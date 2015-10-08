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
public class Snake extends Animal implements Crawls{
    Snake(String name){
        super(name);
    }
    @Override
    public String speak() {
        return "Hisssssssssssss";
    }
    public void crawl(){
        System.out.println(this.getName() + " is crawling");
    }
}
