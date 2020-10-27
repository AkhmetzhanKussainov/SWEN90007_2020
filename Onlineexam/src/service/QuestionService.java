package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datasource.DBConnection;
import datasource.ExamDataMapper;
import domain.Exam;
import domain.MultipleQuestion;
import domain.ShortQuestion;
import domain.Question.choice;
import domain.Question;
import uow.QuestionUow;

public class QuestionService {
	
	ExamDataMapper edm;
	
	public QuestionService() {
		
		edm = new ExamDataMapper();
		
		//multipleQuestions = qdm.loadMultipleChoiceQuestions();
		//shortQuestions = qdm.loadShortQuestions();
		
	}
	
	private List<MultipleQuestion> multipleQuestions;
	
	
	private List<ShortQuestion> shortQuestions;
	
	public List<MultipleQuestion> getAllMultipleQuestions(String subjectCode, String semester, String year, String examType) {
		   
			List<MultipleQuestion> multipleQuestions = new ArrayList<>();
			
			multipleQuestions = edm.loadMultipleQuestionsForExam(subjectCode, examType, year, semester);
			
		//	MultipleQuestion multiq1 = new MultipleQuestion("1", "DEF101", "2020", "2", "M", "What is life?", "None", "Null", "None", "All the above", choice.D, 5, 4);
		///	MultipleQuestion multiq2 = new MultipleQuestion("2", "DEF101", "2020", "2", "M", "Why?", "None", "Null", "None", "All the above", choice.D, 5, 4);
		//	MultipleQuestion multiq3 = new MultipleQuestion("3", "DEF101", "2020", "2", "M", "What?", "None", "Null", "None", "All the above", choice.D, 5, 4);
		//	MultipleQuestion multiq4 = new MultipleQuestion("4", "DEF101", "2020", "2", "M", "How", "None", "Null", "None", "All the above", choice.D, 5, 4);
//			MultipleQuestion(String id, String subjectCode, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int answerNumber
			
			
		//	multipleQuestions.add(multiq1);
		//	multipleQuestions.add(multiq2);
		//	multipleQuestions.add(multiq3);
		//	multipleQuestions.add(multiq4);
			
		    return multipleQuestions;
		    
	    
	}
	
	
	public MultipleQuestion getMultipleQuestion(String Id,String subjectCode, String semester, String year, String examType) {
	    
		return edm.loadMultipleQuestion(Id, subjectCode, examType, year, semester);
		//MultipleQuestion multiq1 = new MultipleQuestion(Id, subjectCode, year, semester, examType, "What is life?", "None", "Null", "None", "All the above", choice.D, 5, 4);
		
	    //return multiq1;
	    
	}
	
	public List<ShortQuestion> getAllShortQuestions(String subjectCode, String semester, String year, String examType) {
	    
		List<ShortQuestion> shortQuestions = new ArrayList<>();
		shortQuestions = edm.loadShortQuestionsForExam(subjectCode, examType, year, semester);
		
		/*ShortQuestion shortq1 = new ShortQuestion("1", "DEF101", "2020", "2", "M", "What is life?", 10);
		ShortQuestion shortq2 = new ShortQuestion("2", "DEF101", "2020", "2", "M", "What", 10);
		ShortQuestion shortq3 = new ShortQuestion("3", "DEF101", "2020", "2", "M", "Why", 10);
		ShortQuestion shortq4 = new ShortQuestion("4", "DEF101", "2020", "2", "M", "Done", 10);
//		MultipleQuestion(String id, String subjectCode, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int answerNumber
		
		
		shortQuestions.add(shortq1);
		shortQuestions.add(shortq2);
		shortQuestions.add(shortq3);
		shortQuestions.add(shortq4);*/
		
	    return shortQuestions;
	    
	}
	
	public ShortQuestion getShortQestion(String Id,String subjectCode, String semester, String year, String examType) {
	
		return edm.loadShortQuestion(Id, subjectCode, examType, year, semester);
	//	ShortQuestion shortq = new ShortQuestion(Id, subjectCode, year, semester, examType, "What is this?", 10);
		
	////    return shortq;
	    
	}
	
	
	
	public void updateUow(Question q) {
		QuestionUow.registerDirty(q);
	}
	
	public void commitUow() {
		QuestionUow.commitAll();
	}

	public Boolean addMultipleQuestion(Exam exam, MultipleQuestion mq) {
		try {
			List<MultipleQuestion> questions = null;
			questions = edm.loadMultipleQuestionsForExam(exam.getSubjectId(), exam.getExamType(), exam.getYear(), exam.getSemester());
			if(questions.isEmpty())
			{
				mq.setId("1");
			}else
			{
				int maxId = 1;
				for(int i=0;i<questions.size();i++)
				{
					if(maxId<Integer.parseInt(questions.get(i).getId()))
					{
						maxId = Integer.parseInt(questions.get(i).getId());
					}
				}
				mq.setId(Integer.toString(maxId+1));
			}
			
			edm.addMultipleQuestions(exam, mq);
			// try creating mq from data mappper
			return true;
		} catch (Exception e) {
			//
			System.out.println(e);
			return false;
	 
		}
	}
	
	public Boolean addShortQuestion(ShortQuestion sq) {
		try {
			// try creating sq from data mappper
			//public Exam(String subjectId, String year, String semester, String examType)
			List<ShortQuestion> questions = null;
			Exam exam = new Exam(sq.getSubjectId(),sq.getYear(),sq.getSemester(),sq.getExamType());
			questions = edm.loadShortQuestionsForExam(exam.getSubjectId(), exam.getExamType(), exam.getYear(), exam.getSemester());
			if(questions.isEmpty())
			{
				sq.setId("1");
			}else
			{
				int maxId = 1;
				for(int i=0;i<questions.size();i++)
				{
					if(maxId<Integer.parseInt(questions.get(i).getId()))
					{
						maxId = Integer.parseInt(questions.get(i).getId());
					}
				}
				sq.setId(Integer.toString(maxId));
			}
			
			edm.addShortQuestions(exam, sq);
			return true;
		} catch (Exception e) {
			//
			System.out.println(e);
			return false;
	
		}
	}
	
	public Boolean updateShortQuestion(ShortQuestion sq) {
		try {
			//public Exam(String subjectId, String year, String semester, String examType)
			Exam exam = new Exam(sq.getSubjectId(),sq.getYear(),sq.getSemester(),sq.getExamType());
			edm.deleteShortQuestionsById(exam, sq.getId());
			edm.addShortQuestions(exam, sq);
			// try updating sq from data mappper
			return true;
		} catch (Exception e) {
			//
			System.out.println(e);
			return false;
	
		}
	}
	
	public Boolean updateMultipleQuestion(MultipleQuestion mq) {
		try {
			// try updating mq from data mappper
			Exam exam = new Exam(mq.getSubjectId(),mq.getYear(),mq.getSemester(),mq.getExamType());
			edm.deleteMultiplleQuestionsById(exam, mq.getId());
			edm.addMultipleQuestions(exam, mq);
			return true;
		} catch (Exception e) {
			//
			System.out.println(e);
			return false;
	
		}
	}
	
	public Boolean deleteMultipleQuestion(Exam exam, String questionID) {
		try {
			// try deleting mq from data mappper
			edm.deleteMultiplleQuestionsById(exam, questionID);
			return true;
		} catch (Exception e) {
			//
			System.out.println(e);
			return false;
	
		}
	}
	
	public Boolean deleteShortQuestion(Exam exam, String questionID) {
		try {
			// try deleting sq from data mappper
			edm.deleteShortQuestionsById(exam, questionID);
			return true;
		} catch (Exception e) {
			//
			System.out.println(e);
			return false;
	
		}
	}

}
