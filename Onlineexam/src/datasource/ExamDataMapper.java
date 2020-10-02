package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.MultipleQuestion;
import domain.Question.choice;
import domain.Question;
import domain.Scriptbook;
import domain.ShortQuestion;
import domain.Exam;

public class ExamDataMapper {
	
	private static final String findAllExams =
	
			"SELECT * from exams";
	
	private static final String findAllMultipleStatement =
			
    		"SELECT * from multipleQuestion";
	
    private static final String changeMultiMark =
			"UPDATE multipleQuestion SET marks = ? where multiq_id = ? ";
	
    private static final String findAllShortStatement =
			"SELECT * from shortQuestion";
    		
    private static final String findAllMultipleByExamStatement =
    "select * from multiplequestion where subjectid=";
    
    private static final String findAllShortByExamStatement =
    	    "select * from shortquestion where subjectid=";
    
    private static final String findAllScriptbooksByExamStatement =
    	    "select * from scriptbooks where subjectid=";
    		
    private static final String changeShortMark =
			"UPDATE shortQuestion SET marks = ? where shortq_id = ? ";
    
    private static final String examTimeCheck = "";
    
    private choice toChoice(String correctAnswer) {
    	
	    	switch (correctAnswer) {
			
			case "A":
		
			return choice.A;
	
			case "B":
		
			return choice.B;
			
			case "C":
		
			return choice.C;
			
			case "D":
		
			return choice.D;
			
		}
		
		return null;
    	
    	
    }
    
    //insertApostrophe for SQL Query
    public String insertA(String string) {
    	
    	return "'"+string+"'";
    	
    }
    
    public List<MultipleQuestion> loadMultipleQuestionsForExam(String subjectId, String examtype, String year, String semester) {
    	    	
    	String sqlQuery = findAllMultipleByExamStatement + insertA(subjectId) + "and examtype=" + insertA(examtype) + " and year="+insertA(year) + " and semester="+insertA(semester)+";";

    	return (loadMultipleChoiceQuestions(sqlQuery));
    	
    }
    
    public List<ShortQuestion> loadShortQuestionsForExam(String subjectId, String examtype, String year, String semester) {
    	
    	String sqlQuery = findAllShortByExamStatement + insertA(subjectId) + "and examtype=" + insertA(examtype) + " and year="+insertA(year) + " and semester="+insertA(semester)+";";

    	return (loadShortQuestions(sqlQuery));
    	
    }
    
    public List<Scriptbook> loadScriptbooksForExam(String subjectId, String examtype, String year, String semester) {
    	
    	String sqlQuery = findAllScriptbooksByExamStatement + insertA(subjectId) + "and examtype=" + insertA(examtype) + " and year="+insertA(year) + " and semester="+insertA(semester)+";";

    	return (loadScriptbooks(sqlQuery));
    	
    }
    
    
    
    public List<Exam> loadExams() {
    	
    	List<Exam> exams = new ArrayList<>();
    
    	try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(findAllExams);
	    	
	    	ResultSet rs = stmt.executeQuery();
	    	
			while (rs.next()) {
				
				String subjectId = rs.getString(1);
				String year = rs.getString(2);
				String semester = rs.getString(3);
				String examType = rs.getString(4);
				String examName = rs.getString(5);
				String examCreator = rs.getString(6);
				int totalMarks =  Integer.parseInt(rs.getString(7));
				
				Exam exam = new Exam(subjectId, year, semester, examType, examName, examCreator, totalMarks);
				
				for (MultipleQuestion multipleQuestion : loadMultipleQuestionsForExam(subjectId, examType, year, semester)) {
					
					exam.addQuestion(multipleQuestion);
					
				}
				
				for (ShortQuestion shortQuestion : loadShortQuestionsForExam(subjectId, examType, year, semester)) {
					
					exam.addQuestion(shortQuestion);
					
				}
				
				for (Scriptbook scriptbook : loadScriptbooksForExam(subjectId, examType, year, semester)) {
				
					exam.addScriptbook(scriptbook);
					
				}	
				
				exams.add(exam);
			
				
				
			}
					
					
		} catch (SQLException e) {
	
		}
    	
    	
    	return exams;
    }
    
    
	public List<MultipleQuestion> loadMultipleChoiceQuestions(String sqlQuery) {
	
		List<MultipleQuestion> multipleQuestions = new ArrayList<>();
	    
		try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(sqlQuery);
	    	
	    	ResultSet rs = stmt.executeQuery();
	    	
			while (rs.next()) {
				
				String id = rs.getString(1);
				String examId = rs.getString(2);
				String questionText = rs.getString(3);
				String ansA = rs.getString(4);
				String ansB = rs.getString(5);
				String ansC = rs.getString(6);
				String ansD = rs.getString(7);
				choice correctAnswer = toChoice(rs.getString(8));
				int possibleMark = Integer.parseInt( rs.getString(9) );
				int answerNumber = Integer.parseInt(rs.getString(10) );
				
				multipleQuestions.add(new MultipleQuestion(id, examId, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMark, answerNumber));
			
			}
					
					
		} catch (SQLException e) {
	
		}
		
		return multipleQuestions;
	
	}
	
	private boolean isTrue(String string) {
		
		if (string=="true") return true;
		if (string=="TRUE") return true;
		if (string=="1") return true;
		return false;
		
	}
	
	public List<Scriptbook> loadScriptbooks(String sqlQuery) {
		
		List<Scriptbook> scriptbooks = new ArrayList<>();
	    
		try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(sqlQuery);
	    	
	    	ResultSet rs = stmt.executeQuery();
	    	
			while (rs.next()) {
				
				String subjectId = rs.getString(1);
				String year = rs.getString(2);
				String semester = rs.getString(3);
				String examType = rs.getString(4);
				
				String studentId = rs.getString(5);
				int scriptMark = Integer.parseInt( rs.getString(9) );
				boolean marked = isTrue(rs.getString(10));
				
				scriptbooks.add(new Scriptbook(subjectId, year, semester, examType, studentId, scriptMark, marked));
			
			}
					
					
		} catch (SQLException e) {
	
		}
		
		return scriptbooks;
	
	}
	
	public List<ShortQuestion> loadShortQuestions(String sqlQuery) {
	    List<ShortQuestion> shortQuestions = new ArrayList<>();
	    try 
	    {
	    	PreparedStatement stmt = DBConnection.prepare(sqlQuery);
	    	
	    	ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				String id = rs.getString(1);
				String examId = rs.getString(2);
				String questionText = rs.getString(3);
				int markPossible = Integer.parseInt(rs.getString(4));
				
				shortQuestions.add(new ShortQuestion(id, examId, questionText, markPossible));
			}
	
		} catch (SQLException e) {
	
		}
	    return shortQuestions;
	}
	
	
	
	public void updateMarks(Question q) {
		
		MultipleQuestion mq = new MultipleQuestion(null, null, null, null, null, null, null, null, 0, 0);
		ShortQuestion sq = new ShortQuestion(null, null, null, 0);
		
		try {
			if (q.getClass().equals(mq.getClass())) {
			PreparedStatement updateStatement = DBConnection.prepare(changeMultiMark);
			//need to convert id to int since the database type is int.
			int id = Integer.parseInt( q.getId() );
			updateStatement.setInt(1, q.getPossibleMark());
			updateStatement.setInt(2, id);
			
			System.out.println(updateStatement);
			updateStatement.execute();
			}else if (q.getClass().equals(sq.getClass())) {
				PreparedStatement updateStatement = DBConnection.prepare(changeShortMark);
				//need to convert id to int since the database type is int.
				int id = Integer.parseInt( q.getId() );
				updateStatement.setInt(1, q.getPossibleMark());
				updateStatement.setInt(2, id);
				
				System.out.println(updateStatement);
				updateStatement.execute();
			}
			
		} catch (SQLException e) {
		}
	
	}
	
	public boolean checkSubmitTime() {
		try {
		
			//Run SQL to get exam start time and end time
			
			
			
			
			//Set up time stamp format
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
			
			
			//put exam start and end time in two Date variable
			Date start = sdf.parse("2013-10-23 08:10:10:000");
			Date end = sdf.parse("2020-10-23 08:10:10:000");

			//Get curent time
			Date now = new Date();
			
			//Convert String time into long
			
			long now_time = now.getTime();
			long start_time = start.getTime();
			long end_time = end.getTime();
			
			
			if(now_time >= start_time && now_time <= end_time) {
				
				
				System.out.println(now_time);
				return true;
				
				
			}
			
			
			
		}catch(Exception e) {
			
		}
		
		return false;
		
	}
	
	
}
