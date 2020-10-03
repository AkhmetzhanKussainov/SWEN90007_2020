package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.IdentityMap;

import domain.*;

import domain.User.houses;

public class UserDataMapper {
	
	private static final String findUser = "SELECT * FROM users WHERE userId=";
	private static final String findStudentFull = "SELECT * FROM students JOIN users ON userNumber=studentNumber WHERE userid=";
	private static final String findTeacherFull = "SELECT * FROM teachers JOIN users ON userNumber=teacherNumber WHERE userid=";
	private static final String findAllUsers = "SELECT * FROM users";
	

	
	private static final String findSubjectByStudent = 
			
			"SELECT * from subjects "
			+ "JOIN enrollments ON enrollments.subjectId = subjects.subjectId "
			+ "WHERE studentNumber = ?";
	
	private static final String findSubjectByTeacher = 
			
			"SELECT * from subjects "
			+ "JOIN appointments ON appointments.subjectId = subjects.subjectId "
			+ "WHERE teacherNumber = ?";
	
	//private static final String updateStudenthouse = "UPDATE students SET house = ? where studentNumber = ? ";
	private List<Student> studentList;
    private List<Teacher> teacherList;
    private List<Admin> adminList;
	
	public UserDataMapper() {
		
		studentList = new ArrayList<>();
	    teacherList = new ArrayList<>();
	    adminList = new ArrayList<>();
		
		
	}	
	
	public void loadAllUsers() {
		
  	    try {
  	    	
  	    	PreparedStatement stmt = DBConnection.prepare(findAllUsers);
  	    	
  	    	ResultSet rs = stmt.executeQuery();
  	    	
  			while (rs.next()) {
  				
  				String userId = rs.getString(1);
  				String userName = rs.getString(2);
  				String password = rs.getString(3);
  				String userType = rs.getString(4);
  				String userNumber = rs.getString(5);
  				
  				if (userType.equals("S")) {
  				
	  				Student student = new Student(userId, userName, password, userNumber);
	    
	  				studentList.add(student);
	  				
	  				
	  				//Put the data in the identity map
	  				IdentityMap<Student> identityMap = IdentityMap.getInstance(student);
	  				
	  				//Put the student 
	  				identityMap.put(userId, student);
	  				
  				}
  				
  				if (userType.equals("T")) {
  	  				
	  				Teacher teacher = new Teacher(userId, userName, password, userNumber);
	    
	  				teacherList.add(teacher);
	  				
	  				
	  				//Put the data in the identity map
	  				IdentityMap<Teacher> identityMap = IdentityMap.getInstance(teacher);
	  				
	  				//Put the student 
	  				identityMap.put(userId, teacher);
	  				
  				}
  				
  				
  			}
  			
  			
			
		} catch (SQLException e) {
	
		}		
		
	
	}
	
	public List<Student> getAllStudents() {
		
		return studentList;
		
	}
	
	public List<Teacher> getAllTeachers() {
		
		return teacherList;
		
	}
	
	private houses convertHouse(String houseKey) {
		
		if (houseKey.equals("G")) {
			return houses.Gryffindor;
		}
		
		if (houseKey.equals("S")) {
			return houses.Slytherin;
		}
		
		if (houseKey.equals("H")) {
			return houses.Hufflepuff;
		}
		
		if (houseKey.equals("R")) {
			return houses.Ravenclaw;
		}
		
		return null;
		
	}
	
	//Load all of the information
	public Student loadFullStudent(String userId) {
		
		//String findUserSpecified = findUser + userId;
		String findStudentFullSpecified = findStudentFull + userId; 
		
	    try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(findStudentFullSpecified);
	    	
	    	ResultSet rs = stmt.executeQuery();
	    	while(rs.next()) {
		
				
				String userName = rs.getString(5);
				String password = rs.getString(3);
				String studentId = rs.getString(1);
				//String userId = rs.getString(5);
				
				houses house = convertHouse(rs.getString(2));
				String firstName = rs.getString(3);
				String lastName = rs.getString(4);
			
				Student student = new Student(userId, userName, password, studentId);
				
				//Set the new information
				student.setFirstName(firstName);
				student.setLastName(lastName);
				student.setHouse(house);
				student.setStudentId(studentId);
				
				//Update student in the Identity Map
				//Get access to the singleton instance
				IdentityMap<Student> identityMap = IdentityMap.getInstance(student);
				
				//Replace the student 
				identityMap.put(userId, student);
				
				return student;
	    	}
			
					
		} catch (SQLException e) {
			System.out.println(e);
		}		
		
	    return null;
	    
	}
	
	public Teacher loadFullTeacher(String userId) {
		
		//String findUserSpecified = findUser + userId;
		String findTeacherFullSpecified = findTeacherFull + userId; 
		
	    try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(findTeacherFullSpecified);
	    	
	    	ResultSet rs = stmt.executeQuery();
	    	while(rs.next()) {
				
				
				String userName = rs.getString(7);
				String password = rs.getString(8);
				String teacherId = rs.getString(1);
				//String userId = rs.getString(6);
				
				houses house = convertHouse(rs.getString(2));
				String firstName = rs.getString(3);
				String lastName = rs.getString(4);
			
				Teacher teacher = new Teacher(userId, userName, password, teacherId);
				
				//Set the new information
				teacher.setFirstName(firstName);
				teacher.setLastName(lastName);
				teacher.setHouse(house);
				
				//Update teacher in the Identity Map
				//Get access to the singleton instance
				IdentityMap<Teacher> identityMap = IdentityMap.getInstance(teacher);
				
				//Replace the student 
				identityMap.put(userId, teacher);
				
				return teacher;
	    	}
				
			
					
		} catch (SQLException e) {
			System.out.println(e);
		}		
		
	    return null;
	    
	}

	
	
    public List<Subject> loadSubjectByStudent(Student s){
    	
    	List<Subject> subjects = new ArrayList<Subject>();
        
    	try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(findSubjectByStudent);
	    	
	    	String studentID = s.getStudentId();
	    	
	    	stmt.setString(1, studentID); 	
	    	System.out.println(stmt);
	    	
	    	ResultSet rs = stmt.executeQuery();
	    	
			while (rs.next()) {
				
				String subjectId = rs.getString(1);
				String name = rs.getString(2);
				Subject subject = new Subject(subjectId, name);
				
				subjects.add(subject);
				
				IdentityMap<Subject> identityMap = IdentityMap.getInstance(subject);
				identityMap.put(subjectId, subject);
				
			}
										
		} catch (SQLException e) {
	
		}   	
    	return subjects;
    	
    }

	public List<Subject> loadSubjectByTeacher(Teacher t){
	    	
	    	List<Subject> subjects = new ArrayList<Subject>();
	  
	    	try {
		    	
		    	PreparedStatement stmt = DBConnection.prepare(findSubjectByTeacher);
		    	
		    	String teacherID = t.getTeacherId();
		    	
		    	stmt.setString(1, teacherID); 	
		    	System.out.println(stmt);
		    	
		    	ResultSet rs = stmt.executeQuery();
		    	System.out.println("reached");
		    	
		    	
				while (rs.next()) {
					
					String subjectId = rs.getString(1);
					String name = rs.getString(2);
					
					Subject subject = new Subject(subjectId, name);
					
					subjects.add(subject);
					
					IdentityMap<Subject> identityMap = IdentityMap.getInstance(subject);
					identityMap.put(subjectId, subject);
			
				}
											
			} catch (SQLException e) {
				System.out.println(e);
			}   	
	    	return subjects;
	    	
	    }
	

	
	
}
