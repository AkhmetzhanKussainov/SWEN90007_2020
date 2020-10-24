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



	public String getSubjectId() {
		return subjectId;
	}



	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public String getSemester() {
		return semester;
	}



	public void setSemester(String semester) {
		this.semester = semester;
	}



	public String getExamType() {
		return examType;
	}



	public void setExamType(String examType) {
		this.examType = examType;
	}



	public String getStudentNumber() {
		return studentNumber;
	}



	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}



	public String getAttemptAns() {
		return attemptAns;
	}



	public void setAttemptAns(String attemptAns) {
		this.attemptAns = attemptAns;
	}



	public void setMarked(boolean marked) {
		this.marked = marked;
	}
	
}
