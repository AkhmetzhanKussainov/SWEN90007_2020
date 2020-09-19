package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.IdentityMap;

import domain.*;

public class UserDataMapper {
	
	//For Identity map types
	private Student student;
	private Teacher teacher;
	private Admin admin;
	
	private static final String findUser ="SELECT * FROM users WHERE userId=";
	
	private String removeType(String string) {
		
		return string.substring(1);
		
	}
	
	
	public
	

    List<MultipleQuestion> multiplequestions = new ArrayList<>();
	
	multiplequestions.add(new 
	
	
	
	public Student loadStudent(String userId) {
		
		String findUserSpecified = findUser + userId;
		
		List<MultipleQuestion> multiplequestions = new ArrayList<>();
	    try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(findUserSpecified);
	    	
	    	ResultSet rs = stmt.executeQuery();
	    	
			while (rs.next()) {
				
				String userId = rs.getString(1);
				String userName = rs.getString(2);
				String password = rs.getString(3);
				String studentId = removeType(rs.getString(5));
				
				multiplequestions.add(new MultipleQuestion(id, question, ans1, ans2, ans3, ans4, chosen_ans, mark));
			
			}
					
		} catch (SQLException e) {
	
		}		
		
	}
	
	public getStudentHouse(String userId) {
		
		//Get access to the singleton instance
		IdentityMap<Student> identityMap = IdentityMap.getInstance(student);
		
		//Get the student 
		Student tempStudent = identityMap.get(userId);
		
		//Check if null
		if (tempStudent == null) {
			
			loadStudent(userId);
			
		}
		
		return tempStudent.getHouse();		
				
	}

	public String getStudentStudentId(String userId) {
		
		//Get access to the singleton instance
		IdentityMap<Student> identityMap = IdentityMap.getInstance(student);
		
		//Get the student 
		Student tempStudent = identityMap.get(userId);
		
		//Check if null
		if (tempStudent == null) {
			
			loadStudent(userId);
			
		}
		
		return tempStudent.getStudentId();

	}
	
	
	
	
}
