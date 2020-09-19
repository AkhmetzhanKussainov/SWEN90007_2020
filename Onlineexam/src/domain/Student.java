package domain;

public class Student extends User {

	public String studentId;
	
	public enum house;
	
	public Student(String userId, String username, String password) {
		
		//Lazy initialization
		this.studentId = null;
		this.house = null;
		
		this.username = username;
		
		
	}
	
	public enum getHouse() {
		
		
	}
	
}
