package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import datasource.DBConnection;
import datasource.ExamDataMapper;
import domain.Exam;
import domain.MultipleAttempt;
import domain.MultipleQuestion;
import domain.ShortQuestion;
import domain.Question.choice;
import domain.Question;
import domain.Scriptbook;
import domain.ShortAttempt;
import uow.QuestionUow;

public class ScriptbookService {
	
	public ScriptbookService() {
		
	}

	public void submitScriptbook(List<MultipleAttempt> multipleAttempts, List<ShortAttempt> shortAttempts) {
		System.out.println(multipleAttempts);
		for(MultipleAttempt ma: multipleAttempts) {
//			System.out.println(ma.getQuestionId());
			System.out.println(ma.getAttemptedAns());
		}
		System.out.println(shortAttempts);
	}
	
	public void submitScriptbookMarks(List<MultipleAttempt> multipleAttempts, List<ShortAttempt> shortAttempts) {
		System.out.println(multipleAttempts);
		for(MultipleAttempt ma: multipleAttempts) {
//			System.out.println(ma.getQuestionId());
			System.out.println(ma.getMark());
		}
		System.out.println(shortAttempts);
	}
	
	public void initiateScriptbook(Scriptbook sb) {
		System.out.println(sb);
	}
	
	public void markScriptbook(Scriptbook sb) {
		System.out.println(sb);
		System.out.println(sb.getTotalMark());
	}

	
	public List<Scriptbook> getExamScriptbooks(String subjectCode, String year, String semester, String examType) {
		
		List<Scriptbook> scriptbooks = new ArrayList<>();
		
		try {
			
			//Scriptbook(String subjectId, String year, String semester, String examType, String studentId, int scriptMark, boolean marked)
	
			Scriptbook sb1 = new Scriptbook("DEF101", "2020", "2", "F", "S12", 0, false, true);
			Scriptbook sb2 = new Scriptbook("DEF101", "2020", "2", "F", "S13", 80, true, true);
			Scriptbook sb3 = new Scriptbook("DEF101", "2020", "2", "F", "S14", 80, true, true);
			Scriptbook sb4 = new Scriptbook("DEF101", "2020", "2", "F", "S15", 80, true, true);
			
			scriptbooks.add(sb1);
			scriptbooks.add(sb2);
			scriptbooks.add(sb3);
			scriptbooks.add(sb4);
		
				
	} catch (Exception e) {
		System.out.println(e);

	}
	
	
	return scriptbooks;
		
	}
	
	
	private List<MultipleAttempt> multipleattempts;
	
	
	private List<ShortAttempt> shortattempts;
	
	public List<MultipleAttempt> getAllMultipleAttempts(String subjectCode, String semester, String year, String examType, String studentNumber) {
	    
		List<MultipleAttempt> multipleAttempts = new ArrayList<>();
		
		//MultipleAttempt(String questionId, String subjectId, String year, String semester, String examType,
		//String studentNumber, String attemptedAns)
		
		MultipleAttempt multia1 = new MultipleAttempt("1", "DEF101", "2020", "2", "M", "S12", "A", "A", 5, "What is life?");
		MultipleAttempt multia2 = new MultipleAttempt("2", "DEF101", "2020", "2", "M", "S12", "B", "C", 5, "Why?");
		MultipleAttempt multia3 = new MultipleAttempt("3", "DEF101", "2020", "2", "M", "S12", "C", "C", 5, "What?");
		MultipleAttempt multia4 = new MultipleAttempt("4", "DEF101", "2020", "2", "M", "S12", "D", "D", 5, "How");
//		MultipleQuestion(String id, String subjectCode, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int answerNumber
		
		
		multipleAttempts.add(multia1);
		multipleAttempts.add(multia2);
		multipleAttempts.add(multia3);
		multipleAttempts.add(multia4);
		
	    return multipleAttempts;
	    
	}
	
	
public List<ShortAttempt> getAllShortAttempts(String subjectCode, String semester, String year, String examType, String studentNumber) {
	    
		List<ShortAttempt> shortAttempts = new ArrayList<>();
		
		//MultipleAttempt(String questionId, String subjectId, String year, String semester, String examType,
		//String studentNumber, String attemptedAns)
		
		ShortAttempt shorta1 = new ShortAttempt("1", "DEF101", "2020", "2", "M", "S12", "something", 5, "What is life?");
		ShortAttempt shorta2 = new ShortAttempt("2", "DEF101", "2020", "2", "M", "S12", "something asa", 5, "What");
		ShortAttempt shorta3 = new ShortAttempt("3", "DEF101", "2020", "2", "M", "S12", "something asdsad", 5, "Why");
		ShortAttempt shorta4 = new ShortAttempt("4", "DEF101", "2020", "2", "M", "S12", "something asdasdad", 5, "Done");
//		MultipleQuestion(String id, String subjectCode, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int answerNumber
		
		
		shortAttempts.add(shorta1);
		shortAttempts.add(shorta2);
		shortAttempts.add(shorta3);
		shortAttempts.add(shorta4);
		
	    return shortAttempts;
	    
	}
	

}
