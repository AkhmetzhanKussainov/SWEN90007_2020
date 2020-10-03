package domain;

import domain.User.houses;

public class Teacher extends User {
	
	private String teacherId;
	private houses house;
	private String firstName;
	private String lastName;
	private String title;
	
	public Teacher(String userId, String username, String password, String teacherId) {
		
		//Lazy initialization
		house = null;
		
		this.password = password;
		this.userId = userId;
		this.username = username;
		this.teacherId = teacherId;
		
	}
	
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
	
	public houses getHouse() {	
		return house;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setHouse(houses house) {
		
		this.house = house;
	}
	
	public void setFirstName(String firstName) {
			
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		
		this.lastName = lastName;
		
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	

}
