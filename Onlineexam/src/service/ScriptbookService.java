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
	
	public ScriptbookService() {
		
	}

	public void submitScriptbook(List<MultipleAttempt> multipleAttempts, List<ShortAttempt> shortAttempts) {
		System.out.println(multipleAttempts);
		for(MultipleAttempt ma: multipleAttempts) {
			System.out.println(ma.getQuestionId());
			System.out.println(ma.getAttemptedAns());
		}
		System.out.println(shortAttempts);
	}
	
	public void initiateScriptbook(Scriptbook sb) {
		System.out.println(sb);
	}


}
