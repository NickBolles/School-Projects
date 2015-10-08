package Project2;

import java.util.*;

import Project3.FileInput;

public class Project2 {
	String line = "";
	int n = 0;
	ArrayList country = new ArrayList(0);
	ArrayList skaterName = new ArrayList(0);
	ArrayList event = new ArrayList(0);
	ArrayList points = new ArrayList(0);
	ArrayList teamPoints = new ArrayList(0);

	String[][] countries = new String[0][0];
	ArrayList[] data = {
			country,
			skaterName,
			event,
			points,
			teamPoints
	};
	
	String[][] max;
	String sorted[][];
	
	public static void main(String[] args) {
		
		Project2 proj = new Project2();
		proj.loadData();
		proj.sort();
		proj.printResults();
	}
	
	public void loadData(){
		
		FileInput fileIn = new FileInput();

		fileIn.openFile("C:\\team.txt");
		line = fileIn.readLine();
		while (line != null) {		
			String sCountry = line.substring(0, 3);
			String sName = line.substring(3, 30);
			String event = line.substring(30,31);
			String points = line.substring(31,36);
			String tpoints = line.substring(36);
			data[0].add(sCountry);
			data[1].add(sName);
			data[2].add(event);
			data[3].add(points);
			data[4].add(tpoints);
			
			line = fileIn.readLine();
			n++;
		}
	}
	public void sort(){
		try{
			int array[][] = new int[3][3];
			int total = 0;
			for (int i = 0; i< array.length;i++)
				for (int j = 0; j< array.length;j++){
					array[i][j] = i*j;
					total += array[i][j];
				}
			total *= array[3][3];
			System.out.println(" Value =" + total);
			
		}catch(Exception e){
			System.err.println("Error in computing the total");
		}
		
		
		//allocate the size of the countries array to be the size of the data which will be too big, we will reallocate later
		countries = new String[data[0].size()][2];
		
		//iterate through all of the skaters
		for (int i=0;i<data[0].size()-1;i++){
			boolean foundCountry = false;
			String sCountry = (String) data[0].get(i);
			String sName = (String) data[1].get(i);
			String event = (String) data[2].get(i);
			String points = (String) data[3].get(i);
			String tPoints = (String) data[4].get(i);
			switch(event){
				case "1":
				case "2":
				case "3":
				case "4":
					for (int k=0;k<countries.length-1;k++){
						if (countries[k][0] == null){
							countries[k][1] = points;
							countries[k][0] = sCountry;
							break;
						}else{
							//if the current skater is in the current country then increment the country score
							if ( sCountry.compareTo(countries[k][0]) == 0 ){
								double countryScore = Double.parseDouble(countries[k][1]);
								countryScore += Double.parseDouble(points);
								countries[k][1] = Double.toString(countryScore);
								foundCountry =true;
								break;
							}
						}
						

						
					}
					break;
				default:break;
			}
			
		}
		//convert the countries array to be the size of the number of countries
		for (int k=0;k<countries.length-1;k++){
			if (countries[k][0] == null){
				String newItems[][] = new String[k][2];
				//copy the array to the new Item
				for (int i=0;i<k;i++){
					newItems[i] = countries[i];
				}
				//copy the array back
				countries = newItems;
				break;
			}
		}
		
		
		
		sorted = new String[countries.length][2];
		
		//iterate through all of the countries and sort them
		for (int i=0;i<countries.length;i++){
			String[] curCountry = countries[i];
			double curScore = Double.parseDouble(curCountry[1]);
			
			//Sort the rate
			for (int k=0;k<sorted.length;k++){
				if (sorted[k][1] != null){
					if (Double.parseDouble(sorted[k][1]) < curScore){
						String temp[] = curCountry;
						//insert something into the array
						for(int l = k; l<(sorted.length);l++){
							String temp1[] = sorted[l];
							sorted[l] = temp;
							temp = temp1;
						}
						sorted[sorted.length-1] = temp;
						k=sorted.length;
					}	
				}else{
					sorted[k] = curCountry;
					k=sorted.length;
				}
			}
		}
		
	}
	public void printResults(){
		//Print the header
		System.out.println("Country     Total Points");
		System.out.println("===========================");
		//print out each Country, from the sorted array
		for (int i=0;i<sorted.length;i++){
			System.out.printf("%d  %s      %.2f\n", i, sorted[i][0], Double.parseDouble(sorted[i][1]) );
		}

		System.out.println("");
		System.out.println("Qualifing Teams");
		System.out.println("================");		
		for (int k=0;k<sorted.length/2;k++){
			//print out the current country name

			System.out.println("");
			System.out.println(sorted[k][0]);
			System.out.println("---------------");
			//Iterate through all of the skaters to find the skaters that skate for the current country
			for (int i=0;i<data[0].size(); i++){
				//if this item in the data is the country we are looking for then print out the skater
				if (sorted[k][0].compareTo((String) data[0].get(i)) == 0){
					//print out the name of the skater
					System.out.println(data[1].get(i));
				}
			}
		}
	}
	
}//End of main

