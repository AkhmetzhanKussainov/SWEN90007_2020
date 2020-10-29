package uow;

import java.util.ArrayList;
import domain.Teacher;
import datasource.UserDataMapper;

public class TeacherUow {

	private static ArrayList<Teacher> newTeacher = new ArrayList<Teacher>();
	private static ArrayList<Teacher> dirtyTeacher = new ArrayList<Teacher>();
	private static ArrayList<Teacher> deleteTeacher = new ArrayList<Teacher>();
	
	public static void registerNew(Teacher t) {
		
		 if(!newTeacher.contains(t)) {
			 newTeacher.add(t);
		 }
	}
	
	public static void registerDirty(Teacher t) {
		
		 if(!dirtyTeacher.contains(t)) {
			 dirtyTeacher.add(t);
		 }
	}
	
	public static void registerDelete(Teacher t) {
		
		 if(!deleteTeacher.contains(t)) {
			 deleteTeacher.add(t);
		 }
	}
	
	public static String commitAll() {
		
		boolean newRes = true;
		boolean newDir = true;
		boolean newDel = true;
		
		for(Teacher t:newTeacher) {
			try {
				UserDataMapper datamapper = new UserDataMapper();
				
				datamapper.createTeacher(t);
				
			}catch(Exception e){
				newRes = false;
			}
		}
		
		for(Teacher t:dirtyTeacher) {
			try {
				UserDataMapper datamapper = new UserDataMapper();
				
				datamapper.updateTeacher(t);
				
			}catch(Exception e){
				newDir = false;
			}
		}
		
		for(Teacher t:deleteTeacher) {
			try {
				
				UserDataMapper datamapper = new UserDataMapper();
				datamapper.deleteTeacher(t.getTeacherId());
				
			}catch(Exception e){
				newDel = false;
			}
		}
		
		newTeacher.clear();
		dirtyTeacher.clear();
		deleteTeacher.clear();
		
		
		return "Unit of work process on create:"+String.valueOf(newRes)
				+"\n on update:"+String.valueOf(newDir)
				+"\n on delete:"+String.valueOf(newDel);
	}
}

