package datasource;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Exam;
import domain.MultipleQuestion;
import domain.Scriptbook;
import domain.ShortQuestion;
import domain.Student;
import domain.Subject;
import domain.Teacher;
import domain.User.houses;

public class SubjectDataMapper {
	
	
	
	private static final String findAllSubjects = 
			
			"SELECT * from subjects";
	
	private static final String findSubject =
			"SELECT * from subjects WHERE subjectId = ?";
	
	private static final String findStudentbySubject = "SELECT * FROM students "
			+ "JOIN enrollments ON enrollments.studentNumber=students.studentNumber "
			+ "JOIN users ON userNumber=students.studentNumber "
		+ "WHERE subjectId=";

	private static final String findTeacherbySubject = "SELECT * FROM teachers "
			+ "JOIN appointments ON appointments.teacherNumber=teachers.teacherNumber "
			+ "JOIN users ON userNumber=teachers.teacherNumber "
			+ "WHERE subjectId=";

	
	
	
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
	
	private String getYear() {
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(date);
	}
	
	private houses convertHouse(String houseKey) {
		
		if (houseKey.equals("G")) {
			return houses.Gryffindor;
		}
		
		if (houseKey.equals("S")) {
			return houses.Slytherin;
		}
		
		if (houseKey.equals("H")) {
			return houses.Hufflepuff;
		}
		
		if (houseKey.equals("R")) {
			return houses.Ravenclaw;
		}
		
		return null;
		
	}
	
	
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
    

    public List<Student> loadStudentsBySubject(String subjectId){
		//String findUserSpecified = findUser + userId;
		
				String findStudentFullSpecified = findStudentbySubject + subjectId; 
				List<Student> student_subjectList  = new ArrayList<Student>();
				
			    try {
			    	
			    	PreparedStatement stmt = DBConnection.prepare(findStudentFullSpecified);
			    	
			    	ResultSet rs = stmt.executeQuery();
			    	
			    	while(rs.next()) {
			    	
				    	
						String studentId = rs.getString(1);
						
						houses house = convertHouse(rs.getString(2));
						String firstName = rs.getString(3);
						String lastName = rs.getString(4);	
						
						String userId = rs.getString(10);
				    	String userName = rs.getString(11);
						String password = rs.getString(12);
						
						Student student = new Student(userId, userName, password, studentId);
						//Set the new information
						student.setFirstName(firstName);
						student.setLastName(lastName);
						student.setHouse(house);
						student.setStudentId(studentId);
							
						//Update student in the Identity Map
						//Get access to the singleton instance
						IdentityMap<Student> identityMap = IdentityMap.getInstance(student);
						
						//Replace the student 
						identityMap.put(userId, student);
						
						//put student in output list
						student_subjectList.add(student);
						
			    	}
						return student_subjectList;
							
				} catch (SQLException e) {
			
				}		
				
			    return null;
			    
	}

	public List<Teacher> loadTeacherBySubject(String subjectId){
		//String findUserSpecified = findUser + userId;
		
				String findteacherFullSpecified = findTeacherbySubject + subjectId; 
				List<Teacher> teacher_subjectList  = new ArrayList<Teacher>();
				
			    try {
			    	
			    	PreparedStatement stmt = DBConnection.prepare(findteacherFullSpecified);
			    	
			    	ResultSet rs = stmt.executeQuery();
			    	
			    	while(rs.next()) {
			    		
			    			
							String teacherId = rs.getString(1);
							
							
							houses house = convertHouse(rs.getString(2));
							String firstName = rs.getString(3);
							String lastName = rs.getString(4);
							
							String userId = rs.getString(11);
							
							String userName = rs.getString(12);
							String password = rs.getString(13);
						
							Teacher teacher = new Teacher(userId, userName, password, teacherId);
							
							//Set the new information
							teacher.setFirstName(firstName);
							teacher.setLastName(lastName);
							teacher.setHouse(house);
							
							//Update teacher in the Identity Map
							//Get access to the singleton instance
							IdentityMap<Teacher> identityMap = IdentityMap.getInstance(teacher);
							
							//Replace the student 
							identityMap.put(userId, teacher);
							
							teacher_subjectList.add(teacher);

						
			    	}
						return teacher_subjectList;
							
				} catch (SQLException e) {
			
				}		
				
			    return null;
			    
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
	    	
	    	stmt.setNString(1, getYear());
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
		    	
		    	
		    	
		    	stmt.setNString(1, getYear());
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
