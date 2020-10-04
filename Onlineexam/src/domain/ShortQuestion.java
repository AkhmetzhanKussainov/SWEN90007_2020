package domain;

import java.sql.*;
import java.util.*;

import datasource.DBConnection;

public class ShortQuestion extends Question {
    
	
    public ShortQuestion(String id, String subjectId, String year, String semester, String examType, String questionText, int possibleMark) {
    	this.id = id;
    	this.subjectId = subjectId;
    	this.year = year;
    	this.semester = semester;
    	this.examType = examType;
    	this.questionText = questionText;
    	this.possibleMark= possibleMark;
    	
    }
	
}
