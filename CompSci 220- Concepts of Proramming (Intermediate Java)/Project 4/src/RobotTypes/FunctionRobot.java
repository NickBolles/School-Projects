package RobotTypes;
import main.Robot;
public class FunctionRobot extends Robot{
	public FunctionRobot(int num){
		super();
		setDescription("Functional Robot " + num);
		setShortDescription("Func " + num);
	}
	public void move(){
		int position = getPosition();
		if (position == 1){
			setPosition(position+4);
		}else{			
			setPosition(position+(int)Math.ceil(2*log(position-1,2)));
		}
	}
	static double log(int x, int base)
	{
	    return (Math.log(x) / Math.log(base));
	}
}