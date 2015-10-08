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
public class Circle extends Shape{
    private Point center;
    private double radius;
    Circle(Point center, double radius){
        this.center = center;
        this.radius = radius;
    }
    public Point getCenter() {
        return this.center;
    }
    public void setCenter(Point center){
        this.center = center;
    }
    public double getRadius() {
        return this.radius;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }        
    @Override
    public double area(){
        return Math.pow(Math.PI * this.radius,2);
    }
    @Override
    public double perimeter(){
        return 2 * Math.PI * this.radius;
    }
    @Override
    public boolean hit(Point p){
        if (Point.distance(p, this.center)< this.radius){
            return true;
        }
        return false;
    }
    public String toString(){
        return "c["+this.center.toString()+", "+this.radius+"]";
    }
    
}
