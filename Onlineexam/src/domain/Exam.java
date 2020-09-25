package domain;

import java.util.ArrayList;
import java.util.List;

public class Exam {
	
	private List<Question> questionList;
	
	private List<Scriptbook> scriptbookList;
	
	private int totalMarks;
	
	public void addQuestion(Question question) {
		
		questionList.add(question);
		
	}
	
	public Exam(String subjectId, String year, String semester, String examType, String examName, String examCreator, int totalMarks) {
		
		questionList = new ArrayList<>();
		scriptbookList = new ArrayList<>();
		this.setTotalMarks(totalMarks);	
	}
	
	public void addScriptbook(Scriptbook scriptbook) {
	
		scriptbookList.add(scriptbook);
		
	}

	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	
}
