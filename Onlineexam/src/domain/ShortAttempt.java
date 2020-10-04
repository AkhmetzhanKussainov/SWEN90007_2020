package domain;


public class ShortAttempt extends Attempt {
	
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

	private String shortAnswer;
	
	public String getShortAnswer() {
		return shortAnswer;
	}
	
	public void setShortAnswer(String shortAnswer) {
		
		this.shortAnswer = shortAnswer;
		
	}

}
