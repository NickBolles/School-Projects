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
public class Square extends Rectangle implements Polygon{
    
    Square(Point upperLeft, Point lowerRight){        
        super(upperLeft,lowerRight);
        //Make sure delta y and delta x is equal to verify that it is a square
        if ((upperLeft.getY()-lowerRight.getY())==(upperLeft.getX()- lowerRight.getX())){
            throw new InvalidArgumentException();
        }        
    }

    @Override
    public int numOfSides() {
        return 4;
    }
    
    @Override
    public void setLowerLeft(Point lowerLeft){
        if ((this.upperLeft.getY()-lowerLeft.getY())==(this.lowerRight.getX()- lowerLeft.getX())){
            this.lowerRight.setY(lowerLeft.getY());        
            this.upperLeft.setX(lowerLeft.getX());
        }else{
            throw new InvalidArgumentException();
        }  
    }
    @Override
    public void setUpperLeft(Point upperLeft) {
        if ((upperLeft.getY()-this.lowerRight.getY())==(upperLeft.getX()- this.lowerRight.getX())){
            this.upperLeft = upperLeft;
        }else{
            throw new InvalidArgumentException();
        }        
    }
    @Override
    public void setLowerRight(Point lowerRight) {
        if ((this.upperLeft.getY()-lowerRight.getY())==(this.upperLeft.getX()- lowerRight.getX())){
            this.lowerRight = lowerRight;
        }else{
            throw new InvalidArgumentException();
        }
    }
    @Override
    public void setUpperRight(Point upperRight){
        if ((upperRight.getY()-this.lowerRight.getY())==(upperRight.getX() -this.lowerRight.getX())){
            this.lowerRight.setX(upperRight.getX());        
            this.upperLeft.setY(upperRight.getY());
        }else{
            throw new InvalidArgumentException();
        }
    }
    
}
