package RobotTypes;

import main.Robot;

public class RandomRobot extends Robot{
	//define the constructor
	public RandomRobot(int num){
		super();
		setDescription("Random Robot " + num);
		setShortDescription("Rand " + num);
		
		//TODO: REMOVE DEBUG CODE
		//setPosition(90);
	}
	public void move(){
		int position = getPosition();
		int rand = (int) Math.round(Math.random()*100);
		int numToMove = 0;
		//System.out.println("random" + rand);
		//20% of the time move 1 square
		if (rand<20){
			numToMove = 1;
		}
		//30% of the time move 2 squares
		else if (rand<50){
			numToMove = 2;
		}
		//15% of the time move 6 squares
		else if (rand<65){
			numToMove = 6;
		}
		//The rest of the time, 35%,
		//don't move, so don't modify numToMove
		setPosition(position+numToMove);
	}
}
