package uow;

import java.util.ArrayList;
import domain.Student;
import datasource.UserDataMapper;

public class StudentUow {

	private static ArrayList<Student> newStudent = new ArrayList<Student>();
	private static ArrayList<Student> dirtyStudent = new ArrayList<Student>();
	private static ArrayList<Student> deleteStudent = new ArrayList<Student>();
	
	public static void registerNew(Student s) {
		
		 if(!newStudent.contains(s)) {
			 newStudent.add(s);
		 }
	}
	
	public static void registerDirty(Student s) {
		
		 if(!dirtyStudent.contains(s)) {
			 dirtyStudent.add(s);
		 }
	}
	
	public static void registerDelete(Student s) {
		
		 if(!deleteStudent.contains(s)) {
			 deleteStudent.add(s);
		 }
	}
	
	public static String commitAll() {
		
		boolean newRes = true;
		boolean newDir = true;
		boolean newDel = true;
		
		for(Student s:newStudent) {
			try {
				UserDataMapper datamapper = new UserDataMapper();
				
				datamapper.createStudent(s);
				
			}catch(Exception e){
				newRes = false;
			}
		}
		
		for(Student s:dirtyStudent) {
			try {
				UserDataMapper datamapper = new UserDataMapper();
				
				datamapper.updateStudent(s);
				
			}catch(Exception e){
				newDir = false;
			}
		}
		
		for(Student s:deleteStudent) {
			try {
				
				UserDataMapper datamapper = new UserDataMapper();
				datamapper.deleteStudent(s.getStudentId());
				
			}catch(Exception e){
				newDel = false;
			}
		}
		
		newStudent.clear();
		dirtyStudent.clear();
		deleteStudent.clear();
		
		
		return "Unit of work process on create:"+String.valueOf(newRes)
				+"\n on update:"+String.valueOf(newDir)
				+"\n on delete:"+String.valueOf(newDel);
	}
}

