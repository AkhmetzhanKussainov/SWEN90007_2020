package domain;

import java.util.List;

import datasource.UserDataMapper;

import domain.User.houses;

public class Student extends User {

	private String studentId;
	private houses house;
	
	private String firstName;
	private String lastName;
	
	public void setHouse(houses house) {
		
		this.house = house;
	}
	
	public void setFirstName(String firstName) {
			
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		
		this.lastName = lastName;
		
	}
	
	public void setStudentId(String studentId) {
		
		this.studentId = studentId;
	
	}
	
	public String getUserName() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	
	public Student(String userId, String username, String password, String studentId) {
		
		//Lazy initialization
		//studentId = null;
		house = null;
		
		this.password = password;
		this.userId = userId;
		this.username = username;
		this.studentId = studentId;
		
	}
	
	//Lazy load
	private void getInfo() {
		
		UserDataMapper dataMapper = new UserDataMapper();
		
		Student tempStudent = dataMapper.loadFullStudent(studentId);
		
		house = tempStudent.getHouse();
		firstName = tempStudent.getFirstName();
		lastName = tempStudent.getLastName();
		
	}
	
	public houses getHouse() {
		
		if (house == null) {
				
			getInfo();
			
		}
		
		return house;
		
	}
	
	
	public String getFirstName() {
		
		if (firstName == null) {
				
			getInfo();
			
		}
		
		return firstName;
		
	}
	
	public String getLastName() {
		
		if (lastName == null) {
				
			getInfo();
			
		}
		
		return lastName;
		
	}
	
	public String getHouseAsString() {
		
		String houseAsString = getHouse().name();
		
		return houseAsString;
		
	}
	
	public List<Subject> getAllSubjects(){
		
		UserDataMapper datamapper = new UserDataMapper();
		
		return datamapper.loadSubjectByStudent(this);
	}
	
	/*
	public String getStudentId() {
		
		if (studentId == null) {
			
			getInfo();
			
		}
		
		return studentId;
		
	}*/
	
}
