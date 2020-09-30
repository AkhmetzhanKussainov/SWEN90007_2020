package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Exam;
import domain.MultipleQuestion;
import domain.Scriptbook;
import domain.ShortQuestion;
import domain.Student;
import domain.Subject;
import domain.Teacher;

public class SubjectDataMapper {
	
	private static final String findAllSubjects = 
			
			"SELECT * from subjects";
	
	private static final String findSubject =
			"SELECT * from subjects WHERE subjectId = ?";
	
	private static final String findSubjectByStudent = 
			
			"SELECT * from subjects "
			+ "JOIN enrollments ON enrollments.subjectId = subjects.subjectId "
			+ "WHERE studentNumber = ?";
	
	private static final String findSubjectByTeacher = 
			
			"SELECT * from subjects "
			+ "JOIN appointments ON appointments.subjectId = subjects.subjectId"
			+ "WHERE teacherNumber = ?";
	
	
	
	private static final String createSubject = 
			"INSERT INTO subjects"
			+ "VALUES ('?', '?')";
	
	private static final String updateSubjectName = 
			"UPDATE subjects SET subjectName = ? WHERE subjectId = ?";
	
	private static final String deleteSubject = 
			"DELETE FROM subjects WHERE subjectId = ?";
		
	
	
	private static final String insertStudentSubject = 
			"INSERT INTO enrollments (year, semester, subjectId, studentNumber)"
			+ "VALUES ('?', '?', '?', '?')";
	
	private static final String insertTeacherSubject = 
			"INSERT INTO appointments (year, semester, subjectId, teacherNumber)"
			+ "VALUES ('?', '?', '?', '?')";
	
	private static final String deleteStudentSubject = 
			"DELETE FROM enrollments WHERE studentNumber = ? AND subjectId = ? ";
	
	private static final String deleteTeacherSubject = 
			"DELETE FROM appointments WHERE teacherNumber = ? AND subjectId = ? ";

	
    public List<Subject> loadAllSubject() {
    	
    	List<Subject> subjects = new ArrayList<Subject>();
    
    	try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(findAllSubjects);
	    	
	    	ResultSet rs = stmt.executeQuery();
	    	
			while (rs.next()) {
				
				String subjectId = rs.getString(1);
				String name = rs.getString(2);
				
				Subject subject = new Subject(subjectId, name);
				
				subjects.add(subject);
				
				//identity map
				IdentityMap<Subject> identityMap = IdentityMap.getInstance(subject);
				identityMap.put(subjectId, subject);
		
			}
										
		} catch (SQLException e) {
	
		}   	
    	return subjects;
    }	
        
    public Subject loadSubject(String id) {
    	Subject instance = new Subject(null, null);
    	IdentityMap<Subject> identityMap = IdentityMap.getInstance(instance);
    	
    	if(identityMap.get(id)==null) {
    	
    	
		try {
			    	
			    	PreparedStatement stmt = DBConnection.prepare(findSubject);
			    	stmt.setNString(1, id);
			    	
			    	ResultSet rs = stmt.executeQuery();
		
					String subjectId = rs.getString(1);
					String name = rs.getString(2);
						
					Subject subject = new Subject(subjectId, name);
					return subject;
												
				} catch (SQLException e) {
			
				}
		
    	}else {
    		return identityMap.get(id);
    	}
		
		return null;
			
		    	
    }
    
    public List<Subject> loadSubjectByStudent(Student s){
    	
    	List<Subject> subjects = new ArrayList<Subject>();
        
    	try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(findSubjectByStudent);
	    	
	    	String studentID = s.getStudentId();
	    	
	    	stmt.setString(1, studentID); 	
	    	
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

	public List<Subject> loadSubjectTeacher(Teacher t){
	    	
	    	List<Subject> subjects = new ArrayList<Subject>();
	        
	    	try {
		    	
		    	PreparedStatement stmt = DBConnection.prepare(findSubjectByTeacher);
		    	
		    	String teacherID = t.getTeacherId();
		    	
		    	stmt.setString(1, teacherID); 	
		    	
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
    
    
    
    public void createSubject(String id, String name) {
	try {
		    	
		    	PreparedStatement stmt = DBConnection.prepare(createSubject);
		    	
		    	stmt.setString(1, id);
		    	stmt.setNString(2, name);
		    	
		    	stmt.executeQuery();

		    	Subject subject = new Subject(id,name);
		    	
		    	IdentityMap<Subject> identityMap = IdentityMap.getInstance(subject);
				identityMap.put(id, subject);
								
			} catch (SQLException e) {
		
		}
    }
    
    public void updateSubject(String id, String name) {
    	try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(updateSubjectName);
	    	
	    	stmt.setString(1, name);
	    	stmt.setNString(2, id);
	    	
	    	stmt.executeQuery();
	    	
	    	Subject subject = new Subject(id,name);
	    	
	    	IdentityMap<Subject> identityMap = IdentityMap.getInstance(subject);
			identityMap.put(id, subject);
							
		} catch (SQLException e) {
	
	}
    }
    
    public void deleteSubejct(String id) {
    	
	try {
		    	
		    	PreparedStatement stmt = DBConnection.prepare(deleteSubject);

		    	stmt.setNString(1, id);
		    	
		    	stmt.executeQuery();
		    	
		    	Subject subject = new Subject(id,null);
		    	
		    	IdentityMap<Subject> identityMap = IdentityMap.getInstance(subject);
				identityMap.remove(id);
								
			} catch (SQLException e) {
		
		}
    }
    

    public void assignStudentSubject(Student studentN, Subject subjectN) {
    	
    	try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(insertStudentSubject);
	    	
	    	
	    	String subjectID = subjectN.getCode();
	    	String studentID = studentN.getStudentId();
	    	
	    	stmt.setNString(1, "2020");
	    	stmt.setNString(2, "2");
	    	stmt.setNString(3, subjectID);
	    	stmt.setNString(4, studentID);
	    	
	    	
	    	stmt.executeQuery();
	    	
	    	
	    	
	    	IdentityMap<Subject> identityMap1 = IdentityMap.getInstance(subjectN);
	    	Subject subjectO = identityMap1.get(subjectID);
	    	
	    	IdentityMap<Student> identityMap2 = IdentityMap.getInstance(studentN);
	    	Student studentO = identityMap2.get(studentID);
	    	
	    	subjectO.assignStudent(studentO);
	    	
	    	identityMap1.put(subjectID, subjectO);
	    	
	    	
		} catch (SQLException e) {
	
	}
    }
    
	public void assignTeacherSubject(Teacher teacherN, Subject subjectN) {
	    	
	    	try {
		    	
		    	PreparedStatement stmt = DBConnection.prepare(insertTeacherSubject);
		    	
		    	String subjectID = subjectN.getCode();
		    	String teacherID = teacherN.getTeacherId();
		    	
		    	
		    	stmt.setNString(1, "2020");
		    	stmt.setNString(2, "2");
		    	stmt.setNString(3, subjectID);
		    	stmt.setNString(4, teacherID);
		    	
		    	stmt.executeQuery();
		    	
		    	IdentityMap<Subject> identityMap1 = IdentityMap.getInstance(subjectN);
		    	Subject subjectO = identityMap1.get(subjectID);
		    	
		    	IdentityMap<Teacher> identityMap2 = IdentityMap.getInstance(teacherN);
		    	Teacher teacherO = identityMap2.get(teacherID);
		    	
		    	subjectO.assignTeacher(teacherO);
		    	
		    	identityMap1.put(subjectID, subjectO);
								
			} catch (SQLException e) {
		
		}
	}
	
	public void removeStudentSubject(Student student, Subject subject) {
		try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(deleteStudentSubject);
	    	
	    	String studentID = student.getStudentId();
	    	String subjectID = subject.getCode();
	    	
	    	stmt.setNString(1, studentID);
	    	stmt.setNString(2, subjectID);
	    	
	    	stmt.executeQuery();
	    	
	    	IdentityMap<Subject> identityMap1 = IdentityMap.getInstance(subject);
	    	Subject subjectO = identityMap1.get(subjectID);
	    	
	    	IdentityMap<Student> identityMap2 = IdentityMap.getInstance(student);
	    	Student studentO = identityMap2.get(studentID);
	    	
	    	subjectO.removeStudent(studentO);
	    	
	    	identityMap1.put(subjectID, subjectO);
							
		} catch (SQLException e) {
	
	}
	}
	
	public void deleteTeacherSubject(Teacher teacher, Subject subject) {
    	
    	try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(deleteTeacherSubject);
	    	
	    	String teacherID = teacher.getTeacherId();
	    	String subjectID = subject.getCode();
	    	
	    	stmt.setNString(1, teacherID);
	    	stmt.setNString(2, subjectID);
	    	
	    	stmt.executeQuery();
	    	
	    	IdentityMap<Subject> identityMap1 = IdentityMap.getInstance(subject);
	    	Subject subjectO = identityMap1.get(subjectID);
	    	
	    	IdentityMap<Teacher> identityMap2 = IdentityMap.getInstance(teacher);
	    	Teacher teacherO = identityMap2.get(teacherID);
	    	
	    	subjectO.removeTeacher(teacherO);
	    	
	    	identityMap1.put(subjectID, subjectO);
							
		} catch (SQLException e) {
	
	}
}
}
