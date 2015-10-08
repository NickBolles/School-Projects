package Project1;

import java.util.Arrays;
import java.util.Comparator;

public class Project1 {
	String line = "";
	int n = 0;
	String data[][] = new String[19][7];
	String max[][];
	String sorted[][];
	
	public static void main(String[] args) {
		Project1 proj = new Project1();
		proj.loadData();
		proj.sort();
		proj.printResults();
		}
	
	public void loadData(){
		
		FileInput fileIn = new FileInput();

		fileIn.openFile("fsdata.txt");
		line = fileIn.readLine();
		while (line != null) {			
			
			String sName = line.substring(0, 17);
			String sClub = line.substring(17, 33);
			String spPts = line.substring(33,38);
			String lpPts = line.substring(38,44);
			String pcPts = line.substring(44,49);
			String eePts = line.substring(49);
			Double dbltPts = Double.parseDouble(spPts) + Double.parseDouble(lpPts);
			String tPts = dbltPts.toString();
			data[n][0] = sName;
			data[n][1] = sClub;
			data[n][2] = spPts;
			data[n][3] = lpPts;
			data[n][4] = pcPts;
			data[n][5] = eePts;
			data[n][6] = tPts;
			//Double.parseDouble(
					
			//System.out.println("Name: " + sName + " Club: " + sClub + " Short Pts: " + spPts + " lpPts: " + lpPts + " prog comp: " + pcPts + " Exec Ele: " + eePts);
			line = fileIn.readLine();
			n++;
		}
	}
	public void sort(){
		

		/*
		 * 0: Short Program Max Scorer
		 * 1: Long Program Max Scorer
		 * 2: Total Points Max Scorer
		 * 3: Artistic Points Max Scorer
		 * 4: Athletic Points Max Scorer
		 */
		//This max array will hold all information about the skater though
		max = new String[5][data[0].length];
		
		
		//initially the first entry will be the max, 
		//any hereafter that are higher will replace it 
		for (int i=0;i<max.length;i++){
			max[i] = data[0];
		}
		
		
		sorted = new String[data.length][data[0].length];
		sorted[0] = data[0];
		
		//iterate through all of the skaters
		for (int i=1;i<data.length;i++){
			String curSkater[] = data[i];
			double curSkaterScores[] = parseDouble(curSkater);
			
			for (int k=0;k<sorted.length;k++){
				//System.out.println("sorted " + k + " 6  is: " + sorted[k][6]);
				//System.out.println("CurSkater is " + curSkater[0] + "Points is " + curSkaterScores[4]);
				if (sorted[k][6] != null){
					if (Double.parseDouble(sorted[k][6]) < curSkaterScores[4]){
						String temp[] = curSkater;
						for(int l = k; l<(sorted.length-k);l++){
							String temp1[] = sorted[l];
							sorted[l] = temp;
							temp = temp1;
						}
						k=sorted.length;
					}	
				}else{
					sorted[k] = curSkater;
					k=sorted.length;
				}
				
			}
			
			
			//create a double array of the max's
			//do this here because it could change with each skater
			double dblMaxArray[][] = new double[max.length][5];
			for (int k=0;k<max.length;k++){
				dblMaxArray[k] = parseDouble(max[k]);
			}
			
			
			//this for loop will loop through the different types of points
			//and compare them to the stored dblMaxArray points
			for (int k=0;k<curSkaterScores.length;k++){
				//compare the currentSkater points with the max array points
				if (curSkaterScores[k] > dblMaxArray[k][k]){
					//Add the skater into the max array at the current spot
					max[k] = curSkater;
				}
			}
		}
		
	}
	public void printResults(){
		//Print the header
		System.out.println("Rank     Skater Name         Skater Club        Short Program Points     Long Program Points    Total Points");
		System.out.println("------------------------------------------------------------------------------------------------------------");
		//print out each skater, from the sorted array
		for (int i=0;i<sorted.length;i++){
			System.out.printf(" %2d     %s    %s          %.2f                  %.2f              %.2f\n", i, sorted[i][0], sorted[i][1], Double.parseDouble(sorted[i][2]),Double.parseDouble(sorted[i][6]),Double.parseDouble(sorted[i][6]));
		}
		//cache the winners of each section from the max array
		String spWinner[] = max[0];
		String lpWinner[] = max[1];
		String pcWinner[] = max[2];
		String eeWinner[] = max[3];
		String tpWinner[] = max[4];
		//Print out the winner of each program
		System.out.println("");
		System.out.println("");
		System.out.println("Short Program Winner is  " + spWinner[0].replaceAll("\\s\\s\\s\\s", "") + " with a score of " + spWinner[2]);
		System.out.println("Long Program Winner is   " + lpWinner[0].replaceAll("\\s\\s\\s\\s", "") + " with a score of " + lpWinner[3]);
		System.out.println("Total Points Winner is   " + tpWinner[0].replaceAll("\\s\\s\\s\\s", "") + " with a score of " + tpWinner[6]);
		System.out.println("Artistic Winner is       " + pcWinner[0].replaceAll("\\s\\s\\s\\s", "") + " with a score of " + pcWinner[4]);
		System.out.println("Athleticism Winner is    " + eeWinner[0].replaceAll("\\s\\s\\s\\s", "") + " with a score of " + spWinner[5]);
		System.out.println("");
		System.out.println("");		
		System.out.println("The 2014 US Olympic Men Skating team is: " + sorted[0][0] + " and " + sorted[1][0]);
	
	}
	public double[] parseDouble(String skater[]){
		////////////////////////////////////////////////
		//			    NOTE FOR GRADING              //
		//   This can easily be done in a for loop,   //
		//   but it is easier to visualize this way   //
		//   and be able to tell what index is what   //
		////////////////////////////////////////////////
		
		double result[] = new double[5];
		//set the shortProgram Points
		result[0] = Double.parseDouble(skater[2]);
		//set the longProgram Points
		result[1] = Double.parseDouble(skater[3]);
		//set the program points
		result[2] = Double.parseDouble(skater[4]);
		//set the element points
		result[3] = Double.parseDouble(skater[5]);
		//set the total points
		result[4] = Double.parseDouble(skater[6]);
		//return the results
		return result;
	}
}//End of main

