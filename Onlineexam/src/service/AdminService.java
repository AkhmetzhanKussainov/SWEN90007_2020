package service;

import java.util.List;

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
	
}
