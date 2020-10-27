package service;

import java.util.Arrays;
import java.util.List;

import Exceptions.DatabaseException;
import Exceptions.FormException;
import datasource.*;
import domain.*;

public class AdminService {
	
	private UserDataMapper um;
	
	private SubjectDataMapper sm;
	
	private ExamDataMapper em;
	
	public AdminService() {
		
		um = new UserDataMapper();

		sm = new SubjectDataMapper();
		
		
		
	}
	
	public Subject getSubject(String subjectId) {

		return sm.loadSubject(subjectId);
		
	}
	
	public boolean hasStudentsBySubject(String subjectId) {
		
		return um.loadFullStudentBySubject(subjectId).size() > 0;
		
	}
	
	public boolean hasTeachersBySubject(String subjectId) {
		
		return um.loadFullTeacherBySubject(subjectId).size() > 0;
		
	}
	
	public boolean hasExamsBySubject(String subjectId) {
		
		return sm.loadAllExamBySubject(subjectId).size() > 0;
		
	}
	
	public List<Student> getStudentsBySubject(String subjectId) {
		
		return  um.loadFullStudentBySubject(subjectId);
		
	}
	
	public List<Teacher> getTeachersBySubject(String subjectId) {
		
		return  um.loadFullTeacherBySubject(subjectId);
		
	}

	public List<Exam> getExamsBySubject(String subjectId) {
		
		return  sm.loadAllExamBySubject(subjectId);
		
	}
	
	public List<Student> getAllStudents() {
		um.loadAllUsers();
		return  um.getAllStudents();
		
	}
	
	public List<Teacher> getAllTeachers() {
		//um.loadAllUsers();
		return  um.getAllTeachers();
		
	}
	
	//Takes a subject id, name, list of strings of students, list of strings of teachers
	public void createSubject(String subjectId, String subjectName, String[] studentsList, String[] teachersList, String year, String semester) throws FormException, DatabaseException  {
		
		if(studentsList==null || teachersList==null) {
		
			throw new FormException("Must have at least one student and teacher", null);
			
		}
		
		//Convert the arrays
		List<String> students = Arrays.asList(studentsList);
		List<String> teachers = Arrays.asList(teachersList);
		
		//If can't write the subject, don't continue with the enrollments
		
		try {sm.writeSubject(subjectId, subjectName);}
		
		catch (Exception e) {
			
			throw new DatabaseException("Unable to create subject: "+e.getMessage(), e);
			
		}
		
		
		
		//Write student enrollments
		for(String student : students) {
			sm.writeEnrollment(student, subjectId, year, semester);
		}
		
		//Write teacher appointments
		for(String teacher : teachers) {
			sm.writeAppointment(teacher, subjectId, year, semester);
		}
					
	
	}
	
	
}
