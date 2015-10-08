package project5;

public class User {
	private String id, password, age, gender;
	public User(String Id, String Password, String Age, String Gender){
		id =Id;
		password = Password;
		age = Age;
		gender = Gender;
	}
	public String getId(){
		return id;
	}
	public String getPassword(){
		return password;
	}
	public String getAge(){
		return age;
	}
	public String getGender(){
		return gender;
	}
}
