package project5;

public class Movie {
	private String id, title, genre, year, mpaa, directors, company;
	public Movie(String Id, String Title, String Genre, String Year, String Mpaa, String Directors, String Company){
		id =Id;
		title = Title;
		genre = Genre;
		year = Year;
		mpaa = Mpaa;
		directors = Directors;
		company = Company;
	}
	public String getId(){
		return id;
	}
	public String getTitle(){
		return title;
	}
	public String getGenre(){
		return genre;
	}
	public String getYear(){
		return year;
	}
	public String getMpaa(){
		return mpaa;
	}
	public String getDirectors(){
		return directors;
	}
	public String getCompany(){
		return company;
	}
}
