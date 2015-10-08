/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab10;

/**
 *
 * @author Nicholas
 */


class Animal{

 

  // name of animal e.g. cat  

  private String name;

  // sound makes by this animal e.g. meow */

  private String speech;

  Animal(String name, String speech){

    this.name = name;

    this.speech = speech;

  }

  void setName(String name){

    this.name = name;

  }

  void setSpeech(String speech){

    this.speech = speech;

  }

  String getName(){

    return name;

  }

  String getSpeech(){

    return speech;

  }

  void speak(){

    System.out.println("The " + name + " says \"" + speech +".\"");

  }

  

  void feed(String item){

    System.out.println("The " + name + " is munching on " + item + ".");

  }

}

