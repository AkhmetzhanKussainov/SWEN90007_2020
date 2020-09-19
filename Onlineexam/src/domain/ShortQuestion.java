package domain;

import java.sql.*;
import java.util.*;

import datasource.DBConnection;

public class ShortQuestion {
	
	private String id;
	private String question;
    private String ans;  
    private String mark;
    
    private static final String findAllShortStatement =
			"SELECT * from shortq ORDER BY id";
    		//"SELECT * from shortq";
    private static final String changeShortMark =
			"UPDATE shortq SET marks = ? where shortq_id = ? ";
    
    public ShortQuestion(String id, String question, String ans, String mark) {
    	this.id = id;
    	this.question = question;
    	this.ans = ans;
    	this.mark= mark;
    }
    
    public String getId() {
    	return id;
    }
    
    public String getQuestion() {
    	return this.question;
    }
    
    public String getAns() {
		return this.ans;
	}
   
	public String getMark() {
		return this.mark;
	}
	
	public void  setMark(String Mark) {
		this.mark = Mark;
	}
	
	public static List<ShortQuestion> getAllShort() {
	    List<ShortQuestion> shortquestions = new ArrayList<>();
	    try {
	    	PreparedStatement stmt = DBConnection.prepare(findAllShortStatement);
	
	    	//shortquestions.add(new ShortQuestion("1", "2", "3", "4"));
	    	
	    	ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				String id = rs.getString(1);
				String question = rs.getString(2);
				String ans = rs.getString(3);
				String mark = rs.getString(4);
				
				shortquestions.add(new ShortQuestion(id, question, ans, mark));
			}
	
		} catch (SQLException e) {
	
		}
	    return shortquestions;
	}
	
	public static void changeMark(String chosen_id, String mark) {
		try {
			PreparedStatement insertStatement = DBConnection.prepare(changeShortMark);
			insertStatement.setString(1, mark);
			//same with the multiple question one.
			int id = Integer.parseInt( chosen_id );
			insertStatement.setInt(2, id);
			insertStatement.execute();
		} catch (SQLException e) {
		}
	}
}
