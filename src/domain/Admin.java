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
	
	
}
