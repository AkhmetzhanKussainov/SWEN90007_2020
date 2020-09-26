package domain;

public class Attempt {

	private int mark;
	
	private boolean marked = false;
	
	private String questionId;
	
	public void setQuestionId(String questionId) {
		
		this.questionId = questionId;
		
	}
	
	public String getQuestionId() {
		
		return questionId;
		
	}
	
	public int getMark() {
		
		return mark;
		
	}
	
	public void setMark(int mark) {
		
		this.mark = mark;
		marked= true;
		
	}
	
	public boolean isMarked() {
		
		return marked;
		
	}
	
	public int getMark(int mark) {
		
		return mark;
		
	}
	
}
