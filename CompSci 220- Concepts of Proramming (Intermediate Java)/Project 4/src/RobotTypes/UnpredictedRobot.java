package RobotTypes;
import main.Robot;
public class UnpredictedRobot extends Robot{
	public UnpredictedRobot(int num){
		super();
		setDescription("Unpredicted Robot " + num);
		setShortDescription("Unpr " + num);
		
		//TODO: REMOVE DEBUG CODE
		//setPosition(90);
	}
	public void move(){
		int position = getPosition();
		int rand =  (int) Math.round(Math.random()*100);
		int numToMove = 0;
		//System.out.println("unpredicted" + rand);
		//30% of the time move 3 squares
		if (rand<30){
			numToMove = 3;
		}
		//20% of the time move backward 2 squares
		else if (rand<50){
			numToMove = -2;
		}
		//25% of the time move 6 squares
		else if (rand<75){
			numToMove = 6;
		}
		//15% of the time move backward 5 squares
		else if (rand<90){
			numToMove = -5;
		}
		//The rest of the time, 10%,
		//don't move, so don't modify numToMove
		setPosition(position+numToMove);
		
	}
}
