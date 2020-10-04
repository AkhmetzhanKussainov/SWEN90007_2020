package domain;

import java.util.List;

import datasource.SubjectDataMapper;

public class Admin {
	
		
	public List<Subject> getAllSubject(){
		
		SubjectDataMapper datamapper = new SubjectDataMapper();
		return datamapper.loadAllSubject();
		
	}
	
	public boolean createSubject(String subjectId, String subjectName, List<Student> students, List<Teacher> teachers) {
		
		SubjectDataMapper datamapper = new SubjectDataMapper();
		
		
		if(!students.isEmpty() && !teachers.isEmpty()) {
			
			for(int i=0; i<students.size(); i++) {
				Student student = students.get(i);
				datamapper.assignStudentSubject(student, subjectId);
			}
			
			for(int i=0; i<teachers.size(); i++) {
				Teacher teacher = teachers.get(i);
				datamapper.assignTeacherSubject(teacher, subjectId);
			}
			
		}else{
			return false;
		}
		
		datamapper.createSubject(subjectId, subjectName);
		
		return true;

		
	}
}
