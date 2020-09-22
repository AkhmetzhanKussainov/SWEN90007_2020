package domain;

import domain.User.houses;

public class Teacher extends User {
	
	private String teacherId;
	private houses house;
	private String firstName;
	private String lastName;
	
	public String getUserName() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getTeacherId() {
		return teacherId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	
	public Teacher(String userId, String username, String password, String teacherId) {
		
		//Lazy initialization
		house = null;
		
		this.password = password;
		this.userId = userId;
		this.username = username;
		this.teacherId = teacherId;
		
	}

}
