package main;

public class Robot {
	private int position;
	private String name;
	private String description;
	private String shortDescription;
	//Define the constructor for Robots
	public Robot(){
		setPosition(1);
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public void setDescription(String newDescription){
		description = newDescription;
	}
	public String getShortDescription(){
		return shortDescription;
	}
	public void setShortDescription(String newDescription){
		shortDescription = newDescription;
	}
	public int getPosition(){
		return position;
	}
	public void setPosition(int newPos){
		position = newPos;
	}
	//This will be overridden by subclasses
	public void move() {
		
	}
}
