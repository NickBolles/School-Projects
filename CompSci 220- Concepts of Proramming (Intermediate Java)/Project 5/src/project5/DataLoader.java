package project5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class DataLoader {
	private ArrayList<User> users = new ArrayList<User>(0);
	private ArrayList<Movie> movies = new ArrayList<Movie>(0);
	private ArrayList<Rating> ratings = new ArrayList<Rating>(0);
	private DatabaseManager dbManager;
	private boolean debug = false;
	//The DataLoader Constructor
	public DataLoader(boolean Debug, DatabaseManager DBManager){
		if (Debug){
			debug = true;
		}
		if (DBManager == null){
			System.out.println("ERROR CREATING DATALOADER: DBManager is undefined!");
		}
		else{
			dbManager = DBManager;
		}
	}
	public void loadAndSave(String moviePath,String UserPath,String ratingPath){
		//First Load the Data
		loadData(moviePath,UserPath,ratingPath);
		//Now we need to save it
		System.out.println("Saving Users...Please Wait...");
		//Make sure that there is data, if there isn't, there is no reason to save it
		if (users!=null){
			dbManager.saveUsers(users);
		}
		System.out.println("Users Saved!");
		
		System.out.println("Saving Movies...Please Wait...");
		if (movies!=null){
			dbManager.saveMovies(movies);		
		}
		System.out.println("Movies Saved!");
		
		System.out.println("Saving Ratings...Please Wait...");
		if (ratings != null){
			dbManager.saveRatings(ratings);			
		}
		System.out.println("Ratings Saved!");
		
		
	}
	public void loadData(String moviePath,String userPath,String ratingPath){
		/*
		 * These three methods could be joined into one, 
		 * and switch between them with an argument, which would be much more efficient
		 */
		//load Users
		users =loadUsers(userPath);
		//Load Movies
		movies = loadMovies(moviePath);
		//Load Movies
		ratings = loadRatings(ratingPath);
		
		if (debug){
			System.out.println("Finished Loading Data");
		}
	}
	public ArrayList<User> getUsers(){
		return users;
	}
	public ArrayList<Movie> getMovies(){
		return movies;
	}
	public ArrayList<Rating> getRatings(){
		return ratings;
	}
	
	public ArrayList<User> loadUsers(String fileURL){
		System.out.println("	Loading Users... Please Wait...");
		ArrayList<User> users = new ArrayList<User>(0);
		
		boolean hasMore = true;
		FileReader fileReader;
		try {
			fileReader = new FileReader(fileURL);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while(hasMore){
					String line = "";
					line = bufferedReader.readLine();
					if (line != null){
						String[] segments = line.split(",");
						if (segments.length == 4){
							users.add(new User(segments[0],segments[1],segments[2], segments[3]));	
						}
					}else{
						hasMore = false;
					}
			}
			
			System.out.println("	Done Loading Users");
			return users;
		} catch (FileNotFoundException e) {
			System.out.println("ERROR!: Cannot Find file: " + fileURL);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR!: Cannot Parse File: " + fileURL);
			e.printStackTrace();
		}
		
		return null;		
	}
	public ArrayList<Movie> loadMovies(String fileURL){
		System.out.println("	Loading Movies... Please Wait...");
		ArrayList<Movie> MOVIES = new ArrayList<Movie>(0);
		
		boolean hasMore = true;
		FileReader fileReader;
		try {
			fileReader = new FileReader(fileURL);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while(hasMore){
					String line = "";
					line = bufferedReader.readLine();
					if (line != null){
						String[] segments = line.split(",");
						if (segments.length == 7){
							MOVIES.add(new Movie(segments[0],segments[1],segments[2], segments[3], segments[4], segments[5], segments[6]));	
						}
					}else{
						hasMore = false;
					}
			}
			System.out.println("	Done Loading Movies");
			return MOVIES;
		}  catch (FileNotFoundException e) {
			System.out.println("ERROR!: Cannot Find file: " + fileURL);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR!: Cannot Parse File: " + fileURL);
			e.printStackTrace();
		}
		return null;		
	}
	public ArrayList<Rating> loadRatings(String fileURL){
		System.out.println("	Loading Ratings... Please Wait...");
		ArrayList<Rating> ratings = new ArrayList<Rating>(0);
		
		boolean hasMore = true;
		FileReader fileReader;
		try {
			fileReader = new FileReader(fileURL);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while(hasMore){
					String line = "";
					line = bufferedReader.readLine();
					if (line != null){
						String[] segments = line.split(",");
						if (segments.length == 3){
							ratings.add(new Rating(segments[0],segments[1],segments[2]));	
						}
					}else{
						hasMore = false;
					}
			}
			System.out.println("	Done Loading Ratings");
			return ratings;
		}  catch (FileNotFoundException e) {
			System.out.println("ERROR!: Cannot Find file: " + fileURL);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR!: Cannot Parse File: " + fileURL);
			e.printStackTrace();
		}
		return null;		
	}
	 
}
