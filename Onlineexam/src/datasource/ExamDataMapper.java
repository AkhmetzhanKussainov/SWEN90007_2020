package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.MultipleQuestion;
import domain.Question.choice;
import domain.Question;
import domain.Scriptbook;
import domain.ShortAttempt;
import domain.ShortQuestion;
import domain.Adjudicator;
import domain.Attempt;
import domain.Exam;
import domain.ExamTimeRange;
import domain.MultipleAttempt;

public class ExamDataMapper {
	
	private static final String findAllExams =
	
			"SELECT * from exams";
	
	private static final String findAllMultipleStatement =
			
    		"SELECT * from multipleQuestion";
	
    private static final String changeMultiMark =
			"UPDATE multipleQuestion SET marks = ? where subjectId=?,year=?,semester=?,examType=?, questionId=?, studentNumber = ? ";
	
    private static final String findAllShortStatement =
			"SELECT * from shortQuestion";
    		
    private static final String findAllMultipleByExamStatement =
    "select * from multiplequestion where subjectId=?,year=?,semester=?,examType=?";
    
    private static final String findAllShortByExamStatement =
    	    "select * from shortquestion where subjectId=?,year=?,semester=?,examType=?";
    
    private static final String findAllScriptbooksByExamStatement =
    	    "select * from scriptbooks where subjectId=?,year=?,semester=?,examType=?";
    		
    private static final String changeShortMark =
			"UPDATE shortQuestion SET marks = ? where subjectId=?,year=?,semester=?,examType=?, questionId=?, studentNumber = ? ";
    
    private static final String changeExam =
    		"UPDATE exams SET examName = ? , examCreator = ?, totalMarks=?, startTime =?, endTime=? where subjectId = ?, year=?, semester=? , examType=?, published=?, closed=?";
    
    private static final String createExam = 
    		"INSERT into exams (subjectId, year, semester, examType, examName, examCreator, totalMarks, published, closed, startTime, endTime)"
    		+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String deleteExam = 
    		"DELETE from exams where subjectId=? , year = ? , semester = ? , examType = ?";
    
    private static final String findExam =
    		"SELECT * from exams where examId=?";
    
    private static final String closeExam =
    		"UPDATE exams SET closed = 'Y' where subjectId=?, year=?, semester = ?, examType = ?";
    
    private static final String addScriptbook =
    		"INSERT into scriptbooks (subjectId,year,semester,examType,studentNumber, scriptTotalMarks, marked)"
    		+ "VALUES (?,?,?,?,?,?,?)";
    
    private static final String deleteMultipleStatement =
    		"DELETE from multipleQuestion where subjectId=? , year = ? , semester = ? , examType = ?";
    
    private static final String deleteShortStatement = 
    		"DELETE from shortQuestion where subjectId=? , year = ? , semester = ? , examType = ?";
    
    private static final String addShortStatement =
    		"INSERT into shortQuestion (questionId, subjectId, year, semester, examType, questionText, possibleMarks)"
    		+ "VALUES (?,?,?,?,?,?,?)";
    
    private static final String addMultipleStatement =
    		"INSERT into shortQuestion (questionId, subjectId, year, semester, examType, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)"
    	    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String findScriptBookByExamStudent =
    		"SELECT * from scripbooks where subjectId=?,year=?,semester=?,examType=?, studentNumber=?";
    
    private static final String getAttemptsShort =
    		"SELECT * from shortAttempt where subjectId=?,year=?,semester=?,examType=?, studentNumber=?";
    private static final String getAttemptsMultiple =
    		"SELECT * from multipleAttempt where subjectId=?,year=?,semester=?,examType=?, studentNumber=?";
    
    
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
				
//				String examId = rs.getString(1);
				String subjectId = rs.getString(1);
				String examName = rs.getString(5);
				String examCreator = rs.getString(6);
				String year = rs.getString(2);
				String examType =rs.getString(4);
				String semester = rs.getString(3);
				int totalMarks =  Integer.parseInt(rs.getString(9));
				String published = rs.getString(7);
				String closed = rs.getString(8);
				
				Exam exam = new Exam(subjectId, year, semester, examType, examName, examCreator, totalMarks, published, closed);
				
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
				
				boolean submitted = rs.getBoolean(5);
				
				String studentId = rs.getString(5);
				int scriptMark = Integer.parseInt( rs.getString(6) );
				boolean marked = isTrue(rs.getString(7));
				
				Scriptbook temp = new Scriptbook(subjectId, year, semester, examType, studentId, scriptMark, marked);
				temp.setSubmitted(submitted);
				
				scriptbooks.add(temp);
			
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
	
	public void deleteShortQuestions(Exam exam)
	{
		PreparedStatement statement;
		try {
			statement = DBConnection.prepare(deleteShortStatement);
			statement.setString(1, exam.getSubjectID());
			statement.setString(2, exam.getYear());
			statement.setString(3, exam.getSemester());
			statement.setString(4, exam.getExamType());
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void deleteMultipleQuestions(Exam exam)
	{
		PreparedStatement statement;
		try {
			statement = DBConnection.prepare(deleteMultipleStatement);
			statement.setString(1, exam.getSubjectID());
			statement.setString(2, exam.getYear());
			statement.setString(3, exam.getSemester());
			statement.setString(4, exam.getExamType());
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void addMultipleQuestions(Exam exam, MultipleQuestion q)
	{

		try {
			PreparedStatement statement = DBConnection.prepare(addMultipleStatement);
			statement.setInt(1, Integer.parseInt(q.getId()));
			statement.setString(2, exam.getSubjectID());
			statement.setString(3, exam.getYear());
			statement.setString(4, exam.getSemester());
			statement.setString(5, exam.getExamType());
			statement.setString(6, q.getQuestionText());
			statement.setString(7, q.getAnsA());
			statement.setString(8, q.getAnsB());
			statement.setString(9, q.getAnsC());
			statement.setString(10, q.getAnsD());
			statement.setNString(11, q.getCorrectAnswer());
			statement.setInt(12, q.getPossibleMark());
			statement.setInt(13, q.getAnswerNumber());
			
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void addShortQuestions(Exam exam, ShortQuestion q)
	{

		try {
			PreparedStatement statement = DBConnection.prepare(addShortStatement);
			statement.setInt(1, Integer.parseInt(q.getId()));
			statement.setString(2, exam.getSubjectID());
			statement.setString(3, exam.getYear());
			statement.setString(4, exam.getSemester());
			statement.setString(5, exam.getExamType());
			statement.setString(6, q.getQuestionText());
			statement.setInt(7, q.getPossibleMark());

			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	
	//function to change the exam related information (NOT FOR UPDATING QUESTIONS)
	public String changeExam(Exam updatedExam)
	{
		try
		{
			if(updatedExam.canEdit())
			{
				PreparedStatement statement = DBConnection.prepare(changeExam);
				String subjectID = updatedExam.getSubjectID();
				String examName = updatedExam.getExamName();
				String examCreator = updatedExam.getexamCreator();
				String year = updatedExam.getYear();
				String semester = updatedExam.getSemester();
				String examType = updatedExam.getExamType();
				int totalMarks = updatedExam.getTotalMarks();
				Date startTime = updatedExam.getStartDate();
				Date endTime = updatedExam.getEndDate();
				String published = updatedExam.getPublished();
				String closed = updatedExam.getClosed();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//				String startTimeString = dateFormat.format(startTime);
//				String endTimeString = dateFormat.format(endTime);  
				
				statement.setString(1, examName);
				statement.setString(2, examCreator);
				statement.setInt(3, totalMarks);
				statement.setString(4,null);
				statement.setNString(5, null);
				statement.setString(6, subjectID);
				statement.setString(7, year);
				statement.setString(8, semester);
				statement.setString(9, examType);
				statement.setString(10, published);
				statement.setString(11, closed);
				
				System.out.println(statement);
				
				if(statement.execute())
					{
						System.out.println("Successfully updated!");
					}else
					{
						System.out.println("Error while updating!");
					};
			}else
			{
				System.out.println("Cannot edit, One of the students has already taken the exam");
			}
		
		}catch(SQLException e)
		{
			e.printStackTrace();
			return "Failure";
		}
		return "Success";
	}
	
	public String publishExam(Exam newExam)
	{
		try
		{
			PreparedStatement statement = DBConnection.prepare(createExam);
			String subjectId = newExam.getSubjectID();
			String year = newExam.getYear();
			String semester = newExam.getSemester();
			String examType = newExam.getExamType();
			String examName = newExam.getExamName();
			String examCreator = newExam.getexamCreator();
			int totalMarks = newExam.getTotalMarks();
			Date startTime = newExam.getStartDate();
			Date endTime = newExam.getEndDate();
			String published = newExam.getPublished();
			String closed = newExam.getClosed();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
//			String startTimeString = dateFormat.format(startTime);
//			String endTimeString = dateFormat.format(endTime);  
			
			statement.setString(1, subjectId);
			statement.setString(2, year);
			statement.setString(3, semester);
			statement.setString(4, examType);
			statement.setString(5, examName);
			statement.setString(6, examCreator);
			statement.setString(8, published);
			statement.setString(9, closed);
			statement.setInt(7, totalMarks);
			statement.setString(10, null);
			statement.setString(11, null);
			System.out.println(statement);
			
			statement.execute();
			
//			if(statement.execute())
//			{
//				System.out.println("Successfully executed");
//			}else
//			{
//				System.out.println("Error while creating the exam");
//			}
		}catch(SQLException e)
		{
			System.out.println(e);
			e.printStackTrace();
			return "Failure";
		}
		return "Success";
	}
	
	
	public void deleteExam(Exam examToDelete)
	{
		try
		{
			PreparedStatement statement = DBConnection.prepare(deleteExam);
			statement.setString(1, examToDelete.getSubjectID());
			statement.setString(2, examToDelete.getYear());
			statement.setString(3, examToDelete.getSemester());
			statement.setString(4,examToDelete.getExamType());
			
			System.out.println(statement);
			
			if(statement.execute())
			{
				System.out.println("Successfully deleted exam");
				
			}else
			{
				System.out.println("Failed to delete the exam");
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void closeExam(Exam closedExam)
	{
		try
		{
			PreparedStatement statement = DBConnection.prepare(closeExam);
			
			statement.setString(1, closedExam.getSubjectID());
			statement.setString(2, closedExam.getYear());
			statement.setString(3, closedExam.getSemester());
			statement.setString(4,closedExam.getExamType());
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void addScriptbook(Scriptbook scriptbook)
	{
		try
		{
			PreparedStatement statement = DBConnection.prepare(addScriptbook);
			
			statement.setString(1, scriptbook.getSubjectId());
			statement.setNString(2, scriptbook.getYear());
			statement.setString(3, scriptbook.getSemester());
			statement.setString(4, scriptbook.getExamType());
			statement.setString(5, scriptbook.getStudentNumber());
			statement.setInt(6,scriptbook.getTotalMark());
			statement.setBoolean(7, scriptbook.isMarked());
			
			statement.execute();
			
			System.out.println(statement);
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateQuestions(Exam exam,List<MultipleQuestion> multiplequestions, List<ShortQuestion> shortQuestions)
	{
		this.deleteMultipleQuestions(exam);
		this.deleteShortQuestions(exam);
		int id = 0;
		for(MultipleQuestion q: multiplequestions)
		{
			q.setId(Integer.toString(id));
			this.addMultipleQuestions(exam, q);
			id++;
		}
		for(ShortQuestion q: shortQuestions)
		{
			q.setId(Integer.toString(id));
			this.addShortQuestions(exam, q);
			id++;
		}
	}
	
	/*public Scriptbook findScriptByExamStudent(Exam exam, String studentID)
	{
		try {
			PreparedStatement statement = DBConnection.prepare(findScriptBookByExamStudent);
			statement.setString(1, exam.getSubjectID());
			statement.setNString(2, exam.getYear());
			statement.setNString(3, exam.getSemester());
			statement.setNString(4, exam.getExamType());
			statement.setNString(5, studentID);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				String subjectId = rs.getString(1);
				String year = rs.getString(2);
				String semester = rs.getNString(3);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}*/
	
	
	
	public List<Attempt> getAllAttempts(Exam exam, String studentID)
	{
		List<Attempt> attempts = new ArrayList<>();
		try
		{
			PreparedStatement statement = DBConnection.prepare(getAttemptsShort);
			statement.setString(1, exam.getSubjectID());
			statement.setNString(2, exam.getYear());
			statement.setNString(3, exam.getSemester());
			statement.setNString(4, exam.getExamType());
			statement.setNString(5, studentID);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				int questionId = rs.getInt(1);
				String subjectId = rs.getNString(2);
				String year =rs.getNString(3);
				String semester = rs.getNString(4);
				String examType = rs.getNString(5);
				String studentNumber = rs.getNString(6);
				String attemptAns = rs.getNString(7);
				int mark = rs.getInt(8);
				boolean marked = rs.getBoolean(9);
				Attempt temp = new Attempt(Integer.toString(questionId),subjectId,year,semester,examType,studentNumber,attemptAns);
				attempts.add(temp);
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			PreparedStatement statement = DBConnection.prepare(getAttemptsMultiple);
			statement.setString(1, exam.getSubjectID());
			statement.setNString(2, exam.getYear());
			statement.setNString(3, exam.getSemester());
			statement.setNString(4, exam.getExamType());
			statement.setNString(5, studentID);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				int questionId = rs.getInt(1);
				String subjectId = rs.getNString(2);
				String year =rs.getNString(3);
				String semester = rs.getNString(4);
				String examType = rs.getNString(5);
				String studentNumber = rs.getNString(6);
				String attemptAns = rs.getNString(7);
				int mark = rs.getInt(8);
				boolean marked = rs.getBoolean(9);
				Attempt temp = new Attempt(Integer.toString(questionId),subjectId,year,semester,examType,studentNumber,attemptAns);
				attempts.add(temp);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return attempts;
	}
	
	//TODO submissionTimeValid
/*	public Boolean checkSubmissionTimeValid(Exam exam)
	{
		ExamTimeRange etr = new ExamTimeRange();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String startTimeString = dateFormat.format(exam.getStartDate());
		String endTimeString = dateFormat.format(exam.getEndDate());  
		etr.setStart_time(startTimeString);
		etr.setEnd_time(endTimeString);
		Adjudicator adjudicator = new Adjudicator();
		if(adjudicator.checkExamTime(etr))
		{
			return true;
		}else
		{
			return false;
		}
	}*/
	
	public void studentTakesExam()
	{
		
	}
	
	
	
	
	//TODO studentTakesExam
	//TODO studentgetAllSubjets
	//TODO studentsubmitExam

	
	//TODO update SQL statements from previous to fit the current DB
	
}
