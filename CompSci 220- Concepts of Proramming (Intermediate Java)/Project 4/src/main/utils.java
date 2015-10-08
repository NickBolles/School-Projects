package main;

import java.util.Scanner;

public class utils {
	
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
	public boolean promptForBool(){
		boolean inputValid=false;
		boolean bool = false;
		do{ //Loop to make sure input is valid
			Scanner scanner=new Scanner(System.in);			
			switch(scanner.next()){
				case "y":
				case "Y":
					bool = true;
					inputValid = true;
					break;
				case "n":
				case "N":
					bool = false;
					inputValid = true;
					break;
				default:
					inputValid = false;
					//provide an error message to the user
	                System.out.println("Please Enter \"y\" or \"n\" ");
					break;
			}
		}while (inputValid = false);//End Valid Input do-while Loop
		return bool;
	}
}
