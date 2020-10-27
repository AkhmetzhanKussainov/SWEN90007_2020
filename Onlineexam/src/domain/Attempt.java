package domain;

public class Attempt {

	
	protected String questionId;
	protected String subjectId;
	protected String year;
	protected String semester;
	protected String examType;
	protected String studentNumber;
	protected String attemptAns;
	protected int mark;
	protected boolean marked = false;
	
	public Attempt(String questionId, String subjectId, String year, String semester, String examType, String studentNumber, String attemptedAns)
	{
		this.questionId = questionId;
		this.subjectId = subjectId;
		this.year = year;
		this.semester = semester;
		this.examType = examType;
		this.studentNumber = studentNumber;
		this.attemptAns = attemptedAns;
	}
	
	public void setQuestionId(String questionId) {
		
		this.questionId = questionId;
		
	}
	
	public String getQuestionId() {
		
		return questionId;
		
	}
	
	public String getAttemptedAns() {
		
		return attemptAns;
		
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
