package domain;

import java.util.ArrayList;
import java.util.List;

import datasource.SubjectDataMapper;

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
	
	
	public List<Student> getAllStudent(){
		
		SubjectDataMapper datamapper = new SubjectDataMapper();
		return datamapper.loadStudentsBySubject(this.subjectCode);
		
	}
	
	public List<Teacher> getAllTeacher(){
		
		SubjectDataMapper datamapper = new SubjectDataMapper();
		return datamapper.loadTeachersBySubject(this.subjectCode);
		
	}
	
	public List<Exam> getAllExam(){
		
		SubjectDataMapper datamapper  = new SubjectDataMapper();
		return datamapper.loadAllExamBySubject(this.subjectCode);
	}
	
	public List<Exam> getPublishedExams(Student student){
		
		SubjectDataMapper datamapper  = new SubjectDataMapper();
		return datamapper.loadPublishedExamsByStudent(student);
	}
	
	public boolean isValidExamName(String examname) {
		
		SubjectDataMapper datamapper  = new SubjectDataMapper();
		return datamapper.checkExamName(examname);
	}
	
	public boolean isValidExamType( String year, String semester, String examtype) {
		
		SubjectDataMapper datamapper  = new SubjectDataMapper();
		return datamapper.checkExamSignificantInfo(year, semester, examtype);
	}
	
}
