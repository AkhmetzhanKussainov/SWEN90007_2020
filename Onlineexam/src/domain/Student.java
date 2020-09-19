package domain;

import datasource.UserDataMapper;

//import domain.User.houses;

public class Student extends User {

	private String studentId;
	private houses house;
	
	public Student() {
		
		
	}
	
	public Student(String userId, String username, String password) {
		
		//Lazy initialization
		studentId = null;
		house = null;
		
		this.password = password;
		this.userId = userId;
		this.username = username;
		
	}
	
	//Lazy load
	private void getInfo() {
		
		UserDataMapper dataMapper = new UserDataMapper();
		
		studentId = dataMapper.getStudentStudentId(userId);
		house = dataMapper.getStudentHouse(userId);
		
	}
	
	public houses getHouse() {
		
		if (house == null) {
				
			getInfo();
			
		}
		
		return house;
		
	}
	
	public getStudentId() {
		
		if (studentId == null) {
			
			getInfo();
			
		}
		
		return studentId;
		
	}
	
}
