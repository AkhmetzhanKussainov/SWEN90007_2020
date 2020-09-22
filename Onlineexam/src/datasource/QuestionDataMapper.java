package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.MultipleQuestion;
import domain.Question.choice;
import domain.ShortQuestion;

public class QuestionDataMapper {
	
	private static final String findAllMultipleStatement =
			//"SELECT * FROM multiq ORDER BY multiq_id";
    		"SELECT * from multipleQuestion";
    private static final String changeMultiMark =
			"UPDATE multipleQuestion SET marks = ? where multiq_id = ? ";
	
    private static final String findAllShortStatement =
			"SELECT * from shortQuestion";
    		//"SELECT * from shortq";
    private static final String changeShortMark =
			"UPDATE shortQuestion SET marks = ? where shortq_id = ? ";
    
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
    
    
	public List<MultipleQuestion> loadMultipleChoiceQuestions() {
	
		List<MultipleQuestion> multipleQuestions = new ArrayList<>();
	    
		try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(findAllMultipleStatement);
	    	System.out.println(stmt);
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
	
	public List<ShortQuestion> loadShortQuestions() {
	    List<ShortQuestion> shortQuestions = new ArrayList<>();
	    try {
	    	PreparedStatement stmt = DBConnection.prepare(findAllShortStatement);
	
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
	
	
	
	/*public void changeMark(String chosen_id, String changed_mark) {
		
		
		try {

			PreparedStatement updateStatement = DBConnection.prepare(changeMultiMark);
			//need to convert id to int type to make SQL working properly cuz I have set id as INT type in setDB.sql XD
			int id = Integer.parseInt( chosen_id );
			updateStatement.setString(1, changed_mark);
			updateStatement.setInt(2, id);
			
			System.out.println(updateStatement);
			updateStatement.execute();
			
		} catch (SQLException e) {
		}
		
	}*/
	
}
