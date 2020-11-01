package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Exceptions.AlreadySubmittedException;
import Exceptions.DatabaseException;
import Exceptions.FormException;
import datasource.DBConnection;
import datasource.ExamDataMapper;
import domain.Exam;
import domain.MultipleAttempt;
import domain.MultipleQuestion;
import domain.ShortQuestion;
import domain.Question.choice;
import domain.Question;
import domain.Scriptbook;
import domain.ShortAttempt;
import uow.QuestionUow;

public class ScriptbookService {

	private ExamDataMapper edm;

	public ScriptbookService() {
		edm = new ExamDataMapper();
	}

	
	
	public void updateMultiple(String subjectId, String year, String semester, String examType, int questionId, String studentNumber, MultipleAttempt ma) {
		try {
			edm.updateMultiple(subjectId, year, semester, examType, questionId, studentNumber, ma);
		}
		catch (Exception e) {
			
		}
	}
	
	public void updateShort(String subjectId, String year, String semester, String examType, int questionId, String studentNumber, ShortAttempt sa) {
	
		try {
			edm.updateShort(subjectId, year, semester, examType, questionId, studentNumber, sa);
		}
		catch (Exception e) {
			
		}
		
	}
	
	public int getVersionShort(String subjectCode, String year, String semester, String examType, int questionId, String studentId) {
		
		//Hack
		int version=0;
		
		try {
			version = edm.getVersionShort(subjectCode, year, semester, examType, questionId, studentId);
		}
		catch (Exception e) {
			
		}
		
		return version;
	}
	
	public int getVersionMultiple(String subjectCode, String year, String semester, String examType, int questionId, String studentId) {
		
		//Hack
		int version=0;
		
		try {
			version = edm.getVersionMultiple(subjectCode, year, semester, examType, questionId, studentId);
		}
		catch (Exception e) {
			
		}
		
		return version;
	}
	
	
	
	public void updateScriptBookTotalMark(int newMark, String subjectCode, String year, String semester, String examType, String studentNumber) {
		try {
			edm.updateScriptBookTotalMark(newMark, subjectCode, year, semester, examType, studentNumber);
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	/*
	 * public void submitScriptbook(List<MultipleAttempt> multipleAttempts,
	 * List<ShortAttempt> shortAttempts) { //public Scriptbook(String subjectId,
	 * String year, String semester, String examType, String studentId, int
	 * scriptMark, boolean marked) System.out.println(multipleAttempts); Scriptbook
	 * scriptbook = null; if(!multipleAttempts.isEmpty()) { MultipleAttempt attempt
	 * = multipleAttempts.get(0); Exam exam =
	 * edm.loadExam(attempt.getSubjectId(),attempt.getYear(),attempt.getSemester(),
	 * attempt.getExamType()); int marks = exam.getTotalMarks(); scriptbook = new
	 * Scriptbook(attempt.getSubjectId(),attempt.getYear(),attempt.getSemester(),
	 * attempt.getExamType(),attempt.getStudentNumber(),marks,false); }else
	 * if(!shortAttempts.isEmpty()) { ShortAttempt attempt = shortAttempts.get(0);
	 * Exam exam =
	 * edm.loadExam(attempt.getSubjectId(),attempt.getYear(),attempt.getSemester(),
	 * attempt.getExamType()); int marks = exam.getTotalMarks(); scriptbook = new
	 * Scriptbook(attempt.getSubjectId(),attempt.getYear(),attempt.getSemester(),
	 * attempt.getExamType(),attempt.getStudentNumber(),marks,false); }
	 * 
	 * edm.studentSubmitsExam(scriptbook);
	 * 
	 * for(MultipleAttempt ma: multipleAttempts) { //
	 * System.out.println(ma.getQuestionId());
	 * System.out.println(ma.getAttemptedAns()); }
	 * //System.out.println(shortAttempts); }
	 * 
	 * public void submitScriptbookMarks(List<MultipleAttempt> multipleAttempts,
	 * List<ShortAttempt> shortAttempts) { //System.out.println(multipleAttempts);
	 * 
	 * Scriptbook scriptbook = null; if(!multipleAttempts.isEmpty()) {
	 * MultipleAttempt attempt = multipleAttempts.get(0); Exam exam =
	 * edm.loadExam(attempt.getSubjectId(),attempt.getYear(),attempt.getSemester(),
	 * attempt.getExamType()); int marks = exam.getTotalMarks(); scriptbook = new
	 * Scriptbook(attempt.getSubjectId(),attempt.getYear(),attempt.getSemester(),
	 * attempt.getExamType(),attempt.getStudentNumber(),marks,false); }else
	 * if(!shortAttempts.isEmpty()) { ShortAttempt attempt = shortAttempts.get(0);
	 * Exam exam =
	 * edm.loadExam(attempt.getSubjectId(),attempt.getYear(),attempt.getSemester(),
	 * attempt.getExamType()); int marks = exam.getTotalMarks(); scriptbook = new
	 * Scriptbook(attempt.getSubjectId(),attempt.getYear(),attempt.getSemester(),
	 * attempt.getExamType(),attempt.getStudentNumber(),marks,false); }
	 * 
	 * edm.updateMarks(scriptbook); for(MultipleAttempt ma: multipleAttempts) { //
	 * System.out.println(ma.getQuestionId()); System.out.println(ma.getMark()); }
	 * System.out.println(shortAttempts); }
	 */

	public void submitScriptbook(Scriptbook sb) throws AlreadySubmittedException, DatabaseException {
		
		//Create the scriptbook in database
		try {edm.addScriptbook(sb);}
		
		catch (Exception e) {
			
			throw new AlreadySubmittedException("You've already submitted this exam: "+e.getMessage(), e);
			
		}
		
		//Add the details to the scriptbook
		try {edm.studentSubmitsExam(sb);}
		
		catch (Exception e) {
			
			throw new DatabaseException("Unable to create scriptbook attempts: "+e.getMessage(), e);
			
		}
		
		System.out.println(sb);
	}

//	public void markScriptbook(Scriptbook sb) {
//		try {
//			edm.updateMarks(sb);
//			}
//		catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		System.out.println(sb);
//		System.out.println(sb.getTotalMark());
//	}

	public List<Scriptbook> getExamScriptbooks(String subjectCode, String year, String semester, String examType) {

		System.out.println("Getting scriptbooks");
		
		List<Scriptbook> scriptbooks = new ArrayList<>();

		try {
			
			scriptbooks = edm.loadScriptbooksForExam(subjectCode, examType, year, semester);

			// Scriptbook(String subjectId, String year, String semester, String examType,
			// String studentId, int scriptMark, boolean marked)

			/*Scriptbook sb1 = new Scriptbook("DEF101", "2020", "2", "F", "S12", 0, false, true);
			Scriptbook sb2 = new Scriptbook("DEF101", "2020", "2", "F", "S13", 80, true, true);
			Scriptbook sb3 = new Scriptbook("DEF101", "2020", "2", "F", "S14", 80, true, true);
			Scriptbook sb4 = new Scriptbook("DEF101", "2020", "2", "F", "S15", 80, true, true);*/

			//scriptbooks.add(sb1);
			//scriptbooks.add(sb2);
			//scriptbooks.add(sb3);
			//scriptbooks.add(sb4);

		} catch (Exception e) {
			System.out.println(e);

		}
		
		System.out.println("Gotten scriptbooks");
		
		for (Scriptbook scriptbook : scriptbooks) {
			System.out.println(scriptbook.getStudentNumber());
		}
		
		return scriptbooks;

	}

	private List<MultipleAttempt> multipleattempts;

	private List<ShortAttempt> shortattempts;

	public List<MultipleAttempt> getAllMultipleAttempts(String subjectCode, String year, String semester,
			String examType, String studentNumber) {

		List<MultipleAttempt> multipleAttempts = new ArrayList<>();

		System.out.println("Started");
		
		// MultipleAttempt(String questionId, String subjectId, String year, String
		// semester, String examType,
		// String studentNumber, String attemptedAns)
		
		try
		{
			//public Exam(String subjectId, String year, String semester, String examType)
			Exam exam = new Exam(subjectCode,year,semester,examType);
			multipleAttempts = edm.getMultipleAttempt(exam, studentNumber);
		}catch(Exception e)
		{
			System.out.println("Error");
			e.printStackTrace();
		}

		/*MultipleAttempt multia1 = new MultipleAttempt("1", "DEF101", "2020", "2", "M", "S12", "A", "A", 5,
				"What is life?");
		MultipleAttempt multia2 = new MultipleAttempt("2", "DEF101", "2020", "2", "M", "S12", "B", "C", 5, "Why?");
		MultipleAttempt multia3 = new MultipleAttempt("3", "DEF101", "2020", "2", "M", "S12", "C", "C", 5, "What?");
		MultipleAttempt multia4 = new MultipleAttempt("4", "DEF101", "2020", "2", "M", "S12", "D", "D", 5, "How");*/
//		MultipleQuestion(String id, String subjectCode, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int answerNumber

	/*	multipleAttempts.add(multia1);
		multipleAttempts.add(multia2);
		multipleAttempts.add(multia3);
		multipleAttempts.add(multia4);*/

		return multipleAttempts;

	}

	// Simulating fetching marked attempts, no need to create another service method
	// for this getAllShortAttempts will work for both Student and Teacher ideally

	/*
	 * public List<MultipleAttempt> getAllMultipleAttemptsDemo(String subjectCode,
	 * String semester, String year, String examType, String studentNumber) {
	 * 
	 * List<MultipleAttempt> multipleAttempts = new ArrayList<>();
	 * 
	 * // public MultipleAttempt(String questionId, String subjectId, String year,
	 * // String semester, String examType, // String studentNumber, String
	 * attemptedAns, String correctAns, Integer // totalMarks, String questionText,
	 * int mark) {
	 * 
	 * MultipleAttempt multia1 = new MultipleAttempt("1", "DEF101", "2020", "2",
	 * "M", "S12", "A", "A", 5, "What is life?", 5); MultipleAttempt multia2 = new
	 * MultipleAttempt("2", "DEF101", "2020", "2", "M", "S12", "B", "C", 5, "Why?",
	 * 0); MultipleAttempt multia3 = new MultipleAttempt("3", "DEF101", "2020", "2",
	 * "M", "S12", "C", "C", 5, "What?", 5); MultipleAttempt multia4 = new
	 * MultipleAttempt("4", "DEF101", "2020", "2", "M", "S12", "D", "D", 5, "How",
	 * 5); // MultipleQuestion(String id, String subjectCode, String year, String
	 * semester, String examType, String questionText, String ansA, String ansB,
	 * String ansC, String ansD, choice correctAnswer, int possibleMark, int
	 * answerNumber
	 * 
	 * multipleAttempts.add(multia1); multipleAttempts.add(multia2);
	 * multipleAttempts.add(multia3); multipleAttempts.add(multia4);
	 * 
	 * return multipleAttempts;
	 * 
	 * }
	 */

	
	public int getCurrentMark(String subjectCode, String year, String semester, String examType, String studentNumber) {
		
		System.out.println("Getting the current mark");
		
		Scriptbook scriptbook = null;

		try {
			//Could be more efficient by not loading exam
			scriptbook = edm.findSpecificScriptBook(subjectCode, year, semester, examType, studentNumber);		

		} catch (Exception e) {
			
			System.out.println(e);

		}
		
		return scriptbook.getTotalMark();
		
	}	
		
		
	public int getAutomaticMark(String subjectCode, String year, String semester, String examType, String studentNumber) {
		
		System.out.println("Getting the automatic mark");
		
		try {
		
			List<ShortAttempt> shortAttempts = getAllShortAttempts(subjectCode, year, semester, examType, studentNumber);
			List<MultipleAttempt> multipleAttempts = getAllMultipleAttempts(subjectCode, year, semester, examType, studentNumber);
			
			int mark=0;
			
			for (ShortAttempt sa : shortAttempts) {
				
				mark+=sa.getMark();
				
			}
			
			for (MultipleAttempt ma : multipleAttempts) {
				
				mark+=ma.getMark();
				
			}
			
			return mark;
			
		}
		
		catch(Exception e) {
			
			System.out.println("Error getting mark");
			
			return 0;
			
		}	
	
	}

	
	
	
	
	public List<ShortAttempt> getAllShortAttempts(String subjectCode, String year, String semester, String examType,
			String studentNumber) {

		List<ShortAttempt> shortAttempts = new ArrayList<>();

		// MultipleAttempt(String questionId, String subjectId, String year, String
		// semester, String examType,
		// String studentNumber, String attemptedAns)
		
		try
		{
			Exam exam = new Exam(subjectCode,year,semester,examType);
			shortAttempts = edm.getShortAttempts(exam, studentNumber);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

		/*
		 * ShortAttempt shorta1 = new ShortAttempt("1", "DEF101", "2020", "2", "M",
		 * "S12", "something", 5, "What is life?"); ShortAttempt shorta2 = new
		 * ShortAttempt("2", "DEF101", "2020", "2", "M", "S12", "something asa", 5,
		 * "What"); ShortAttempt shorta3 = new ShortAttempt("3", "DEF101", "2020", "2",
		 * "M", "S12", "something asdsad", 5, "Why"); ShortAttempt shorta4 = new
		 * ShortAttempt("4", "DEF101", "2020", "2", "M", "S12", "something asdasdad", 5,
		 * "Done"); // MultipleQuestion(String id, String subjectCode, String year,
		 * String semester, String examType, String questionText, String ansA, String
		 * ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int
		 * answerNumber
		 * 
		 * shortAttempts.add(shorta1); shortAttempts.add(shorta2);
		 * shortAttempts.add(shorta3); shortAttempts.add(shorta4);
		 */

		return shortAttempts;

	}

// Simulating fetching marked attempts, no need to create another service method for this getAllShortAttempts will work for both Student and Teacher ideally

	/*
	 * public List<ShortAttempt> getAllShortAttemptsDemo(String subjectCode, String
	 * semester, String year, String examType, String studentNumber) {
	 * 
	 * List<ShortAttempt> shortAttempts = new ArrayList<>();
	 * 
	 * // public ShortAttempt(String questionId, String subjectId, String year,
	 * String // semester, String examType, // String studentNumber, String
	 * attemptedAns,Integer totalMarks, String // questionText, int mark) {
	 * 
	 * ShortAttempt shorta1 = new ShortAttempt("1", "DEF101", "2020", "2", "M",
	 * "S12", "something", 5, "What is life?", 4); ShortAttempt shorta2 = new
	 * ShortAttempt("2", "DEF101", "2020", "2", "M", "S12", "something asa", 5,
	 * "What", 3); ShortAttempt shorta3 = new ShortAttempt("3", "DEF101", "2020",
	 * "2", "M", "S12", "something asdsad", 5, "Why", 3); ShortAttempt shorta4 = new
	 * ShortAttempt("4", "DEF101", "2020", "2", "M", "S12", "something asdasdad", 5,
	 * "Done", 4); // MultipleQuestion(String id, String subjectCode, String year,
	 * String semester, String examType, String questionText, String ansA, String
	 * ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int
	 * answerNumber
	 * 
	 * shortAttempts.add(shorta1); shortAttempts.add(shorta2);
	 * shortAttempts.add(shorta3); shortAttempts.add(shorta4);
	 * 
	 * return shortAttempts;
	 * 
	 * }
	 */

	public List<Scriptbook> getMarkedExamScriptbooksByStudent(String subjectCode, String year, String semester,
			String examType, String studentNumber) {

		List<Scriptbook> scriptbooks = new ArrayList<>();

		try {
			System.out.println("loaded scriptbooks");
			scriptbooks = edm.loadScriptbooksForExam(subjectCode, examType, year, semester);
			for(int i=0;i<scriptbooks.size();i++)
			{
				if(!scriptbooks.get(i).isMarked())
				{
					scriptbooks.remove(i);
				}
			}

			// Scriptbook(String subjectId, String year, String semester, String examType,
			// String studentId, int scriptMark, boolean marked)

			/*
			 * Scriptbook sb2 = new Scriptbook("DEF101", "2020", "2", "F", "S12", 80, true);
			 * Scriptbook sb3 = new Scriptbook("DEF101", "2020", "2", "F", "S12", 80, true);
			 * Scriptbook sb4 = new Scriptbook("DEF101", "2020", "2", "F", "S12", 80, true);
			 * 
			 * scriptbooks.add(sb2); scriptbooks.add(sb3); scriptbooks.add(sb4);
			 */

		} catch (Exception e) {
			System.out.println(e);

		}

		return scriptbooks;

	}

}
