package uow;

import java.util.ArrayList;
import domain.Question;
import datasource.ExamDataMapper;

public class QuestionUow {

	private static ArrayList<Question> newQuestion = new ArrayList<Question>();
	private static ArrayList<Question> dirtyQuestion = new ArrayList<Question>();
	private static ArrayList<Question> deleteQuestion = new ArrayList<Question>();
	
	public static void registerNew(Question q) {
		
		 if(!newQuestion.contains(q)) {
			 newQuestion.add(q);
		 }
	}
	
	public static void registerDirty(Question q) {
		
		 if(!dirtyQuestion.contains(q)) {
			 dirtyQuestion.add(q);
			 String questionNum = q.getId();
			 int questionMark = q.getPossibleMark();
			 System.out.println("Question "+questionNum+" mark to be "+questionMark+" has been saved in the memory.");
		 }
	}
	
	public static void registerDelete(Question q) {
		
		 if(!deleteQuestion.contains(q)) {
			 deleteQuestion.add(q);
		 }
	}
	
	public static String commitAll() {
		
		boolean newRes = true;
		boolean newDir = true;
		boolean newDel = true;
		
		for(Question q:newQuestion) {
			try {
				//Create datamapper
				//datamapper.addnew
			}catch(Exception e){
				newRes = false;
			}
		}
		
		/**for(Question q:dirtyQuestion) {
			try {
				ExamDataMapper datamapper = new ExamDataMapper();
				datamapper.updateMarks(q);
			}catch(Exception e){
				newDir = false;
			}
		}*/
		
		for(Question q:deleteQuestion) {
			try {
				//Create datamapper
				//datamapper.delete
			}catch(Exception e){
				newDel = false;
			}
		}
		
		newQuestion.clear();
		dirtyQuestion.clear();
		deleteQuestion.clear();
		
		
		return "Unit of work process on create:"+String.valueOf(newRes)
				+"\n on update:"+String.valueOf(newDir)
				+"\n on delete:"+String.valueOf(newDel);
	}
}
