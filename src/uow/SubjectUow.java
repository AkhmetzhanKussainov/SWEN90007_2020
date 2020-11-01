package uow;

import java.util.ArrayList;
import domain.Subject;
import datasource.SubjectDataMapper;

public class SubjectUow {

	private static ArrayList<Subject> newSubject = new ArrayList<Subject>();
	private static ArrayList<Subject> dirtySubject = new ArrayList<Subject>();
	private static ArrayList<Subject> deleteSubject = new ArrayList<Subject>();
	
	public static void registerNew(Subject s) {
		
		 if(!newSubject.contains(s)) {
			 newSubject.add(s);
		 }
	}
	
	public static void registerDirty(Subject s) {
		
		 if(!dirtySubject.contains(s)) {
			 dirtySubject.add(s);
		 }
	}
	
	public static void registerDelete(Subject s) {
		
		 if(!deleteSubject.contains(s)) {
			 deleteSubject.add(s);
		 }
	}
	
	public static String commitAll() {
		
		boolean newRes = true;
		boolean newDir = true;
		boolean newDel = true;
		
		for(Subject s:newSubject) {
			try {
				SubjectDataMapper datamapper = new SubjectDataMapper();
				
				datamapper.createSubject(s.getCode(),s.getName());
				
			}catch(Exception e){
				newRes = false;
			}
		}
		
		for(Subject s:dirtySubject) {
			try {
				SubjectDataMapper datamapper = new SubjectDataMapper();
				
				datamapper.updateSubject(s.getCode(),s.getName());
				
			}catch(Exception e){
				newDir = false;
			}
		}
		
		for(Subject s:deleteSubject) {
			try {
				
				SubjectDataMapper datamapper = new SubjectDataMapper();
				datamapper.deleteSubject(s.getCode());
				
			}catch(Exception e){
				newDel = false;
			}
		}
		
		newSubject.clear();
		dirtySubject.clear();
		deleteSubject.clear();
		
		
		return "Unit of work process on create:"+String.valueOf(newRes)
				+"\n on update:"+String.valueOf(newDir)
				+"\n on delete:"+String.valueOf(newDel);
	}
}

