package domain;

import domain.Question.choice;

public class MultipleAttempt extends Attempt {

	private choice chosenAnswer;
	
	public choice getChoice() {
		return chosenAnswer;
	}
	
}
