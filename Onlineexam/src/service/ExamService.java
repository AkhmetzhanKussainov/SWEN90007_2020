package service;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.DBConnection;
import datasource.ExamDataMapper;
import domain.Exam;
import domain.MultipleQuestion;
import domain.Scriptbook;
import domain.ShortQuestion;

public class ExamService {
	
	public ExamService()
	{
		 ExamDataMapper examDataMapper=new ExamDataMapper();
		 
		 
	}
	
	public List<Exam> getExams(String subjectCode) {
    	
    	List<Exam> exams = new ArrayList<>();
    
    	try {
	    	
				
//				String examId = rs.getString(1);
//				String subjectId = "H101";
//				String examName = "History of the World";
//				String examCreator = "T12";
//				String year = "2020";
//				String examType = "F";
//				String semester = "2";
//				int totalMarks =  0;
//				String published = "N";
//				String closed = "N";
//				
    			java.util.Calendar cal = Calendar.getInstance();
    			Timestamp date = new Timestamp(cal.getTime().getTime());
				Exam exam = new Exam("DEF101", "2020", "2", "F", "History of World", "T12", 80, "N", "N", date, date);
				Exam exam1 = new Exam("S101", "2021", "1", "F", "Subject of World", "T12", 80, "N", "N", date, date);
				Exam exam2 = new Exam("J101", "2020", "1", "M", "World", "T12", 100, "N", "N", date, date);
				Exam exam3 = new Exam("K101", "2020", "2", "M", "Keratin", "T12", 100, "N", "N", date, date);
				
				exams.add(exam);
				exams.add(exam1);
				exams.add(exam2);
				exams.add(exam3);
				
				System.out.println(date.toString());
			
					
					
		} catch (Exception e) {
			System.out.println(e);
	
		}
    	
    	
    	return exams;
    }
	
	public Exam getExam(String subjectCode, String year, String semester, String examType) {
    	
		java.util.Calendar cal = Calendar.getInstance();
		Timestamp date = new Timestamp(cal.getTime().getTime());
		Exam exam = new Exam("H101", "2020", "2", "F", "History of World", "T12", 80, "N", "N", date, date);
    
    	try {
	    					
//				Exam exam = new Exam("H101", "2020", "2", "F", "History of World", "T12", 80, "N", "N");
					
					
		} catch (Exception e) {
			System.out.println(e);
	
		}
    	
    	
    	return exam;
    }
	
	
	
	// on creation - log Exam object
	// create dummy Exam objects
	// update dummy Exam objects
	// delete dummy Exam objects
	

	
	

}

