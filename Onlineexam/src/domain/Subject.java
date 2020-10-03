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
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		
	}
	
	public String getCode() {
		return subjectCode;
	}
	
	public String getName() {
		return subjectName;
	}
	
	public void setName(String name) {
		subjectName = name;
	}
	
	public void assignStudent(Student s) {
		enrolledStudents.add(s);
	}
	
	public void assignTeacher(Teacher t) {
		appointedTeachers.add(t);
	}
	
	public void removeStudent(Student s) {
		enrolledStudents.remove(s);
	}
	
	public void removeTeacher(Teacher t) {
		appointedTeachers.remove(t);
	}
	
}
