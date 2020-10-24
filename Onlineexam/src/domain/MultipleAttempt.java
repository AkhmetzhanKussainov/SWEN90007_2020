package domain;

import domain.Question.choice;

public class MultipleAttempt extends Attempt {
	
	

	public MultipleAttempt(String questionId, String subjectId, String year, String semester, String examType,
			String studentNumber, String attemptedAns) {
		super(questionId, subjectId, year, semester, examType, studentNumber, attemptedAns);
		// TODO Auto-generated constructor stub
	}

	private choice chosenAnswer;
	
	public choice getChoice() {
		return chosenAnswer;
	}
	
	public void setChoice(choice chosenAnswer) {
		
		this.chosenAnswer = chosenAnswer;
		
	}
	
}
