/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program;

/**
 *
 * @author Nicholas
 */
public class Triangle extends Shape implements Polygon{
    private Point p1, p2, p3;

    Triangle(Point p1, Point p2, Point p3){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    
    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP3() {
        return p3;
    }

    public void setP3(Point p3) {
        this.p3 = p3;
    }
    
    @Override
    public double area(){
        /* 
            Credit for this formula goes to http://www.mathopenref.com/coordtrianglearea.html
        */
        return Math.abs(((p1.getX()*(p2.getY()-p3.getY()))+ (p2.getX()*(p3.getY()-p1.getY())) + (p3.getX()*(p1.getY()-p2.getY())))/2);
    }
    
    @Override
    public double perimeter(){
        return Point.distance(p1, p2) + Point.distance(p2, p3) + Point.distance(p1, p3);
    }
    
    
    /*
            I'm not 100% sure that this function works 100% of the time
    */
    @Override
    public boolean hit(Point p){
        
        return false;
    }
    
    public String toString(){
        return "T["+this.p1.toString()+", "+this.p2.toString()+", "+this.p3.toString() +"]";
    }

    @Override
    public int numOfSides() {
        return 3;
    }
    
}
