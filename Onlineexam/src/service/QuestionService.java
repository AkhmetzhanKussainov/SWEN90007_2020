package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.DBConnection;
import datasource.QuestionDataMapper;
import domain.MultipleQuestion;
import domain.ShortQuestion;
import domain.Question;
import uow.QuestionUow;

public class QuestionService {
	
	public QuestionService() {
		
		QuestionDataMapper qdm = new QuestionDataMapper();
		
		multipleQuestions = qdm.loadMultipleChoiceQuestions();
		shortQuestions = qdm.loadShortQuestions();
		
	}
	
	private List<MultipleQuestion> multipleQuestions;
	private List<ShortQuestion> shortQuestions;
	
	public List<MultipleQuestion> getAllMultipleQuestions() {
	    
	    return multipleQuestions;
	    
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
