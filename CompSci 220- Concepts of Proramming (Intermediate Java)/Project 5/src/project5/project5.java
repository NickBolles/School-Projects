package project5;

public class project5 {
	public static boolean debug = true;
	public static String moviePath   = "C:\\cs220\\Movie.txt";
	public static String userPath    = "C:\\cs220\\User.txt";
	public static String ratingsPath = "C:\\cs220\\Ratings.txt";
	public static void main(String[] args) {
		System.out.println("Warming up...");
		System.out.println(" ");
		System.out.println(" ");
		//Create DB Connection and connect
		DatabaseManager dbManager = new DatabaseManager(debug);
		System.out.println("DB Setup completed");
		System.out.println(" ");
		//Load the userData
		System.out.println("Loading Data...");
		DataLoader dataLoader = new DataLoader(debug, dbManager);
		dataLoader.loadAndSave(moviePath,userPath,ratingsPath);
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Finished Initializing!");
		
		SearchFrame frameObj = new SearchFrame(dbManager);
		frameObj.setVisible(true);
	}

}
