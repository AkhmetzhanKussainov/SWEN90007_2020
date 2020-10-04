package uow;

import java.util.ArrayList;

import datasource.ExamDataMapper;
import domain.Exam;

public class ExamUow {
	
	
	private static ArrayList<Exam> newExam = new ArrayList<Exam>();
	private static ArrayList<Exam> dirtyExam = new ArrayList<Exam>();
	private static ArrayList<Exam> deleteExam = new ArrayList<Exam>();
	
	public static void registerNew(Exam e) {
		
		 if(!newExam.contains(e)) {
			 newExam.add(e);
		 }
	}
	
	public static void registerDirty(Exam e) {
		
		 if(!dirtyExam.contains(e)) {
			 dirtyExam.add(e);
		 }
	}
	
	public static void registerDelete(Exam e) {
		
		 if(!deleteExam.contains(e)) {
			 deleteExam.add(e);
		 }
	}
	
	public static String commitAll() {
		
		boolean newRes = true;
		boolean newDir = true;
		boolean newDel = true;
		
		for(Exam ex: newExam) {
			try {
				ExamDataMapper dm = new ExamDataMapper();
				dm.publishExam(ex);
			}catch(Exception e){
				newRes = false;
			}
		}
		
		for(Exam ex: dirtyExam) {
			try {
				ExamDataMapper dm = new ExamDataMapper();
				dm.changeExam(ex);
			}catch(Exception e){
				newDir = false;
			}
		}
		
		
		for(Exam ex:deleteExam) {
			try {
				ExamDataMapper dm = new ExamDataMapper();
				dm.deleteExam(ex);
			}catch(Exception e){
				newDel = false;
			}
		}
		
		newExam.clear();
		dirtyExam.clear();
		deleteExam.clear();
		
		
		return "Unit of work process on create:"+String.valueOf(newRes)
				+"\n on update:"+String.valueOf(newDir)
				+"\n on delete:"+String.valueOf(newDel);
	}
}
