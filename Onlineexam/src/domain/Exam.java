package domain;

import java.util.ArrayList;
import java.util.List;

public class Exam {
	
	private String subjectId;
	private String semester;
	private String year;
	private String examType;
	private String examName;
	private String examCreator;
	private String published;
	
	
	
	private List<Question> questionList;
	
	private List<Scriptbook> scriptbookList;
	
	private int totalMarks;
	
	public void addQuestion(Question question) {
		
		questionList.add(question);
		
	}
	
	public Exam(String subjectId, String year, String semester, String examType, String examName, String examCreator, int totalMarks) {
		
		this.setSubjectId(subjectId);
		this.setYear(year);
		this.setSemester(semester);
		this.setExamType(examType);
		this.setExamName(examName);
		this.setExamCreator(examCreator);
		this.published = "N";
		questionList = new ArrayList<>();
		scriptbookList = new ArrayList<>();
		this.setTotalMarks(totalMarks);	
	}
	
	public void addScriptbook(Scriptbook scriptbook) {
	
		scriptbookList.add(scriptbook);
		
	}
	

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExamCreator() {
		return examCreator;
	}

	public void setExamCreator(String examCreator) {
		this.examCreator = examCreator;
	}

	
	
	public int getTotalMarks() {
		return totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	

	
}
