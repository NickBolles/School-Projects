package exam1;

import java.util.Scanner;
import java.util.StringTokenizer;

public class DataCheck {

	public static void main(String[] args) {
		DataCheck DC = new DataCheck();
		DC.getInput();
		
		
		
	}
	public void getInput(){
		boolean quit = false;
		//make a while loop to allow for testing multiple strings
		while(!quit){
			//Let the user know what to enter
			System.out.println("Please enter a student name and GPA in the form: ");
			System.out.println("<Capatilzed first Name><At least one space><Capatilized Last Name><comma><number><dot><two Numbers>");
			System.out.println("Example: \"Greg Johnson,3.14\"");
			//create the scanner
			Scanner scanner=new Scanner(System.in);
			//get the next line the user enters
			String line =scanner.nextLine();
			//if the string is not equal to "q" or "Q" then continue, otherwise quit
			if (!line.equals("q") && !line.equals("Q")){
				//check the string
				checkString(line);
			}else{
				quit = true;
			}
		}
	}
	public void checkString(String line){
		//tokenize the string
		StringTokenizer tokens = new StringTokenizer(line, ",");
		//get the name string
		String name = tokens.nextToken();
		//get the gpa string
		String gpa = tokens.nextToken();
		
		//if checkName returns true print out a friendly message informing the user whether it was successfull or not
		if (checkName(name)){
			System.out.print("Correct Format of name!! =)  ");
		}else{
			System.out.print("Incorrect Format of name!! =(  ");
		}
		//if checkGPA returns true print out a friendly message informing the user whether it was successfull or not
		if (checkGPA(gpa)){
			System.out.print("Correct Format of gpa!! =)  ");
		}else{
			System.out.print("Incorrect Format of gpa!! =(   ");
		}
		System.out.println("");
		System.out.println("==========================================");
	}
	public boolean checkName(String line){
		//create the regex for the name
		String RegEx = "([A-Z]{1})([a-z]*)([ ]+)([A-Z]{1})([a-z]*)";
		//check the line to see if it is valid
		if (line.matches(RegEx)){
			return true;
		}else{
			return false;
		}
		
	}
	public boolean checkGPA(String line){
		//create the regex for the gpa
		String RegEx = "([0-4]{1})([.]{1})([0-9]{2})";
		//check the line to see if it is valid
		if (line.matches(RegEx)){
			return true;
		}else{
			return false;
		}
	}
}
