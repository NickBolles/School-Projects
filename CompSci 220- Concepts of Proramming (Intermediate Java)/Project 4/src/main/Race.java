package main;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import RobotTypes.FunctionRobot;
import RobotTypes.RandomRobot;
import RobotTypes.UnpredictedRobot;

public class Race {
	private int startPos = 1;
	private int finPos = 100;
	private int tickTime = 1000;//time in ms for the timer to tick
	private int tickCount = 0;
	private ArrayList<Robot> robots = new ArrayList<Robot>(0);
	private Timer timer = new Timer();
	private boolean running = false;
	int robotNum;
	public Race(){
		//create the robots and add them to the robots arrayList
	}
	public void startRace(int RRobotNum,int FRobotNum,int URobotNum){
		//add create the correct robots and add them to the robots list
		createAndAddRobots(RRobotNum,FRobotNum,URobotNum);
		
		System.out.print("Ready....");
		System.out.print("Set....");
		System.out.println("GO!");
		System.out.print("       ");
		for (int i = 0; i<robots.size();i++){
			System.out.print(((Robot) robots.get(i)).getShortDescription() + "  ");
		}
		System.out.println("");
		//create the timer, which will move robots each tick
		createTimer(tickTime);
		//set the running status to true
		setStatus(true);
	}
	private void stopRace() {
		// TODO Auto-generated method stub
		timer.cancel();
		setStatus(false);
	}
		
	private void createAndAddRobots(int RRobotNum,int FRobotNum,int URobotNum){
		//clear the array to make sure that we are starting from scratch
		robots.clear();
		//add the correct number of random robots
		for (int i = 0; i< RRobotNum;i++){
			int num = i;
			robots.add(new RandomRobot(num));
		}
		//add the correct number of function robots
		for (int i = 0; i< FRobotNum;i++){
			int num = i + RRobotNum;
			
			robots.add(new FunctionRobot(num));
		}
		//add the correct number of unpredicted robots
		for (int i = 0; i< URobotNum;i++){
			int num = i + RRobotNum + FRobotNum;
			robots.add(new UnpredictedRobot(num));
		}
		
	}
	
	private void createTimer(int tickTime2) {
		//reset the tickCount to 0
		tickCount = 0;
		//Create a timer that will wait the passed in time in ms and run moveRobots, and checkForWinner
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				moveRobots();
				checkForWinner();
				tickCount+=1;
			}
		}, tickTime2,tickTime2);
		
	}

	
	protected void moveRobots() {
		
		System.out.print("Turn " + tickCount + "   ");
		for (int i = 0; i<robots.size();i++){
			Robot robot = robots.get(i);
			int beforePos = robot.getPosition();
			robot.move();
			System.out.print(robot.getPosition() + "      ");
		}
		System.out.println("");
	}
	protected void checkForWinner() {
		ArrayList<Robot> winners = new ArrayList<Robot>(0);;
		for (int i = 0; i<robots.size();i++){
			Robot robot = robots.get(i);
			if (robot.getPosition()>finPos){
				winners.add(robot);
			}
		}
		if (winners.size()>0){
			String winString = "   Has Won the race!";
			if (winners.size()>1){
				System.out.println("We Have A Tie!");
				winString = "   Has tied with ";
			}
			for (int i = 0; i<winners.size();i++){
				System.out.print(((Robot)winners.get(i)).getDescription());
				if (i!=winners.size()-1){
					System.out.println(winString);
				}else if(winners.size() == 1){
					System.out.println(winString);
				}
				
			}
			stopRace();
			
		}
	}
	public boolean getStatus(){
		return running;
	}
	public void setStatus(boolean newStatus){
		running = newStatus;
	}
}
