

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DataLoader {
	
	private boolean debug = false;
	//The DataLoader Constructor
	public DataLoader(boolean Debug){
		
	}
	
	public ArrayList<String> loadFileAsArrayList(String fileURL){
		System.out.println("	Loading File... Please Wait...");
		ArrayList<String> lines = new ArrayList<String>(0);
		
		boolean hasMore = true;
		FileReader fileReader;
		try {
			fileReader = new FileReader(fileURL);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while(hasMore){
					String line = "";
					line = bufferedReader.readLine();
					if (line != null){
						lines.add(line);
					}else{
						hasMore = false;
					}
			}
			
			System.out.println("	Done Loading Users");
			return lines;
		} catch (FileNotFoundException e) {
			System.out.println("ERROR!: Cannot Find file: " + fileURL);
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR! Cannot find the specified file!  \r\n" + fileURL, "Spam Bot 3000 File Not Found!",
				    2);
		} catch (IOException e) {
			System.out.println("ERROR!: Cannot Parse File: " + fileURL);
			JOptionPane.showMessageDialog(null, "ERROR! Cannot read the specified file!  \r\n" + fileURL, "Spam Bot 3000 File Cannot Be Read!",
				    2);
			e.printStackTrace();
		}
		return lines;
			
	}
	
	 
}
