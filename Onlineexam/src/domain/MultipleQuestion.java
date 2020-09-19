package domain;

import java.sql.*;
import java.util.*;

import datasource.DBConnection;

public class MultipleQuestion {
	
	private String id;
	private String question;
    private String ans1;  
    private String ans2;
    private String ans3;
    private String ans4;
    private String chosen_ans;
    private String mark;

    private static final String findAllMultipleStatement =
			//"SELECT * FROM multiq ORDER BY multiq_id";
    		"SELECT * from multiq";
    private static final String changeMultiMark =
			"UPDATE multiq SET marks = ? where multiq_id = ? ";
    
    public MultipleQuestion(String id, String question, String ans1, String ans2, String ans3, String ans4, String chosen_ans, String mark){
    	
    	this.id = id;
    	this.question = question;
    	this.ans1 = ans1;
    	this.ans2 = ans2;
    	this.ans3 = ans3;
    	this.ans4 = ans4;
    	this.chosen_ans = chosen_ans;
    	this.mark = mark;
    	
    }

    public String getId() {
    	return id;
    }
    
    public String getQuestion() {
    	return question;
    }

	public String getAns1() {
		return ans1;
	}
	
	public String getAns2() {
		return ans2;
	}
	
	public String getAns3() {
		return ans3;
				
	}
	
	public String getAns4() {
		return ans4;
	}
	
	public String getChos() {
		return chosen_ans;
	}
	
	public String getMark() {
		return mark;
	}
	
	public void  setMark(String Mark) {
		this.mark = Mark;
	}
	
	
	public static List<MultipleQuestion> getAllMultiple() {
		
		
		
	    List<MultipleQuestion> multiplequestions = new ArrayList<>();
	    try {
	    	
	    	PreparedStatement stmt = DBConnection.prepare(findAllMultipleStatement);
	    	System.out.println(stmt);
	    	ResultSet rs = stmt.executeQuery();
	    	
			while (rs.next()) {
				
				String id = rs.getString(1);
				String question = rs.getString(2);
				String ans1 = rs.getString(3);
				String ans2 = rs.getString(4);
				String ans3 = rs.getString(5);
				String ans4 = rs.getString(6);
				String chosen_ans = rs.getString(7);
				String mark = rs.getString(8);
				
				
				multiplequestions.add(new MultipleQuestion(id, question, ans1, ans2, ans3, ans4, chosen_ans, mark));
			}
			
			
			
					
		} catch (SQLException e) {
	
		}
	    return multiplequestions;
	}
	
	public static void changeMark(String chosen_id, String changed_mark) {
		
		
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
		
	}
}