package domain;

import java.sql.*;
import java.util.*;

import datasource.DBConnection;

public class ShortQuestion extends Question {
	
	 	private String subjectCode;
	    private String year;
	    private String semester;
	    private String examType;
	
	public ShortQuestion(String id, String subjectCode, String year, String semester, String examType, String questionText, int possibleMark) {
    	this.id = id;
    	this.subjectCode = subjectCode;
    	this.year = subjectCode;
    	this.semester = subjectCode;
    	this.examType = subjectCode;
    	this.questionText = questionText;
    	this.possibleMark= possibleMark;
    	
    }
    
    public ShortQuestion(String id, String examId, String questionText, int possibleMark) {
    	this.id = id;
    	this.examId = examId;
    	this.questionText = questionText;
    	this.possibleMark= possibleMark;
    	
    }
    
    public String getId() {
    	return id;
    }
    
    public String getQuestionText() {
    	return questionText;
    }
	
    public int getPossibleMark() {
    	
    	return possibleMark;
    	
    }
	
}
