import javax.swing.JOptionPane;


public class Final {
	public static boolean debug = true;
	public static void main(String[] args) {
		String fileUrl = (String) JOptionPane.showInputDialog(null,
				"Please Enter the Email.text File location","Spam Bot 3000",
				(Integer) 0,null,null,
				"C:\\Users\\Nicholas\\SkyDrive (2).old\\School\\Sophomore-UWW\\CompSci 220\\Final\\src\\email.txt");
		SpamDetector SD = new SpamDetector(debug);
		int count = SD.checkEmail(fileUrl);
		if (count != -1){
			String message = count/2 + " Spam Keywords found in your email! (Count = " + count + ")";
			int type = 2; //ERROR_MESSAGE
			if (count ==0){
				message = "Your Email is clean! Yay!!";
				//no type included will 
				type = 1;//INFORMATION_MESSAGE
			}
			JOptionPane.showMessageDialog(null, message, "Spam Bot 3000 Report",
				    type);
			System.out.println("Count for email is " + count);
		}
		else{
			//exit with a non-zero exit code to indicate an error occurred
			System.exit(1);
		}
	}

}
