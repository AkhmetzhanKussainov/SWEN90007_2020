package domain;

import java.util.ArrayList;
import java.util.List;

public class Exam {
	
	private List<Question> questionList;
	
	private List<Scriptbook> scriptbookList;
	
	private int totalMarks;
	
	private String subjectId;
	private String year;
	private String semester;
	private String examType;
	private String examName;
	private String examCreator;
	private String examId;
	
	
	
	public void addQuestion(Question question) {
		
		questionList.add(question);
		
	}
	
	public Exam(String examId, String subjectId, String year, String semester, String examType, String examName, String examCreator, int totalMarks) {
		
		questionList = new ArrayList<>();
		scriptbookList = new ArrayList<>();
		this.subjectId = subjectId;
		this.setTotalMarks(totalMarks);	
		this.year = year;
		this.semester = semester;
		this.examType = examType;
		this.examName = examName;
		this.examCreator = examCreator;
		this.examId = examId;
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
	
	public String getSubjectId() {
		return this.subjectId;
	}
	
	public String getYear() {
		return this.year;
	}
	
	public String getSemester() {
		return this.semester;
	}
	
	public String getExamType() {
		return this.examType;
	}
	
	public String getExamCreator() {
		return this.examCreator;
	}
	
	public String getExamName() {
		return this.examName;
	}
	
	public String getExamId() {
		return this.examId;
	}
	
	
}
