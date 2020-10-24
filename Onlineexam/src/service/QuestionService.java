package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.DBConnection;
import datasource.ExamDataMapper;
import domain.Exam;
import domain.MultipleQuestion;
import domain.ShortQuestion;
import domain.Question.choice;
import domain.Question;
import uow.QuestionUow;

public class QuestionService {
	
	ExamDataMapper edm;
	
	public QuestionService() {
		
		edm = new ExamDataMapper();
		
		//multipleQuestions = qdm.loadMultipleChoiceQuestions();
		//shortQuestions = qdm.loadShortQuestions();
		
	}
	
	private List<MultipleQuestion> multipleQuestions;
	
	
	private List<ShortQuestion> shortQuestions;
	
	public List<MultipleQuestion> getAllMultipleQuestions(String subjectCode, String semester, String year, String examType) {
	    
		List<MultipleQuestion> multipleQuestions = new ArrayList<>();
		
		multipleQuestions = edm.loadMultipleQuestionsForExam(subjectCode, examType, year, semester);
		
	//	MultipleQuestion multiq1 = new MultipleQuestion("1", "DEF101", "2020", "2", "M", "What is life?", "None", "Null", "None", "All the above", choice.D, 5, 4);
	///	MultipleQuestion multiq2 = new MultipleQuestion("2", "DEF101", "2020", "2", "M", "Why?", "None", "Null", "None", "All the above", choice.D, 5, 4);
	//	MultipleQuestion multiq3 = new MultipleQuestion("3", "DEF101", "2020", "2", "M", "What?", "None", "Null", "None", "All the above", choice.D, 5, 4);
	//	MultipleQuestion multiq4 = new MultipleQuestion("4", "DEF101", "2020", "2", "M", "How", "None", "Null", "None", "All the above", choice.D, 5, 4);
//		MultipleQuestion(String id, String subjectCode, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int answerNumber
		
		
	//	multipleQuestions.add(multiq1);
	//	multipleQuestions.add(multiq2);
	//	multipleQuestions.add(multiq3);
	//	multipleQuestions.add(multiq4);
		
	    return multipleQuestions;
	    
	}
	
	
	public MultipleQuestion getMultipleQuestion(String Id,String subjectCode, String semester, String year, String examType) {
	    
		List<MultipleQuestion> temp = edm.loadMultipleQuestionsForExam(subjectCode, examType, year, semester);
		for(int i=0;i<temp.size();i++)
		{
			if(temp.get(i).getId().equals(Id))
			{
				return temp.get(i);
			}
		}
		//MultipleQuestion multiq1 = new MultipleQuestion(Id, subjectCode, year, semester, examType, "What is life?", "None", "Null", "None", "All the above", choice.D, 5, 4);
		
	    return null;
	    
	}
	
	public List<ShortQuestion> getAllShortQuestions(String subjectCode, String semester, String year, String examType) {
	    
		List<ShortQuestion> shortQuestions = new ArrayList<>();
		
		ShortQuestion shortq1 = new ShortQuestion("1", "DEF101", "2020", "2", "M", "What is life?", 10);
		ShortQuestion shortq2 = new ShortQuestion("2", "DEF101", "2020", "2", "M", "What", 10);
		ShortQuestion shortq3 = new ShortQuestion("3", "DEF101", "2020", "2", "M", "Why", 10);
		ShortQuestion shortq4 = new ShortQuestion("4", "DEF101", "2020", "2", "M", "Done", 10);
//		MultipleQuestion(String id, String subjectCode, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int answerNumber
		
		
		shortQuestions.add(shortq1);
		shortQuestions.add(shortq2);
		shortQuestions.add(shortq3);
		shortQuestions.add(shortq4);
		
	    return shortQuestions;
	    
	}
	
	public ShortQuestion getShortQestion(String Id,String subjectCode, String semester, String year, String examType) {
	
		
		ShortQuestion shortq = new ShortQuestion(Id, subjectCode, year, semester, examType, "What is this?", 10);
		
	    return shortq;
	    
	}
	
	public void addMultipleQuestion(Exam exam, MultipleQuestion mq)
	{
		edm.addMultipleQuestions(exam, mq);
	}
	
	public void addShortQuestion(Exam exam, ShortQuestion sq)
	{
		edm.addShortQuestions(exam, sq);
	}
	
	public void deleteMultipleQuestion(Exam exam, String sq)
	{
		edm.deleteMultiplleQuestionsById(exam, sq);
		//edm.addShortQuestions(exam, sq);
	}
	
	public void deleteShortQuestion(Exam exam, String sq)
	{
		edm.deleteShortQuestionsById(exam, sq);
		//edm.addShortQuestions(exam, sq);
	}
	
	
	public void updateUow(Question q) {
		QuestionUow.registerDirty(q);
	}
	
	public void commitUow() {
		QuestionUow.commitAll();
	}


}
