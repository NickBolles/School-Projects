package main;

import java.util.Scanner;

public class project4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean quit = false;
		utils utils = new utils();
		Race race = new Race();
		while (!quit){
			Scanner scanner = new Scanner(System.in);
			System.out.println("Please Enter the number of Random Robots to race");
			int randNum = utils.promptForInt();
			System.out.println("Please Enter the number of Function Robots to race");
			int functNum = utils.promptForInt();
			System.out.println("Please Enter the number of Unpredictable Robots to race");
			int unprNum = utils.promptForInt();
			race.startRace(randNum,functNum,unprNum);
			while(race.getStatus()){
				
			}		
			System.out.println("Would you like to do another race?");
			quit = utils.promptForBool();
		}
		
	}
	
}
