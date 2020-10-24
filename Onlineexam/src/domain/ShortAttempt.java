package domain;


public class ShortAttempt extends Attempt {
	
	protected Integer totalMarks;
	protected String questionText;
	
	/*public ShortAttempt(String questionId, String subjectId, String year, String semester, String examType, String questionText, int possibleMarks)
	{
		this.questionId = questionId;
		this.subjectId = subjectId;
		this.year = year;
		this.semester = semester;
		this.examType = examType;
		this.questionText = questionText;
		this.possibleMarks = possibleMarks;
	}*/

	public ShortAttempt(String questionId, String subjectId, String year, String semester, String examType,
			String studentNumber, String attemptedAns) {
		super(questionId, subjectId, year, semester, examType, studentNumber, attemptedAns);
		// TODO Auto-generated constructor stub
	}
	
	public ShortAttempt(String questionId, String subjectId, String year, String semester, String examType,
			String studentNumber, String attemptedAns, Integer totalMarks, String questionText) {
		super(questionId, subjectId, year, semester, examType, studentNumber, attemptedAns);
		// TODO Auto-generated constructor stub
		this.questionText = questionText;
		this.totalMarks = totalMarks;
	}
	
	public ShortAttempt(String questionId, String subjectId, String year, String semester, String examType,
			String studentNumber, String attemptedAns, int mark) {
		super(questionId, subjectId, year, semester, examType, studentNumber, attemptedAns);
		// TODO Auto-generated constructor stub
		this.mark = mark;
	}

	private String shortAnswer;
	
	public String getShortAnswer() {
		return shortAnswer;
	}
	
	public void setShortAnswer(String shortAnswer) {
		
		this.shortAnswer = shortAnswer;
		
	}
	
	public String getQuestionText() {
		
		return questionText;
		
	}
	
	public Integer getTotalMarks() {
		
		return totalMarks;
		
	}

}
