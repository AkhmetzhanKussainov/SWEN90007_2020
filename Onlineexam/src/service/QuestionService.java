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
	
	public QuestionService() {
		
//		ExamDataMapper qdm = new ExamDataMapper();
		
		//multipleQuestions = qdm.loadMultipleChoiceQuestions();
		//shortQuestions = qdm.loadShortQuestions();
		
	}
	
	private List<MultipleQuestion> multipleQuestions;
	
	
	private List<ShortQuestion> shortQuestions;
	
	public List<MultipleQuestion> getAllMultipleQuestions(String subjectCode, String semester, String year, String examType) {
	    
		List<MultipleQuestion> multipleQuestions = new ArrayList<>();
		
		MultipleQuestion multiq1 = new MultipleQuestion("1", "DEF101", "2020", "2", "M", "What is life?", "None", "Null", "None", "All the above", choice.D, 5, 4);
		MultipleQuestion multiq2 = new MultipleQuestion("2", "DEF101", "2020", "2", "M", "Why?", "None", "Null", "None", "All the above", choice.D, 5, 4);
		MultipleQuestion multiq3 = new MultipleQuestion("3", "DEF101", "2020", "2", "M", "What?", "None", "Null", "None", "All the above", choice.D, 5, 4);
		MultipleQuestion multiq4 = new MultipleQuestion("4", "DEF101", "2020", "2", "M", "How", "None", "Null", "None", "All the above", choice.D, 5, 4);
//		MultipleQuestion(String id, String subjectCode, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int answerNumber
		
		
		multipleQuestions.add(multiq1);
		multipleQuestions.add(multiq2);
		multipleQuestions.add(multiq3);
		multipleQuestions.add(multiq4);
		
	    return multipleQuestions;
	    
	}
	
	
	public MultipleQuestion getMultipleQuestion(String Id,String subjectCode, String semester, String year, String examType) {
	    
		
		MultipleQuestion multiq1 = new MultipleQuestion(Id, subjectCode, year, semester, examType, "What is life?", "None", "Null", "None", "All the above", choice.D, 5, 4);
		
	    return multiq1;
	    
	}
	
	public List<ShortQuestion> getAllShortQuestions() {
	    
	    return shortQuestions;
	    
	}
	
	
	public void updateUow(Question q) {
		QuestionUow.registerDirty(q);
	}
	
	public void commitUow() {
		QuestionUow.commitAll();
	}


}
