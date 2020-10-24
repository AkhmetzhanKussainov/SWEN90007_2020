package domain;

import java.util.List;

import datasource.SubjectDataMapper;
public class Admin {
	
	
	private String adminId;
	private String userId;
	private String username;
	private String password;
	
	public Admin(String userId, String username, String password, String adminId) {
			
		this.password = password;
		this.userId = userId;
		this.username = username;
		this.adminId = adminId;
		
	}
	
		
	public String getAdminId() {
		return adminId;
	}


	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


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
