/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program;

import java.lang.UnsupportedOperationException;
/**
 *
 * @author Nicholas
 */
public abstract class Shape {
    Shape(){
    }
    public double area(){
        throw new UnsupportedOperationException();
    }
    public double perimeter(){
        throw new UnsupportedOperationException();
    }
    public boolean hit(Point p){
        throw new UnsupportedOperationException();
    }
}
