package domain;

public abstract class Question {

	protected int possibleMark;	
	protected String id;
	protected String questionText;
	protected String examId;
	
	public enum choice {
		
		A,
		B,
		C,
		D
		
	}
	
	public String getExamId() {
		
		return examId;
		
	}
	
	public String getId() {
    	return id;
    }
    
    public String getQuestionText() {
    	return questionText;
    }
	
    public int getPossibleMark() {
    	
    	return possibleMark;
    	
    }
    
}
