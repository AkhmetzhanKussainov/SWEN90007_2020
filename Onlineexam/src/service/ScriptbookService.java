package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
	ExamDataMapper em;
	
	public ScriptbookService() {
	 em = new ExamDataMapper();
		
	}

	public void submitScriptbook(List<MultipleAttempt> multipleAttempts, List<ShortAttempt> shortAttempts) {
		
		//public Scriptbook(String subjectId, String year, String semester, String examType, String studentId, int scriptMark, boolean marked)
		MultipleAttempt info = multipleAttempts.get(0);
		
		String subjectId = info.getSubjectId();
		String year = info.getYear();
		String semester = info.getSemester();
		String examType = info.getExamType();
		String studentId = info.getStudentNumber();
		
		Scriptbook scriptbook = new Scriptbook(subjectId,year,semester,examType,studentId,0,false);
		
		em.studentSubmitsExam(scriptbook);
		
		System.out.println(multipleAttempts);
		for(MultipleAttempt ma: multipleAttempts) {
			System.out.println(ma.getQuestionId());
			//System.out.println(ma.getAttemptedAns());
		}
		System.out.println(shortAttempts);
	}
	
	public void initiateScriptbook(Scriptbook sb) {
		em.addScriptbook(sb);
		System.out.println(sb);
	}


}
