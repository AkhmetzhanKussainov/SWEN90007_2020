package domain;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	
	private String subjectCode;
	private String subjectName;
	
	private List<Student> enrolledStudents;
	private List<Teacher> appointedTeachers;
	private List<Exam> exams;
	
	public Subject(String subjectCode, String subjectName) {
		
		
		enrolledStudents = new ArrayList<>();
		appointedTeachers = new ArrayList<>();
		exams = new ArrayList<>();
		  
		
	}
	
	
	
	
}
