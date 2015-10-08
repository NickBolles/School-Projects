package project5;

public class Rating {
	private String userId, movieId, rating;
	public Rating(String UserId, String MovieId, String Rating){
		userId = UserId;
		movieId = MovieId;
		rating = Rating;
	}
	public String getUserId(){
		return userId;
	}
	public String getMovieId(){
		return movieId;
	}
	public String getRating(){
		return rating;
	}
}
