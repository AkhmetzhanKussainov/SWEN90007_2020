package service;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Exceptions.DatabaseException;
import Exceptions.FormException;
import datasource.DBConnection;
import datasource.ExamDataMapper;
import domain.Exam;
import domain.MultipleQuestion;
import domain.Scriptbook;
import domain.ShortQuestion;

public class ExamService {
	
	private ExamDataMapper examDataMapper;
	
	//Checks the database to see if the exam has been closed
	public boolean checkClosed(String subjectCode, String year, String semester, String examType) {
		
		Exam exam = getExam(subjectCode, year, semester, examType);
		
		boolean closed = false;
		
		if (exam.getClosed().equals("Y")) {
			
			closed = true;
			
		} else {
			closed = false;
		}
		
		return closed;
	}
	
	
	
	
	public ExamService()
	{
		examDataMapper=new ExamDataMapper();
		 
		 
	}
	
	
	//Deletes an exam in the database
		public void deleteExam(String subjectId, String year, String semester, String examType) throws DatabaseException  {
			
			try {examDataMapper.deleteExam(subjectId, year, semester, examType);}
			
			catch (Exception e) {
				
				throw new DatabaseException("Unable to delete subject: "+e.getMessage(), e);
				
			}
			
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
    			
    			List<Exam> temp = examDataMapper.loadExams();
    			for(int i=0;i<temp.size();i++)
    			{
    				System.out.println(temp.get(i).getExamName());
    			}
    			//exams = examDataMapper.load
    			for(int i=0;i<temp.size();i++)
    			{
    				if(temp.get(i).getSubjectID().equals(subjectCode))
    				{
    					exams.add(temp.get(i));
    				}
    			}
				/*Exam exam = new Exam("DEF101", "2020", "2", "F", "History of World", "T12", 80);
				Exam exam1 = new Exam("S101", "2021", "1", "F", "Subject of World", "T12", 80, "N", "N", date, date);
				Exam exam2 = new Exam("J101", "2020", "1", "M", "World", "T12", 100, "N", "N", date, date);
				Exam exam3 = new Exam("K101", "2020", "2", "M", "Keratin", "T12", 100, "N", "N", date, date);
				
				exams.add(exam);
				exams.add(exam1);
				exams.add(exam2);
				exams.add(exam3);*/
				System.out.println(exams.size());
				System.out.println(date.toString());
			
					
					
		} catch (Exception e) {
			System.out.println(e);
	
		}
    	
    	
    	return exams;
    }
	
	public Exam getExam(String subjectCode, String year, String semester, String examType) {
    	
		java.util.Calendar cal = Calendar.getInstance();
		Timestamp date = new Timestamp(cal.getTime().getTime());
		Exam exam = examDataMapper.loadExam(subjectCode, year, semester, examType);
		
		//Exam exam = new Exam("H101", "2020", "2", "F", "History of World", "T12", 80, "N", "N", date, date);
    
    	try {
	    					
//				Exam exam = new Exam("H101", "2020", "2", "F", "History of World", "T12", 80, "N", "N");
					
					
		} catch (Exception e) {
			System.out.println(e);
	
		}
    	
    	
    	return exam;
    }
	
	public Boolean createExam(Exam exam) {
		try {
			examDataMapper.addExam(exam);
			// try creating exam from data mappper
			return true;
		} catch (Exception e) {
			//
			System.out.println(e);
			return false;
	
		}
	}
	
	public Boolean updateExam(Exam exam) {
		try {
			examDataMapper.changeExam(exam);
			// try update exam from data mappper
			return true;
		} catch (Exception e) {
			//
			System.out.println(e);
			return false;
	
		}
	}
	
	
	
	// on creation - log Exam object
	// create dummy Exam objects
	// update dummy Exam objects
	// delete dummy Exam objects
	

	
	

}

