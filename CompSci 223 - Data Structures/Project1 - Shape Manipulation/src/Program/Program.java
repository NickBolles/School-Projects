/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Program;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Nicholas
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Rectangle test = new Rectangle(new Point(0,10), new Point(10,0));
//        System.out.println(test.getUpperLeft().toString());
//        System.out.println(test.getLowerRight().toString());
//        test.getUpperLeft().setX(9);
//        test.getLowerRight().setY(9);
//        System.out.println(test.getUpperLeft().toString());
//        System.out.println(test.getLowerRight().toString());
        
        
        ArrayList<Shape> Shapes = new ArrayList<Shape>();
        //For Debugging and testing populate the shapes
        populateShapes(Shapes);
        
        boolean quit = false;
        while (!quit){
            System.out.println("=================================");
            System.out.println("What would you like to do?");
            System.out.println("1) Add A Shape");
            System.out.println("2) Update a Shape");
            System.out.println("3) Remove a Shape");
            System.out.println("4) List all Shapes");
            System.out.println("5) List all polygon Shapes");
            System.out.println("6) List all non-polygon Shapes");
            System.out.println("7) Display Total Perimeter of Shapes");
            System.out.println("8) Display Total Area of Shapes");
            System.out.println("9) Throw a Dart");            
            System.out.println("10) Exit");
            boolean valid = false;
            while(!valid){
                int input = Utils.promptForInt();
                switch(input){
                    case 1:
                        createNewShape(Shapes);
                        valid = true;
                        break;
                    case 2:
                        updateShape(Shapes);
                        valid = true;
                        break;
                    case 3:
                        removeShape(Shapes);
                        valid = true;
                        break;
                    case 4:
                        listShapes(Shapes,true,true);
                        valid = true;
                        break;
                    case 5:
                        listShapes(Shapes,true,false);
                        valid = true;
                        break;
                    case 6:
                        listShapes(Shapes,false,true);
                        valid = true;
                        break;
                    case 7:
                        totalPetimeter(Shapes);
                        valid = true;
                        break;
                    case 8:
                        totalArea(Shapes);
                        valid = true;
                        break;
                    case 9:
                        throwDart(Shapes);
                        valid = true;
                        break;
                    case 10:
                        quit = true;
//                        System.out.println("Would you like to Save the shapes?");
//                        if (Utils.promptForBoolean()){
//                            saveShapes(Shapes);
//                        }
                        System.out.println("Exiting Program...");
                        valid = true;
                        break;
                    default:
                        valid = false;
                        System.out.println("Error: Invalid Entry. Try Again");
                    break;

                }
            }
            
        }
        System.out.println("Made By Nick Bolles on 2/4/2015 for UW Whitewater Class CompSci 223 \"Data Structures\"");
        
        
    }
    public static void populateShapes(ArrayList<Shape> Shapes){
        Shapes.add(new Circle(new Point(10.0,10.0),6.3));
        Shapes.add(new Rectangle(new Point(5,15), new Point(15,10)));
        Shapes.add(new Square(new Point(0,5), new Point(5,0))); 
        Shapes.add(new Triangle(new Point(0,0), new Point(3,2),new Point(2,5)));
                        
    }
    public static void createNewShape(ArrayList<Shape> Shapes){
        System.out.println("What Type of Shape Would you Like to create?");
        System.out.println("0) Cancel");
        System.out.println("1) Circle");
        System.out.println("2) Rectangle");
        System.out.println("3) Square");        
        System.out.println("4) Triangle");
        boolean valid = false;
        while (!valid){
            int selection = Utils.promptForInt();
            switch(selection){
                case 0:
                    System.out.println("Canceling create new Shape...");
                    valid = true;
                    break;
                case 1:
                    System.out.println("Adding a Circle...");
                    System.out.println("Enter Center Point: x y");
                    double[] coords = Utils.promptForPoint(false);
                    System.out.println("Enter Radius: ");
                    double radius = Utils.promptForDouble();
                    Shapes.add(new Circle(new Point(coords[0],coords[1]), radius));
                    System.out.println("Circle Added!");
                    valid = true;
                    break;
                case 2:
                    System.out.println("Adding a Rectangle...");
                    System.out.println("Enter Upper Left Point: x y");
                    double[] upperLeft = Utils.promptForPoint(false);
                    System.out.println("Enter Lower Right Point: x y");
                    double[] lowerRight = Utils.promptForPoint(false);
                    try{                            
                        Shapes.add(new Rectangle(new Point(upperLeft[0],upperLeft[1]), new Point(lowerRight[0],lowerRight[1])));
                        System.out.println("Rectangle Added!");
                        valid = true;
                    }catch(InvalidArgumentException e){
                        System.out.println("Error: Invalid Arguments to create Rectangle: " + new Point(upperLeft[0],upperLeft[1]).toString() + "  " + new Point(lowerRight[0],lowerRight[1]).toString());
                    }

                    break;
                case 3:
                    System.out.println("Adding a Square...");
                    System.out.println("Enter Upper Left Point: x y");
                    double[] ul = Utils.promptForPoint(false);
                    System.out.println("Enter Lower Right Point: x y");
                    double[] lr = Utils.promptForPoint(false);
                    try{
                        Shapes.add(new Square(new Point(ul[0],ul[1]), new Point(lr[0],lr[1])));
                        System.out.println("Square Added!");
                        valid = true;
                    }catch(InvalidArgumentException e){
                        System.out.println("Error: Invalid Arguments to create Square: " + new Point(ul[0],ul[1]).toString() + "  " + new Point(lr[0],lr[1]).toString());
                    }
                        valid = true;
                    break;
                case 4:
                    System.out.println("Adding a Triangle...");
                    System.out.println("Enter Point 1: x y");
                    double[] p1 = Utils.promptForPoint(false);
                    System.out.println("Enter Point 2: x y");
                    double[] p2 = Utils.promptForPoint(false);                        
                    System.out.println("Enter Point 3: x y");
                    double[] p3 = Utils.promptForPoint(false);
                    Shapes.add(new Triangle(new Point(p1[0],p1[1]), new Point(p2[0],p2[1]),new Point(p3[0],p3[1])));
                    System.out.println("Triangle Added!");
                    valid = true;
                break;
                default:
                    valid = false;
                    System.out.println("Error: Invalid Entry. Try Again");

                    break;
            }
        }
    }
    public static void updateShape(ArrayList<Shape> Shapes){
        System.out.println("Select the Shape to Edit...");
        int count =listShapes(Shapes, true, true);
        while(true){
            int p = Utils.promptForInt();
            if (p < count){
                Shape s = Shapes.get(p);
                if (s instanceof Circle){
                    Circle c = (Circle) s;
                    System.out.println("Editing Circle " + c.toString() + "...");
                    System.out.println("Leave Blank or enter 0 for numeric values to leave the value as it was");
                        System.out.println("Enter Center Point: x y");
                        double[] center = Utils.promptForPoint(true);
                        System.out.println("Enter Radius: ");
                        double radius = Utils.promptForDouble();                        
                        if (center.length >0){
                            c.setCenter(new Point(center[0],center[1]));                       
                        }
                        if (radius != 0.0){
                            c.setRadius(radius);                       
                        }
                        System.out.println("Shape Updated to " + c.toString());
                }else if(s instanceof Rectangle ){
                    Rectangle r = (Rectangle) s;
                    System.out.println("Editing Rectangle " + r.toString() + "...");
                    System.out.println("Leave Blank or enter 0 for numeric values to leave the value as it was");
                        System.out.println("Enter Upper Left Point: x y");
                        double[] upperLeft = Utils.promptForPoint(true);
                        System.out.println("Enter Lower Right Point: x y");
                        double[] lowerRight = Utils.promptForPoint(true);
                        try{                            
                            if (upperLeft.length >0){
                                r.setUpperLeft(new Point(upperLeft[0],upperLeft[1]));                       
                            }
                            if (lowerRight.length >0){
                                r.setLowerRight(new Point(lowerRight[0],lowerRight[1]));                       
                            }
                            System.out.println("Shape Updated to " + r.toString());
                        }catch(InvalidArgumentException e){
                            System.out.println("Error: Invalid Arguments to create Rectangle: " + new Point(upperLeft[0],upperLeft[1]).toString() + "  " + new Point(lowerRight[0],lowerRight[1]).toString());
                        }
                }else if (s instanceof Square){
                    Square sq = (Square) s;
                    System.out.println("Editing Rectangle " + sq.toString() + "...");
                    System.out.println("Leave Blank or enter 0 for numeric values to leave the value as it was");
                        System.out.println("Enter Upper Left Point: x y");
                        double[] upperLeft = Utils.promptForPoint(true);
                        System.out.println("Enter Lower Right Point: x y");
                        double[] lowerRight = Utils.promptForPoint(true);
                        try{                            
                            if (upperLeft.length >0){
                                sq.setUpperLeft(new Point(upperLeft[0],upperLeft[1]));                       
                            }
                            if (lowerRight.length >0){
                                sq.setLowerRight(new Point(lowerRight[0],lowerRight[1]));                       
                            }
                            System.out.println("Shape Updated to " + sq.toString());
                        }catch(InvalidArgumentException e){
                            System.out.println("Error: Invalid Arguments to create Square: " + new Point(upperLeft[0],upperLeft[1]).toString() + "  " + new Point(lowerRight[0],lowerRight[1]).toString());
                        }
                }else if(s instanceof Triangle){
                    Triangle t = (Triangle) s;
                    System.out.println("Editing Triangle " + t.toString() + "...");
                    System.out.println("Leave Blank or enter 0 for numeric values to leave the value as it was");
                        System.out.println("Enter Point 1: x y");
                        double[] p1 = Utils.promptForPoint(true);
                        System.out.println("Enter Point 2: x y");
                        double[] p2 = Utils.promptForPoint(true);                        
                        System.out.println("Enter Point 3: x y");
                        double[] p3 = Utils.promptForPoint(true);
                        if (p1.length >0){
                            t.setP1(new Point(p1[0],p1[1]));                       
                        }
                        if (p2.length >0){
                            t.setP2(new Point(p2[0],p2[1]));                       
                        }
                        if (p3.length >0){
                            t.setP3(new Point(p3[0],p3[1]));                       
                        }
                        System.out.println("Shape Updated to " + t.toString());
                }
                break;
            }else{
                System.out.println("Error! Invalid Input. Please Enter A number Above, or 0 to cancel");
            }
        }
    }
    public static void removeShape(ArrayList<Shape> Shapes){
        System.out.println("Which Animal Would you like to delete?");
        int count = listShapes(Shapes, true, true);
        
        boolean v = false;
        while (!v){
            int p =Utils.promptForInt();
            if (p < count){
                v = true;
                Shapes.remove(p-1);
                System.out.println("Deleting animal " + p);
            }else{
                System.out.println("Error! Invalid Input. Please Enter A number Above, or 0 to cancel");
            }
        }

    }
    public static int listShapes(ArrayList<Shape> Shapes, boolean p, boolean np){
        Iterator i = Shapes.iterator();
        int count = 0;
        while(i.hasNext()){
            
            Shape s = (Shape) i.next();
            if (p && s instanceof Polygon){
                System.out.println(count + ") " + s.toString());
                count++;
            }
            if (np && !(s instanceof Polygon)){
                System.out.println(count + ") " + s.toString());
                count++;
            }
            
        }
        return count;
    }
    public static void totalPetimeter(ArrayList<Shape> Shapes){
        Iterator i = Shapes.iterator();
        double perimeter = 0.0;
        while(i.hasNext()){
            Shape s = (Shape) i.next();
            try{
                perimeter+=s.perimeter();
             }catch(UnsupportedOperationException e){
                //Just Skip this, it counts as 0
            }
        }
        System.out.println("Perimeter of all Shapes is " +perimeter);
    }
    public static void totalArea(ArrayList<Shape> Shapes){
        Iterator i = Shapes.iterator();
        double area = 0.0;
        while(i.hasNext()){
            Shape s = (Shape) i.next();
            try{
                area+=s.area();
            }catch(UnsupportedOperationException e){
                //Just Skip this, it counts as 0
            }
        }
        System.out.println("Area of all Shapes is " +area);
    }
    public static void throwDart(ArrayList<Shape> Shapes){
        System.out.println("Where would you like to throw the dart? x y");
        double[] p = Utils.promptForPoint(false);
        Point point = new Point(p[0], p[1]);
        int count =0;
        Iterator i = Shapes.iterator();
        while(i.hasNext()){
            Shape s = (Shape) i.next();
            try{
                if (s.hit(point)){
                    System.out.println(s.toString());
                    count++;
                };
            }catch(UnsupportedOperationException e){
                //Just Skip this, it counts as 0
            }
        }
        System.out.println("Your Dart hit " + count + " Shapes");
    }
    public static void saveShapes(ArrayList<Shape> Shapes){
        
    }
    public static void loadShapes(ArrayList<Shape> Shapes){
        
    }
   
    
}
