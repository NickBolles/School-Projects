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
public class Point {
    private double x, y;
    Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public String toString(){
        return "("+this.x+","+this.y+")";
    }
    public static double distance(Point p1, Point p2){
        return Math.sqrt((Math.pow(p2.getX()-p1.getX(),2) + Math.pow(p2.getY()-p1.getY(),2)));
    }
}
