package domain;

import java.sql.*;
import java.util.*;

import datasource.DBConnection;

public class ShortQuestion extends Question {
    
    public ShortQuestion(String id, String examId, String questionText, int possibleMark) {
    	this.id = id;
    	this.examId = examId;
    	this.questionText = questionText;
    	this.possibleMark= possibleMark;
    	
    }
	
}
