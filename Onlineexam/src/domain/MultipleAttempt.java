package domain;

import domain.Question.choice;

public class MultipleAttempt extends Attempt {
	
	protected String correctAns;
	protected Integer totalMarks;
	protected String questionText;
	
	public MultipleAttempt(String questionId, String subjectId, String year, String semester, String examType,
			String studentNumber, String attemptedAns) {
		super(questionId, subjectId, year, semester, examType, studentNumber, attemptedAns);
		// TODO Auto-generated constructor stub
	}
	
	public MultipleAttempt(String questionId, String subjectId, String year, String semester, String examType,
			String studentNumber, String attemptedAns, String correctAns, Integer totalMarks, String questionText) {
		super(questionId, subjectId, year, semester, examType, studentNumber, attemptedAns);
		// TODO Auto-generated constructor stub
		this.questionText = questionText;
		this.totalMarks = totalMarks;
		this.correctAns = correctAns; 
	}
	
	// constructor for marks update
	public MultipleAttempt(String questionId, String subjectId, String year, String semester, String examType,
			String studentNumber, String attemptedAns, int mark) {
		super(questionId, subjectId, year, semester, examType, studentNumber, attemptedAns);
		// TODO Auto-generated constructor stub
		this.mark = mark;
	}

	private choice chosenAnswer;
	
	public choice getChoice() {
		return chosenAnswer;
	}
	
	public void setChoice(choice chosenAnswer) {
		
		this.chosenAnswer = chosenAnswer;
		
	}
	
	public String getQuestionText() {
		
		return questionText;
		
	}
	
	public Integer getTotalMarks() {
		
		return totalMarks;
		
	}
	
	public String getCorrectAns() {
		
		return correctAns;
		
	}
	
}
