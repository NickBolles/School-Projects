package

lab4;

import java.util.Scanner;

public

class lab4 {

	/**
	
	* @param args
	
	*/
	
	public static void main(String[] args) {
		System.out.println("Please Enter your phone number in a valid format ");
		Scanner scanner = new Scanner(System.in);
		String string1 = scanner.nextLine();
		//check to see if the string matches the Reg Exp
		boolean matches = string1.matches("^[1-9]{1}[0-9]{2}\\s+[0-9]{3}\\s+[0-9]{4}");
		//Inform the user whether what they entered matches the reg expression
		if (matches){
			System.out.println("Your Phone number is in a valid format!");
		}
		else{
			System.out.println("Your Phone number is in an invalid format!");
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("Please Enter a String");
		String string2 = scanner.nextLine();
		//split the string by the space charector
		String tokens[] = string2.split(" ");
		
		//Store the length of the array for easy use
		int length = tokens.length-1;
		for (int i=0 ; i < length; i++){
			//Subtract the number of iterations from the length of the array
			System.out.println(tokens[length-i]);
		}
		
	}
}