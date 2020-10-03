package domain;

import java.sql.*;
import java.util.*;

import datasource.DBConnection;

public class MultipleQuestion extends Question {
	
	
    private String ansA;  
    private String ansB;
    private String ansC;
    private String ansD;
    private choice correctAnswer;
    private String examId;
    private int answerNumber;
    
    public MultipleQuestion(String id, String examId, String questionText, String ansA, String ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int answerNumber){
    	
    	this.id = id;
    	
    	this.examId = examId;
    	
    	this.questionText = questionText;
    	this.ansA = ansA;
    	this.ansB = ansB;
    	this.ansC = ansC;
    	this.ansD = ansD;
    	
    	this.correctAnswer = correctAnswer;
    	
    	this.possibleMark = possibleMark;
    	
    	
    	
    	this.answerNumber = answerNumber;
    	
    } 
    
    public List<String> getAllAnswer() {
    	
    	List<String> answers = new ArrayList<>();
    	
    	for (int index = 1; index<=answerNumber; index++) {
    		
    		answers.add(getAnswer(choice.values()[index-1]));
    		
    	}
    	
    	return answers;
    	
    }

	public String getAnswer(choice index) {
		
		switch (index) {
		
			case A:
		
			return ansA;

			case B:
		
			return ansB;
			
			case C:
		
			return ansC;
			
			case D:
		
			return ansD;
			
		}
		
		return null;
		
	}
	
	public String getCorrectAnswer() {
		return correctAnswer.toString();
	}

	public String getAnsA() {
		return ansA;
	}

	public void setAnsA(String ansA) {
		this.ansA = ansA;
	}

	public String getAnsB() {
		return ansB;
	}

	public void setAnsB(String ansB) {
		this.ansB = ansB;
	}

	public String getAnsC() {
		return ansC;
	}

	public void setAnsC(String ansC) {
		this.ansC = ansC;
	}

	public String getAnsD() {
		return ansD;
	}

	public void setAnsD(String ansD) {
		this.ansD = ansD;
	}

	public String getExamId() {
		return examId;
	}

	public void setExamId(String examId) {
		this.examId = examId;
	}

	public int getAnswerNumber() {
		return answerNumber;
	}

	public void setAnswerNumber(int answerNumber) {
		this.answerNumber = answerNumber;
	}

	public void setCorrectAnswer(choice correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	
	
}