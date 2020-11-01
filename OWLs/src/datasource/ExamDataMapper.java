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
	
	private static final String updateScriptbookTotalMark =
    		"UPDATE scriptbooks SET scripttotalmarks = ? where subjectId=? AND year=? and semester = ? and examType = ? and studentNumber=?";	
	
	private static final String findAllExams =
	
			"SELECT * from exams";
	
	private static final String findAllMultipleStatement =
			
    		"SELECT * from multipleQuestion";
	
    private static final String changeMultiMark =
			"UPDATE multipleAttempt SET mark = ? where subjectId=? AND year=? and semester = ? and examType = ? and questionId=? and studentNumber = ? ";
	
  
    private static final String getAttemptsShort =
    		"SELECT * from shortAttempt where subjectId=? AND year=? and semester = ? and examType = ? AND studentNumber=?";
    
    private static final String getAttemptsMultiple =
    		"SELECT * from multipleAttempt where subjectId=? AND year=? and semester = ? and examType = ? AND studentNumber=?";
    
    
    private static final String changeShortMark =
			"UPDATE shortAttempt SET mark = ? where subjectId=? AND year=? and semester = ? and examType = ? and questionId=? and studentNumber = ? ";
    
    //New code
    private static final String changeShortVersion =
			"UPDATE shortAttempt SET version = ? where subjectId=? AND year=? and semester = ? and examType = ? and questionId=? and studentNumber = ? ";
    
    
    
    
    //New code
    private static final String submitShortAttempt =
    		"INSERT into shortAttempt (questionId, subjectId, year, semester, examType, studentNumber, attemptAns, mark, marked, version)"
    		+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
  //New code
    private static final String submitMultipleAttempt =
    		"INSERT into multipleAttempt (questionId, subjectId, year, semester, examType, studentNumber, attemptAns, mark, marked, version)"
    		+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
    
    //New code
    private static final String changeMultiVersion =
			"UPDATE multipleAttempt SET version = ? where subjectId=? AND year=? and semester = ? and examType = ? and questionId=? and studentNumber = ? ";
	
    
    //NEW get version
    private static final String getVersionMultiple =
    		"select version from multipleattempt where subjectId=? AND year=? and semester = ? and examType = ? and studentnumber = ? and questionid = ?";
    
    private static final String getVersionShort =
    	    "select version from shortattempt where subjectId=? AND year=? and semester = ? and examType = ? and studentnumber = ? and questionid = ?";
    	    
    
    
    private static final String findAllShortStatement =
			"SELECT * from shortQuestion";
    		
    private static final String findAllMultipleByExamStatement =
    "select * from multiplequestion where subjectId=? AND year=? and semester = ? and examType = ?";
    
    private static final String findAllShortByExamStatement =
    	    "select * from shortquestion where subjectId=? AND year=? and semester = ? and examType = ?";
    
    private static final String findAllScriptbooksByExamStatement =
    	    "select * from scriptbooks where subjectId=? AND year=? and semester = ? and examType = ?";
    		
    
    private static final String changeExam =
    		"UPDATE exams SET examName = ? , examCreator = ?, totalMarks=?, startTime =?, endTime=?, published=?, closed=? where subjectId=? AND year=? and semester = ? and examType = ?";
    
    private static final String createExam = 
    		"INSERT into exams (subjectId, year, semester, examType, examName, examCreator, published, closed, totalMarks, startTime, endTime)"
    		+ "VALUES (?,?,?,?,?,?,'F','F',?,?,?)";
    
    private static final String deleteExam = 
    		"DELETE from exams where subjectId=? AND year=? and semester = ? and examType = ?";
    
    /*private static final String findExam =
    		"SELECT * from exams where examId=?";*/
    
    private static final String closeExam =
    		"UPDATE exams SET closed = 'T' where subjectId=? AND year=? and semester = ? and examType = ?";
    
    private static final String addScriptbook =
    		"INSERT into scriptbooks (subjectId,year,semester,examType,submitted, studentNumber, scriptTotalMarks, marked)"
    		+ "VALUES (?,?,?,?,?,?,?,?)";
    
    
    private static final String deleteMultipleStatement =
    		"DELETE from multipleQuestion where subjectId=? AND year=? and semester = ? and examType = ?";
    
    private static final String deleteShortStatement = 
    		"DELETE from shortQuestion where subjectId=? AND year=? and semester = ? and examType = ?";
    
    private static final String deleteMultipleStatementQuestionId =
    		"DELETE from multipleQuestion where questionId = ? AND subjectId=? AND year=? and semester = ? and examType = ?";
    
    private static final String deleteShortStatementQuestionId = 
    		"DELETE from shortQuestion where questionId = ? AND subjectId=? AND year=? and semester = ? and examType = ?";
    
    private static final String addShortStatement =
    		"INSERT into shortQuestion (questionId, subjectId, year, semester, examType, questionText, possibleMarks)"
    		+ "VALUES (?,?,?,?,?,?,?)";
    
    private static final String addMultipleStatement =
    		"INSERT into multipleQuestion (questionId, subjectId, year, semester, examType, questionText, ansA, ansB, ansC, ansD, correctAnswer, possibleMarks, answerNumber)"
    	    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private static final String findScriptBookByExamStudent =
    		"SELECT * from scriptbooks where subjectId=? AND year=? and semester = ? and examType = ? AND studentNumber=?";
    
    private static final String findSpecificScriptBook =
    "SELECT * from scriptbooks where subjectId=? AND year=? and semester = ? and examType = ? AND studentNumber=?";
    
    
    
    private static final String submittedScriptbook =
    		"UPDATE scriptbooks SET submitted = TRUE where subjectId=? AND year=? and semester = ? and examType = ? and studentNumber=?";
    
    
    
    private static final String findMultipleQuestion = 
    		"select * from multiplequestion where questionId = ? and subjectId=? AND year=? and semester = ? and examType = ?";
    
    private static final String findShortQuestion=
    	    "select * from shortquestion where questionId = ? and subjectId=? AND year=? and semester = ? and examType = ?";
    
    private static final String findExam = "SELECT * from exams where subjectId=? and year=? and semester =? and examType=?";
    
   // private static final String updateMultipleStatement = "UPDATE multiplequestion SET where questionId = ? and subjectId=? AND year=? and semester = ? and examType = ?";
    
    // TODO:
    //New code
    /*public void writeUpdateExam(String studentNumber, String subjectId, String year, String semester) {
    	
	    System.out.println("Preparing to write an enrollment to database");	
	    	
		try {
			    	
			    	PreparedStatement stmt = DBConnection.prepare(insertStudentSubject);
			    	
			    	stmt.setString(1, year);
			    	stmt.setString(2, semester);
			    	stmt.setString(3, subjectId);
			    	stmt.setString(4, studentNumber);

			    	System.out.println(stmt);
			
			    	stmt.executeUpdate();
									
				} catch (SQLException e) {
					
					System.out.println("Error writing enrollment");		
					System.out.println(e);
			
			}
	    }*/
    
    
    
    //End new code
    
    
    //New code
    public void changeVersion(int version, String subjectId, String year, String semester, String examType, int questionId, String studentNumber) throws Exception {
    	
	    PreparedStatement statement = DBConnection.prepare(changeMultiVersion);
		statement.setInt(1, version);
		statement.setString(2, subjectId);
		statement.setString(3, year);
		statement.setString(4, semester);
		statement.setString(5, examType);
		statement.setInt(6, questionId);
		statement.setString(7, studentNumber);
		
		System.out.println(statement);
		
		statement.execute();
    
	}
    
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
    
  /*  public List<MultipleQuestion> loadMultipleQuestionsForExam(String subjectId, String examtype, String year, String semester) {
    	    	
    	String sqlQuery = findAllMultipleByExamStatement + insertA(subjectId) + "and examtype=" + insertA(examtype) + " and year="+insertA(year) + " and semester="+insertA(semester)+";";

    	return (loadMultipleChoiceQuestions(sqlQuery));
    	
    }
    */
    
    public List<MultipleQuestion> loadMultipleQuestionsForExam(String subjectId, String examtype, String year, String semester) {
    	
    	List<MultipleQuestion> sa = new ArrayList<>();
		try {
			PreparedStatement statement = DBConnection.prepare(findAllMultipleByExamStatement);
			statement.setString(1, subjectId);
			statement.setString(2, year);
			statement.setString(3, semester);
			statement.setString(4, examtype);	
			System.out.println(statement);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				String questionId = rs.getString(1);
				String subjectIdIn = rs.getString(2);
				String yearIn = rs.getString(3);
				String semesterIn = rs.getString(4);
				String examTypeIn = rs.getString(5);
				String questionText = rs.getString(6);
				String ansA = rs.getString(7);
				String ansB = rs.getString(8);
				String ansC = rs.getString(9);
				String ansD = rs.getString(10);
				String correct = rs.getString(11);
				int possibleMarks = rs.getInt(12);
				int answerNumber = rs.getInt(13);
				//public MultipleQuestion(String id, String subjectId, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC,String ansD, String correctAnswer,int possibleMark, int answerNumber){
				MultipleQuestion temp = new MultipleQuestion(questionId, subjectIdIn,yearIn, semesterIn,examTypeIn,questionText, ansA,ansB,ansC,ansD,correct, possibleMarks,answerNumber);
				sa.add(temp);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return sa;
    	
    }
    
    public MultipleQuestion loadMultipleQuestion(String questionId, String subjectId, String examtype, String year, String semester) {
    	
    	MultipleQuestion sa = null;
		try {
			
			//Convert the question to an int
			
			PreparedStatement statement = DBConnection.prepare(findMultipleQuestion);
			
			statement.setInt(1, Integer.parseInt(questionId));
			statement.setString(2, subjectId);
			statement.setString(3, year);
			statement.setString(4, semester);
			statement.setString(5, examtype);	
			
			System.out.println(statement);

			System.out.println("made it1");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{

				System.out.println("made it2");
				String questionIdIn = rs.getString(1);
				String subjectIdIn = rs.getString(2);
				String yearIn = rs.getString(3);
				String semesterIn = rs.getString(4);
				String examTypeIn = rs.getString(5);
				String questionText = rs.getString(6);
				String ansA = rs.getString(7);
				String ansB = rs.getString(8);
				String ansC = rs.getString(9);
				String ansD = rs.getString(10);
				String correct = rs.getString(11);
				int possibleMarks = rs.getInt(12);
				int answerNumber = rs.getInt(13);
				//public MultipleQuestion(String id, String subjectId, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC,String ansD, String correctAnswer,int possibleMark, int answerNumber){
				sa = new MultipleQuestion(questionIdIn, subjectIdIn,yearIn, semesterIn,examTypeIn,questionText, ansA,ansB,ansC,ansD,correct, possibleMarks,answerNumber);
				//sa.add(temp);
			}
			
			System.out.println("made it3");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return sa;
    	
    }
    
   /* public List<ShortQuestion> loadShortQuestionsForExam(String subjectId, String examtype, String year, String semester) {
    	
    	String sqlQuery = findAllShortByExamStatement + insertA(subjectId) + "and examtype=" + insertA(examtype) + " and year="+insertA(year) + " and semester="+insertA(semester)+";";

    	return (loadShortQuestions(sqlQuery));
    	
    }*/
    
public List<ShortQuestion> loadShortQuestionsForExam(String subjectId, String examtype, String year, String semester) {
    	
    	List<ShortQuestion> sa = new ArrayList<>();
		try {
			PreparedStatement statement = DBConnection.prepare(findAllShortByExamStatement);
			statement.setString(1, subjectId);
			statement.setString(2, year);
			statement.setString(3, semester);
			statement.setString(4, examtype);		
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				String questionId = rs.getString(1);
				String subjectIdIn = rs.getString(2);
				String yearIn = rs.getString(3);
				String semesterIn = rs.getString(4);
				String examTypeIn = rs.getString(5);
				String questionText = rs.getString(6);
				int possibleMarks = rs.getInt(7);
				ShortQuestion temp = new ShortQuestion(questionId, subjectIdIn,yearIn, semesterIn,examTypeIn,questionText, possibleMarks);
				sa.add(temp);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	return sa;
    }

public ShortQuestion loadShortQuestion(String questionId, String subjectId, String examtype, String year, String semester) {
	
	System.out.println("In the mapper");
	
	ShortQuestion sa = null;
	try {
		PreparedStatement statement = DBConnection.prepare(findShortQuestion);
		statement.setInt(1, Integer.parseInt(questionId));
		statement.setString(2, subjectId);
		statement.setString(3, year);
		statement.setString(4, semester);
		statement.setString(5, examtype);
		
		System.out.println(statement);
		
		ResultSet rs = statement.executeQuery();
		
		while(rs.next())
		{
			String questionIdIn = rs.getString(1);
			String subjectIdIn = rs.getString(2);
			String yearIn = rs.getString(3);
			String semesterIn = rs.getString(4);
			String examTypeIn = rs.getString(5);
			String questionText = rs.getString(6);
			int possibleMarks = rs.getInt(7);
			//public MultipleQuestion(String id, String subjectId, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC,String ansD, String correctAnswer,int possibleMark, int answerNumber){
			sa = new ShortQuestion(questionId, subjectIdIn,yearIn, semesterIn,examTypeIn,questionText, possibleMarks);
			//sa.add(temp);
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return sa;
	
}

    
    
    public List<Scriptbook> loadScriptbooksForExam(String subjectId, String examType,String year, String semester)
    {
    	List<Scriptbook> scriptbooks= new ArrayList<>();
		try {
			PreparedStatement statement = DBConnection.prepare(findAllScriptbooksByExamStatement);
			statement.setString(1, subjectId);
			statement.setString(2, year);
			statement.setString(3, semester);
			statement.setString(4, examType);		
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				String subjectIdIn = rs.getString(1);
				String yearIn = rs.getString(2);
				String semesterIn = rs.getString(3);
				String examTypeIn = rs.getString(4);
				boolean submitted = rs.getBoolean(5);
				String studentNumber = rs.getString(6);
				int scriptTotalMarks = rs.getInt(7);
				boolean marked = rs.getBoolean(8);
				Scriptbook temp = new Scriptbook(subjectId,year, semester,examType,studentNumber,scriptTotalMarks, marked);
				temp.setSubmitted(submitted);
				scriptbooks.add(temp);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
    	
    	return scriptbooks;
    }
    
    /*
    public List<Scriptbook> loadScriptbooksForExam(String subjectId, String examtype, String year, String semester) {
    	
    	String sqlQuery = findAllScriptbooksByExamStatement + insertA(subjectId) + "and examtype=" + insertA(examtype) + " and year="+insertA(year) + " and semester="+insertA(semester)+";";

    	return (loadScriptbooks(sqlQuery));
    	
    }*/
    
    
    
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
				String published = rs.getString(7);
				String closed = rs.getString(8);
				
				int totalMarks =  Integer.parseInt(rs.getString(9));
				
				String startTime = rs.getString(10);
				String endTime = rs.getString(11);
				
				Exam exam = new Exam(subjectId, year, semester, examType, examName, examCreator, totalMarks);
				
				exam.setPublished(published);
				exam.setClosed(closed);
				
				
				for (MultipleQuestion multipleQuestion : loadMultipleQuestionsForExam(subjectId, examType, year, semester)) {
					
					exam.addMultipleQuestion(multipleQuestion);
					
				}
				
				for (ShortQuestion shortQuestion : loadShortQuestionsForExam(subjectId, examType, year, semester)) {
					
					exam.addShortQuestion(shortQuestion);
					
				}
				
				for (Scriptbook scriptbook : loadScriptbooksForExam(subjectId, examType, year, semester)) {
				
					exam.addScriptbookToExam(scriptbook);
					
				}	
				System.out.println(exam.getSubjectId());
				
				exams.add(exam);
			
				
				
			}
					
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    	return exams;
    }
    
    public Exam loadExam(String subjectId, String year, String semester, String examType) {
    	
    	//Exam exam = new Exam(subjectId,year,semester,examType,null,null, 0);
    	Exam exam = null;
    	try {
	    	
	    	PreparedStatement statement = DBConnection.prepare(findExam);
	   
			statement.setString(1, subjectId);
			statement.setString(2, year);
			statement.setString(3,semester);
			statement.setString(4,examType);
	    	
	    	ResultSet rs = statement.executeQuery();

	    	
			while (rs.next()) {
				
				String subjectIdIn = rs.getString(1);
				String yearIn = rs.getString(2);
				String semesterIn = rs.getString(3);
				String examTypeIn = rs.getString(4);
				String examName = rs.getString(5);
				String examCreator = rs.getString(6);
				String published = rs.getString(7);
				String closed = rs.getString(8);
				
				int totalMarks =  Integer.parseInt(rs.getString(9));
				
				String startTime = rs.getString(10);
				String endTime = rs.getString(11);
				
				exam = new Exam(subjectId, year, semester, examType, examName, examCreator, totalMarks,published,closed);
				
				
				for (MultipleQuestion multipleQuestion : loadMultipleQuestionsForExam(subjectId, examType, year, semester)) {
					
					exam.addMultipleQuestion(multipleQuestion);
					
				}
				
				for (ShortQuestion shortQuestion : loadShortQuestionsForExam(subjectId, examType, year, semester)) {
					
					exam.addShortQuestion(shortQuestion);
					
				}
				
				for (Scriptbook scriptbook : loadScriptbooksForExam(subjectId, examType, year, semester)) {
				
					exam.addScriptbookToExam(scriptbook);
					
				}	
							
				
			}
					
					
		} catch (SQLException e) {
	
		}
    	
    	
    	return exam;
    }
    
    
	/*public List<MultipleQuestion> loadMultipleChoiceQuestions(String sqlQuery) {
	
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
	
	}*/
	
	private boolean isTrue(String string) {
		
		if (string=="true") return true;
		if (string=="TRUE") return true;
		if (string=="1") return true;
		return false;
		
	}
	
	/*public List<Scriptbook> loadScriptbooks(String sqlQuery) {
		
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
	
	}*/
	
	/*public List<ShortQuestion> loadShortQuestions(String sqlQuery) {
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
	}*/
	
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
	
	public void deleteShortQuestionsById(Exam exam,String questionId)
	{
		PreparedStatement statement;
		try {
			statement = DBConnection.prepare(deleteShortStatementQuestionId);
			statement.setInt(1, Integer.parseInt(questionId));
			statement.setString(2, exam.getSubjectID());
			statement.setString(3, exam.getYear());
			statement.setString(4, exam.getSemester());
			statement.setString(5, exam.getExamType());
			
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void deleteMultiplleQuestionsById(Exam exam,String questionId)
	{
		PreparedStatement statement;
		try {
			statement = DBConnection.prepare(deleteMultipleStatementQuestionId);
			statement.setInt(1, Integer.parseInt(questionId));
			statement.setString(2, exam.getSubjectID());
			statement.setString(3, exam.getYear());
			statement.setString(4, exam.getSemester());
			statement.setString(5, exam.getExamType());
			
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
			statement.setString(11, q.getCorrectAnswer());
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
	
	
	//TODO:
	public void updateShort(String subjectId, String year, String semester, String examType, int questionId, String studentNumber, ShortAttempt sa) throws Exception {
		
		PreparedStatement statement = DBConnection.prepare(changeShortMark);
		statement.setInt(1, (int)sa.getMark());
		statement.setString(2, subjectId);
		statement.setString(3, year);
		statement.setString(4, semester);
		statement.setString(5, examType);
		statement.setInt(6, questionId);
		statement.setString(7, studentNumber);
		
		System.out.println(statement);
		
		statement.execute();
		
	}
	
	public void updateMultiple(String subjectId, String year, String semester, String examType, int questionId, String studentNumber, MultipleAttempt ma) throws Exception {
		
		PreparedStatement statement = DBConnection.prepare(changeMultiMark);
		statement.setInt(1, (int)ma.getMark());
		statement.setString(2, subjectId);
		statement.setString(3, year);
		statement.setString(4, semester);
		statement.setString(5, examType);
		statement.setInt(6, questionId);
		statement.setString(7, studentNumber);
		
		System.out.println(statement);
		
		statement.execute();
	}
	
	public int getVersionMultiple(String subjectId, String year, String semester, String examType, int questionId, String studentNumber) throws Exception {
		
		int version;
		
		PreparedStatement statement = DBConnection.prepare(getVersionMultiple);
		
		statement.setString(1, subjectId);
		statement.setString(2, year);
		statement.setString(3, semester);
		statement.setString(4, examType);
		statement.setString(5, studentNumber);
		statement.setInt(6, questionId);
		
		
		System.out.println(statement);
		
		ResultSet rs = statement.executeQuery();
		
		rs.next();
		
		version = rs.getInt(1);
		
		System.out.println("VERSION MULTIPLE"+version);
		
		return version;
		
	}
	
	public int getVersionShort(String subjectId, String year, String semester, String examType, int questionId, String studentNumber) throws Exception {
		
		int version;
		
		PreparedStatement statement = DBConnection.prepare(getVersionShort);
		
		statement.setString(1, subjectId);
		statement.setString(2, year);
		statement.setString(3, semester);
		statement.setString(4, examType);
		statement.setString(5, studentNumber);
		statement.setInt(6, questionId);
		
		
		System.out.println(statement);
		
		ResultSet rs = statement.executeQuery();
		
		rs.next();
		
		version = rs.getInt(1);
		
		System.out.println("VERSION SHORT"+version);
		
		return version;
		
	}
	
	
	/*public void updateMarks(Scriptbook scriptbook) throws Exception
	{
		//update marks in the database
		//NOTE does not change the actual question result. Need to be implemented in the frontEnd
		for(ShortAttempt sa: scriptbook.getShortAttemptList())
		{
			
				PreparedStatement statement = DBConnection.prepare(changeShortMark);
				statement.setInt(1, (int)sa.getMark());
				statement.setString(2, scriptbook.getSubjectId());
				statement.setString(3, scriptbook.getYear());
				statement.setString(4,scriptbook.getSemester());
				statement.setString(5, scriptbook.getExamType());
				statement.setInt(6, Integer.parseInt(sa.getQuestionId()));
				statement.setString(7,scriptbook.getStudentNumber());
				
				System.out.println(statement);
				
				statement.execute();
			
		}
		for(MultipleAttempt ma: scriptbook.getMultipleAttemptList())
		{
			
				PreparedStatement statement = DBConnection.prepare(changeMultiMark);
				statement.setInt(1, (int) ma.getMark());
				statement.setString(2, scriptbook.getSubjectId());
				statement.setString(3, scriptbook.getYear());
				statement.setString(4,scriptbook.getSemester());
				statement.setString(5, scriptbook.getExamType());
				statement.setInt(6, Integer.parseInt(ma.getQuestionId()));
				statement.setString(7,scriptbook.getStudentNumber());
				
				System.out.println(statement);
				
				statement.execute();
			
		}
		
	}*/
	
	
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
				String published = (String) updatedExam.getPublished();
				String closed = (String) updatedExam.getClosed();
				int totalMarks = updatedExam.getTotalMarks();
				
				String startTimeString = null;
				String endTimeString = null;
				
				Date startTime = updatedExam.getStartDate();
				Date endTime = updatedExam.getEndDate();

				try
				{
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					startTimeString = dateFormat.format(startTime);
					endTimeString = dateFormat.format(endTime);  
				}catch(Exception e)
				{
					
				}

				
				statement.setString(1, examName);
				statement.setString(2, examCreator);
				statement.setInt(3, totalMarks);
				statement.setString(4,startTimeString);
				statement.setString(5, endTimeString);
				
				statement.setString(6, published);
				statement.setString(7, closed);
				
				statement.setString(8, subjectID);
				statement.setString(9, year);
				statement.setString(10, semester);
				statement.setString(11, examType);
				
				System.out.println(statement);
				
				statement.execute();
				return new String("Success");
			}else
			{
				System.out.println("Cannot edit, One of the students has already taken the exam");
				return new String("Failure");
			}
		
		}catch(SQLException e)
		{
			e.printStackTrace();
			return new String("Failure");
		}
	}
	
	public String addExam(Exam newExam)
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
			String startTimeString = null;
			String endTimeString = null;
			try
			{
				Date startTime = newExam.getStartDate();
				Date endTime = newExam.getEndDate();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				startTimeString = dateFormat.format(startTime);
				endTimeString = dateFormat.format(endTime);  
			}catch(Exception e)
			{
	
			}

			statement.setString(1, subjectId);
			statement.setString(2, year);
			statement.setString(3, semester);
			statement.setString(4, examType);
			statement.setString(5, examName);
			statement.setString(6, examCreator);
			statement.setInt(7, totalMarks);
			statement.setString(8,startTimeString);
			statement.setString(9,endTimeString);
			
			System.out.println(statement);
			
			statement.execute();
			return new String("Success");
		}catch(SQLException e)
		{
			e.printStackTrace();
			return new String("Falure");
		}
	}
	
	
	public void deleteExam(String subjectId, String year, String semester, String examType) throws Exception
	{
		
			PreparedStatement statement = DBConnection.prepare(deleteExam);
			statement.setString(1, subjectId);
			statement.setString(2, year);
			statement.setString(3, semester);
			statement.setString(4, examType);
			
			System.out.println(statement);
			
			statement.execute();
		
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
	
	public void addScriptbook(Scriptbook scriptbook) throws Exception
	{
		
			PreparedStatement statement = DBConnection.prepare(addScriptbook);
			
			boolean submitted = scriptbook.isSubmitted();
			
			System.out.println("Is it submitted according to data mapper? "+submitted);
			
			statement.setString(1, scriptbook.getSubjectId());
			statement.setString(2, scriptbook.getYear());
			statement.setString(3, scriptbook.getSemester());
			statement.setString(4, scriptbook.getExamType());
			statement.setBoolean(5, submitted);
			statement.setString(6, scriptbook.getStudentNumber());
			statement.setInt(7,scriptbook.getTotalMark());
			statement.setBoolean(8, scriptbook.isMarked());
			
			System.out.println(statement);
			
			statement.execute();
			
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
	
	
	//Returns a specific scriptbook
	public Scriptbook findSpecificScriptBook(String subjectCode, String year, String semester, String examType, String studentNumber) throws Exception {
		
		PreparedStatement statement = DBConnection.prepare(findSpecificScriptBook);
		statement.setString(1, subjectCode);
		statement.setString(2, year);
		statement.setString(3, semester);
		statement.setString(4, examType);
		statement.setString(5, studentNumber);
		
		ResultSet rs = statement.executeQuery();
		
		rs.next();
		
		boolean submitted = rs.getBoolean(5);
		
		int scriptTotalMarks = rs.getInt(7);
		boolean marked = rs.getBoolean(8);
		Scriptbook temp = new Scriptbook(subjectCode,year, semester,examType,studentNumber,scriptTotalMarks, marked);
		temp.setSubmitted(submitted);
		return temp;
		
	}
	
	
	
	public Scriptbook findScriptByExamStudent(Exam exam, String studentID)
	{
		try {
			PreparedStatement statement = DBConnection.prepare(findScriptBookByExamStudent);
			statement.setString(1, exam.getSubjectID());
			statement.setString(2, exam.getYear());
			statement.setString(3, exam.getSemester());
			statement.setString(4, exam.getExamType());
			statement.setString(5, studentID);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next())
			{
				String subjectId = rs.getString(1);
				String year = rs.getString(2);
				String semester = rs.getString(3);
				String examType = rs.getString(4);
				boolean submitted = rs.getBoolean(5);
				String studentNumber = rs.getString(6);
				int scriptTotalMarks = rs.getInt(7);
				boolean marked = rs.getBoolean(8);
				Scriptbook temp = new Scriptbook(subjectId,year, semester,examType,studentNumber,scriptTotalMarks, marked);
				temp.setSubmitted(submitted);
				return temp;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//TODO:
	public void incrementShortVersion(String subjectId, String year, String semester, String examType, int questionId, String studentNumber, int version) {
		try {
		//increment
		version += 1; 
		//increment on database
		PreparedStatement statement2 = DBConnection.prepare(changeShortVersion);
		//"UPDATE multipleAttempt SET version = ? where subjectId=? AND year=? and semester = ? and examType = ? and questionId=? and studentNumber = ? ";
		
		statement2.setInt(1, version);
		statement2.setString(2, subjectId);
		statement2.setString(3, year);
		statement2.setString(4, semester);
		statement2.setString(5, examType);
		statement2.setInt(6, questionId);
		statement2.setString(7, studentNumber);
		
		System.out.println(statement2);
		
		statement2.execute();
		}
		catch (Exception e) {
		}
		
	}
	
	public void incrementMultipleVersion(String subjectId, String year, String semester, String examType, int questionId, String studentNumber, int version) {
		try {		
		//increment
		version += 1; 
		//increment on database
		PreparedStatement statement2 = DBConnection.prepare(changeMultiVersion);
		//"UPDATE multipleAttempt SET version = ? where subjectId=? AND year=? and semester = ? and examType = ? and questionId=? and studentNumber = ? ";
		
		statement2.setInt(1, version);
		statement2.setString(2, subjectId);
		statement2.setString(3, year);
		statement2.setString(4, semester);
		statement2.setString(5, examType);
		statement2.setInt(6, questionId);
		statement2.setString(7, studentNumber);
		
		System.out.println(statement2);
		
		statement2.execute();
		}
		catch (Exception e) {}
		
	}
	
	
	public List<ShortAttempt> getShortAttempts(Exam exam, String studentID)
	{
		List<ShortAttempt> attempts = new ArrayList<>();
		try
		{
			PreparedStatement statement = DBConnection.prepare(getAttemptsShort);
			statement.setString(1, exam.getSubjectID());
			statement.setString(2, exam.getYear());
			statement.setString(3, exam.getSemester());
			statement.setString(4, exam.getExamType());
			statement.setString(5, studentID);
			
			ResultSet rs = statement.executeQuery();
			
			System.out.println("short access query"+statement);
			
			while(rs.next())
			{
				int questionId = rs.getInt(1);
				String subjectId = rs.getString(2);
				String year =rs.getString(3);
				String semester = rs.getString(4);
				String examType = rs.getString(5);
				String studentNumber = rs.getString(6);
				String attemptAns = rs.getString(7);
				int mark = rs.getInt(8);
				boolean marked = rs.getBoolean(9);
				
				int version = rs.getInt(10);
				
				System.out.println("short version"+version);
				
				
				
				ShortAttempt temp = new ShortAttempt(Integer.toString(questionId),subjectId,year,semester,examType,studentNumber,attemptAns);
				
				//Set the version number on the attempt
				
				temp.setVersion(version);
				
				//Mark it with read in mark
				//if (marked) {temp.setMark(mark);}
				temp.setMark(mark);
				attempts.add(temp);
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return attempts;
	}
	
	
	public List<MultipleAttempt> getMultipleAttempt(Exam exam, String studentID)
	{
		
		List<MultipleAttempt> attempts = new ArrayList<>();
		try
		{
			PreparedStatement statement = DBConnection.prepare(getAttemptsMultiple);
			statement.setString(1, exam.getSubjectID());
			statement.setString(2, exam.getYear());
			statement.setString(3, exam.getSemester());
			statement.setString(4, exam.getExamType());
			statement.setString(5, studentID);
			
			System.out.println(statement);
			
			ResultSet rs = statement.executeQuery();
			
			System.out.println("multipe access query"+statement);
			
			while(rs.next())
			{
				int questionId = rs.getInt(1);
				String subjectId = rs.getString(2);
				String year =rs.getString(3);
				String semester = rs.getString(4);
				String examType = rs.getString(5);
				String studentNumber = rs.getString(6);
				String attemptAns = rs.getString(7);
				int mark = rs.getInt(8);
				boolean marked = rs.getBoolean(9);
				
				int version = rs.getInt(10);
				
				System.out.println("multiple read in" + version);
				
				
				MultipleAttempt temp = new MultipleAttempt(Integer.toString(questionId),subjectId,year,semester,examType,studentNumber,attemptAns);
				
				//Set the version
				temp.setVersion(version);
				
				//Mark it with read in mark
				//if (marked) {temp.setMark(mark);}
				temp.setMark(mark);
				attempts.add(temp);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return attempts;
	}
	
	
	public void updateScriptBookTotalMark(int newMark, String subjectCode, String year, String semester, String examType, String studentNumber) throws Exception {
	
		PreparedStatement statement = DBConnection.prepare(updateScriptbookTotalMark);
	
		statement.setInt(1, newMark);
		statement.setString(2, subjectCode);
		statement.setString(3, year);
		statement.setString(4, semester);
		statement.setString(5, examType);
		statement.setString(6, studentNumber);
		
		statement.execute();
		
	}

	public void studentSubmitsExam(Scriptbook scriptbook) throws Exception
	{
		
//			PreparedStatement statementUpdateScriptbook = DBConnection.prepare(submittedScriptbook);
//			statementUpdateScriptbook.setString(1, scriptbook.getSubjectId());
//			statementUpdateScriptbook.setString(2, scriptbook.getYear());
//			statementUpdateScriptbook.setString(3, scriptbook.getSemester());
//			statementUpdateScriptbook.setString(4,	scriptbook.getExamType());
//			statementUpdateScriptbook.setString(5, scriptbook.getStudentNumber());
//			statementUpdateScriptbook.execute();
			
			List<ShortAttempt> sa = scriptbook.getShortAttemptList();
			List<MultipleAttempt> ma = scriptbook.getMultipleAttemptList();
			
			for(ShortAttempt a:sa)
			{
				System.out.println(a.getShortAnswer());
				PreparedStatement statementSubmitShort = DBConnection.prepare(submitShortAttempt);
				statementSubmitShort.setInt(1, Integer.parseInt(a.getQuestionId()));
				statementSubmitShort.setString(2, scriptbook.getSubjectId());
				statementSubmitShort.setString(3, scriptbook.getYear());
				statementSubmitShort.setString(4, scriptbook.getSemester());
				statementSubmitShort.setString(5, scriptbook.getExamType());
				statementSubmitShort.setString(6, scriptbook.getStudentNumber());
				statementSubmitShort.setString(7, a.getShortAnswer());
				statementSubmitShort.setInt(8, a.getMark());
				statementSubmitShort.setBoolean(9, false);
				System.out.println("short "+statementSubmitShort);
				statementSubmitShort.execute();
			}
			
			for(MultipleAttempt a:ma)
			{
				System.out.println(a.getChoiceAsString());
				System.out.println(a.getQuestionId());
				PreparedStatement statementSubmitMultiple = DBConnection.prepare(submitMultipleAttempt);
				statementSubmitMultiple.setInt(1, Integer.parseInt(a.getQuestionId()));
				statementSubmitMultiple.setString(2, scriptbook.getSubjectId());
				statementSubmitMultiple.setString(3, scriptbook.getYear());
				statementSubmitMultiple.setString(4, scriptbook.getSemester());
				statementSubmitMultiple.setString(5, scriptbook.getExamType());
				statementSubmitMultiple.setString(6, scriptbook.getStudentNumber());
				statementSubmitMultiple.setString(7, a.getChoiceAsString());
				statementSubmitMultiple.setInt(8, a.getMark());
				statementSubmitMultiple.setBoolean(9, false);
				System.out.println("short "+statementSubmitMultiple);
				statementSubmitMultiple.execute();
			}
		
	}
	
	
}
