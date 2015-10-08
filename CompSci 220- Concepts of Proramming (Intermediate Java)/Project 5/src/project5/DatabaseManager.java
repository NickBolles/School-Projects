package project5;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DatabaseManager {
	private boolean debug = false;
	
	private static final String dbPrefix ="cs220-2147_";
	// Please enter your own netid here, all lowercase
	private String netId ="bollesnj07"; 
	private String pass = "nb2909";
	static final String hostName="clawhammer.uww.edu";
	private String databaseURL="jdbc:mysql://"+hostName+
	"/"+dbPrefix+netId;
	private Connection connection;
	
	//define the constructor
	public DatabaseManager(boolean Debug){
		if (Debug){
			debug = true;
			//if debugging use the much faster local SQL server for ease of development
			//databaseURL = "jdbc:mysql://localhost/"+dbPrefix+netId;
		}else{
			//Allow for changing the database information. Put this in a !Debug to not be annoying for now
			JTextField inputLine = new JTextField();
			
			final JDialog dialog = new JDialog();
			//ask for the netId
			netId = (String) JOptionPane.showInputDialog(null,"Please Enter Your NetId","Database Setup",(Integer) 0,null,null,netId);
			//Ask for the password, if different then the default
			String newPass = (String) JOptionPane.showInputDialog(null,"Please Enter Your Password, or leave blank to keep the default password","Database Setup",(Integer) 0,null,null,"");
			if (!newPass.equals("")){
				pass = newPass;
			}
			databaseURL="jdbc:mysql://"+hostName+"/"+dbPrefix+netId;
			databaseURL = (String) JOptionPane.showInputDialog(null,"Please Enter the DatabaseURL","Database Setup",(Integer) 0,null,null,databaseURL);
			
		}
			
		
		try {
			//Get the connection and print out results if debugging
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(databaseURL, netId, pass);
			if (debug){
				System.out.println("Successfully connected to database:");
				System.out.println("           " + databaseURL);
				System.out.println("As user:   " + netId);
			}
			//if an SQLException hasn't occurred create the tables
			createTables();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean createTables(){
		try{
			if(debug){
				System.out.println("Creating Tables...Please Wait...");
			}
			//Create the MOVIE Table
			Statement statement = connection.createStatement();
			
			String sql;
			//Create The Tables, preferebly we would define the schema  and this would preferably this would be done in JSON, 
			//or in another class, for time sake It will be done here as just SQL Strings
			
			
			
			// CREATE THE MOVIE TABLEW
			sql = "CREATE TABLE IF NOT EXISTS MOVIES "+ "(MovieId CHAR(6) , "+ " Title CHAR(100), " +
			" Genre CHAR(50), MovieYear INTEGER, MPAARating CHAR(20), Directors CHAR(50), Company CHAR(50), PRIMARY KEY (MovieId))";
			
			if (debug){
				System.out.println("	" +sql);
			}
			statement.executeUpdate(sql);
			
			
			// CREATE THE USER TABLE
			sql = "CREATE TABLE IF NOT EXISTS USERS "+ "(UserId CHAR(8) , "+ " Password CHAR(10), " +
					" Age INTEGER, Gender CHAR(1), PRIMARY KEY (UserId))";
			if (debug){
				System.out.println("	" +sql);
			}
			statement.executeUpdate(sql);
			
			// CREATE THE USER TABLE
			sql = "CREATE TABLE IF NOT EXISTS RATINGS "+ "(UserId CHAR(8) , "+ " MovieId CHAR(10), Rating INTEGER, PRIMARY KEY (UserId, MovieId))";
			if (debug){
				System.out.println("	" +sql);
			}
			statement.executeUpdate(sql);
			
			if(debug){
				System.out.println("Tables Created!");
			}
			return true;
		}catch (SQLException e) {
			if (debug){
				System.out.println("ERROR IN CREATE TABLES()");	
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public void saveUsers(ArrayList<User> users){
		for (int i = 0; i< users.size(); i++){
			saveUser(users.get(i));			
		}
	}
	public void saveUser(User user){
		Statement statement;
		try {
			statement = connection.createStatement();
			
			String sql = "INSERT IGNORE INTO USERS(UserId,Password,Age,Gender)"+" VALUES ('" + user.getId() + "','" + user.getPassword() + "','" + user.getAge() + "','" + user.getGender() + "');";
			if (debug){
				//System.out.println("	" +"Saving User " + user.getId() + " pass=" + user.getPassword() + " age=" + user.getAge() + " gender=" + user.getGender());
				System.out.println("	" +sql);
			}
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void saveMovies(ArrayList<Movie> movies){
		for (int i = 0; i< movies.size(); i++){
			saveMovie(movies.get(i));			
		}
	}
	public void saveMovie(Movie movie){
		Statement statement;
		try {
			statement = connection.createStatement();
			
			String sql = "INSERT IGNORE INTO MOVIES(MovieId,Title,Genre,MovieYear,MPAARating,Directors,Company)"+" VALUES ('" + movie.getId() + "','" + movie.getTitle() + "','" + movie.getGenre() + "','" + movie.getYear() + "','"+movie.getMpaa()+"','"+movie.getDirectors()+"','"+movie.getCompany()+"');";
			if (debug){
				//System.out.println("	" +"Saving Movie " + movie.getId() + " title=" + movie.getTitle() + " genre=" + movie.getGenre() + " year=" + movie.getYear() + " mpaa="+movie.getMpaa()+" directors="+movie.getDirectors()+" company="+movie.getCompany());
				System.out.println("	" +sql);
			}
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void saveRatings(ArrayList<Rating> ratings){
		for (int i = 0; i< ratings.size(); i++){
			saveRating(ratings.get(i));			
		}
	}
	public void saveRating(Rating rating){
		Statement statement;
		try {
			statement = connection.createStatement();
			
			String sql = "INSERT IGNORE INTO RATINGS(UserId,MovieId,Rating)"+" VALUES ('" + rating.getUserId() + "','" + rating.getMovieId() + "','" + rating.getRating() + "');";
			if (debug){
				//System.out.println("	" +"Saving Rating userId=" + rating.getUserId() + " movieId=" + rating.getMovieId() + " rating=" + rating.getRating());
				System.out.println("	" +sql);
			}
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public ResultSet search(int type,String searchString){
		try {
			Statement statement = connection.createStatement();
			String sql;
			if (type == 0){
				sql="SELECT Title,MovieYear,Genre,Directors,MPAARating " + 
						"FROM MOVIES WHERE MOVIES.Title = '" + searchString + "';";
				
			}else{
				sql="SELECT Title,MovieYear,Genre,Directors,MPAARating,RATINGS.Rating FROM MOVIES INNER JOIN RATINGS ON "+
						" MOVIES.MovieId = RATINGS.MovieId "+
						" WHERE RATINGS.Rating > " + searchString;
			}
			
			if (debug){
				//System.out.println("	" +"Saving Rating userId=" + rating.getUserId() + " movieId=" + rating.getMovieId() + " rating=" + rating.getRating());
				System.out.println("	" +sql);
			}
			ResultSet result = statement.executeQuery(sql);
			System.out.println("Done Executing Query");
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return null;
	}
}
