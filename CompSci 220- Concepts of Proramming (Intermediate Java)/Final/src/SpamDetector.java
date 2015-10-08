import java.util.*;
public class SpamDetector {
	private String[] keyWords = {"amazon", "official","bank","security", "urgent", "alert", "important", "information", "ebay", "password", "credit", "verify", "confirm", "account", "bill","immediately", "address", "telephone", "charity", "check", "secure", "personal", "confidential", "warning", "fraud", "citibank", "paypal"};
	private DataLoader DL;
	SpamDetector(boolean debug){
		 DL = new DataLoader(debug);
	};
	public int checkEmail(String fileURL){
		ArrayList<String> lines = DL.loadFileAsArrayList(fileURL);
		if (lines!=null){
			int count = 0;
			for (int i = 0; i<lines.size();i++){
				String line = (String) lines.get(i);
				String[] tokens = line.split(" ");
				for (int k = 0; k< tokens.length; k++){
					for (int j = 0; j<keyWords.length;j++){
						if (keyWords[j].equals(tokens[k])){
							System.out.println("KEYWORD FOUND! on line " + i + " word " + k);
							count+=2;
							//set j = to keywords.length to stop any more checks on this word
							j = keyWords.length;
							//set k = tokens.length to stop any more checks on this line
							k = tokens.length;
						}						
					}
				}
			}
			return count;
		}else{
			//there was probably an error reading the file, return -1;
			return -1;
		}
		
	}
}
