package lab2;

import java.util.Scanner;

public class Sale {
	int sales[][] = new int[5][6];
	String names[] = new String [5];
	
	public static void main(String[] args){
		Sale sale = new Sale();
		//initialize the array to 0 so that we can increment it in the getData() method
		for (int i =0; i<sale.sales.length;i++){
			for (int k =0; k<sale.sales[i].length-1;k++){
				sale.sales[i][k] = 0;
			}
		}
		
		//For better User experiance get the name of the employees
		for (int i =0; i<sale.names.length;i++){
			System.out.print("Enter Sales Person " + (i+1) + "'s name:  ");
			Scanner scanner=new Scanner(System.in);
			sale.names[i] = scanner.nextLine();
		}
		//get the data from the user
		sale.getData();
		sale.displaySale();
	}
	
	public void getData(){
		//initialize the variables needed for this method
		boolean enter = true;
		int day = 0;
		//Explain where the program is at for the user
		System.out.println("Enter Data");
		System.out.println("===============================");
		while(enter){
			for (int i =0; i<sales.length;i++){
				for (int k =0; k<sales[i].length;k++){
					System.out.print("Please Enter The value for " + names[i] + " product " + (k+1) + ":  ");
					sales[i][k] += promptForInt();
				}
			}
			boolean valid = false;
			do{
				System.out.print("Would you like to enter another day? y/n   ");
			
				Scanner scanner=new Scanner(System.in);
				switch(scanner.next()){
					case "y":
					case "Y":
						enter = true;
						valid = true;
						day++;
						break;
					case "n":
					case "N":
						enter = false;
						valid = true;
						break;
					default:
						System.out.println("ERROR: unrecognized input, try again...");
						valid = false;
						break;
				}
			}while (!valid);		
		}//end of enter data loop
		System.out.println("Thats all the data we need! Calculating now...");
	}
	public void displaySale(){
		for (int i =0; i<sales.length;i++){
			int total = 0;
			System.out.println(names[i] + "'s Sales stats");
			System.out.println("===========================");
			for (int k =0; k<sales[i].length;k++){
				System.out.println("Product " + (k+1) + ": " + sales[i][k]);
				total += sales[i][k];
			}
			System.out.println("Total Sales: " + total);
			System.out.println(" ");
		}
	}
	public int promptForInt(){
        boolean inputValid=false;
        int num=0;
        do{ //Loop to make sure input is valid
            Scanner numScanner=new Scanner(System.in);
            try{                   
                num = numScanner.nextInt();
                inputValid = true;
            //catch the error if the input isn't an Int
            }catch(java.util.InputMismatchException e){
            	//provide an error message to the user and DONT 
            	//modify inputValid so that they have a chance to start again
                System.out.println("Please Enter Only Numbers");
            }
        }while (inputValid==false); //End Valid Input do-while Loop
        return num;
    }
}
