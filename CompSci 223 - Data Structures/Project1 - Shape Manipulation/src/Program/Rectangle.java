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
public class Rectangle extends Shape implements Polygon{
    Point upperLeft, lowerRight;
    
    Rectangle(Point upperLeft, Point lowerRight){
        //Make sure That upperLeft is above and to the left of lowerRight and vice versa
        if (upperLeft.getY() > lowerRight.getY() && upperLeft.getX() < lowerRight.getX()){
            this.upperLeft = upperLeft;
            this.lowerRight = lowerRight;
        }else{
            throw new InvalidArgumentException();
        }
    }

    public Point getUpperLeft() {
//        return this.upperLeft;
        return new Point(this.upperLeft.getX(), this.upperLeft.getY());
    }
    public void setUpperLeft(Point upperLeft) {
        if (upperLeft.getX() < this.lowerRight.getX() && upperLeft.getY() > this.lowerRight.getY()){
            this.upperLeft = upperLeft;
        }else{
            throw new InvalidArgumentException();
        }        
    }
    
   
    public Point getLowerRight() {
        return new Point(this.lowerRight.getX(), this.lowerRight.getY());
    }

    public void setLowerRight(Point lowerRight) {
        if (lowerRight.getX() > this.upperLeft.getX() && lowerRight.getY() < this.upperLeft.getY()){
            this.lowerRight = lowerRight;
        }else{
            throw new InvalidArgumentException();
        }
    }   
    
    
    
    public Point getUpperRight() {        
        return new Point(this.lowerRight.getX(), this.upperLeft.getY());
    }
    
    public void setUpperRight(Point upperRight){
        if (upperRight.getX() > this.upperLeft.getX() && upperRight.getY() > this.lowerRight.getY()){
            this.lowerRight.setX(upperRight.getX());        
            this.upperLeft.setY(upperRight.getY());
        }else{
            throw new InvalidArgumentException();
        }
    }
    
    public Point getLowerLeft() {        
        return new Point(this.upperLeft.getX(), this.lowerRight.getY());
    }
    
    public void setLowerLeft(Point lowerLeft){
        if (lowerLeft.getX() < this.lowerRight.getX() && lowerLeft.getY() < this.upperLeft.getY()){
            this.lowerRight.setY(lowerLeft.getY());        
            this.upperLeft.setX(lowerLeft.getX());
        }else{
            throw new InvalidArgumentException();
        }
        
    }
    
    public double getRight(){
        return this.lowerRight.getX();
    }
    public double getBottom(){
        return this.lowerRight.getY();
    }
    public double getTop(){
        return this.upperLeft.getY();
    }
    public double getLeft(){
        return this.upperLeft.getX();
    }
    
    @Override
    public double area(){
        double h = Point.distance(this.getUpperLeft(), this.getUpperRight());
        double w = Point.distance(this.getUpperLeft(), this.getLowerLeft());
        return (h*w);
    }
    
    @Override
    public double perimeter(){
        double h = Point.distance(this.getUpperLeft(), this.getUpperRight());
        double w = Point.distance(this.getUpperLeft(), this.getLowerLeft());
        return ((h*2)+(w*2));
    }
    
    @Override
    public boolean hit(Point p){
        if (p.getX() >= this.getLeft() && p.getX() <= this.getRight()){
            if (p.getY() >= this.getBottom() && p.getY() <= this.getTop()){
                return true;
            }
        }
        return false;
    }
    
    public String toString(){
        return "R["+this.upperLeft.toString()+", "+this.lowerRight.toString()+"]";
    }

    @Override
    public int numOfSides() {
        return 4;
    }
    
}
