package domain;

import java.util.ArrayList;
import java.util.List;

public class Scriptbook {

	private int totalMark=0;
	
	private boolean marked = false;
	
	private boolean submitted = false;
	
	private Exam exam;
	
	//private List<Attempt> attemptList;
	
	private List<MultipleAttempt> multipleAttemptList;
	
	private List<ShortAttempt> shortAttemptList;
	
	private String studentNumber;
	
	private String subjectId;
	
	private String year;
	
	private String semester;
	
	private String examType;
	
	public Scriptbook(String subjectId, String year, String semester, String examType, String studentId, int scriptMark, boolean marked) {
		
		this.setSubjectId(subjectId);
		this.setYear(year);
		this.setSemester(semester);
		this.setExamType(examType);
		this.studentNumber = studentId;
		this.marked = marked;
		this.totalMark = scriptMark;
		
		multipleAttemptList = new ArrayList<>();
		shortAttemptList = new ArrayList<>();
		
		//attemptList = new ArrayList<>();
				
	}
	
	public void setTotalMark(int mark) {
		
		totalMark = mark;
		setMarked(true);
		
	}
	
	public int getTotalMark()
	{
		return this.totalMark;
	}
	
	public boolean isMarked()
	{
		return this.marked;
	}
	
	public void setMarked(boolean marked) {
		
		this.marked = marked;
	
	}
	
	public void studentSubmitted()
	{
		this.submitted = true;
	}
	
	public Boolean isSubmitted()
	{
		return this.submitted;
	}
	
	public int summedMark() {
		
		int summedMark = 0;
		
		for (MultipleAttempt attempt : multipleAttemptList) {
			if (attempt.isMarked()) {
				
				summedMark += attempt.getMark();
				
			}
		}
		
		for(ShortAttempt attempt: shortAttemptList)
		{
			if (attempt.isMarked()) {
				
				summedMark += attempt.getMark();
				
			}
		}
		
		return summedMark;
		
	}
	
	public String getStudentNumber()
	{
		return this.studentNumber;
	}
	
	public void resetAttemptList()
	{
		this.multipleAttemptList = new ArrayList<>();
		this.shortAttemptList = new ArrayList<>();
		//this.attemptList = new ArrayList<>();
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

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	


	/*public List<Attempt> getAttemptList() {
		return attemptList;
	}*/

public List<MultipleAttempt> getMultipleAttemptList() {
		return multipleAttemptList;
	}

	public void setMultipleAttemptList(List<MultipleAttempt> multipleAttemptList) {
		this.multipleAttemptList = multipleAttemptList;
	}

	public List<ShortAttempt> getShortAttemptList() {
		return shortAttemptList;
	}

	public void setShortAttemptList(List<ShortAttempt> shortAttemptList) {
		this.shortAttemptList = shortAttemptList;
	}

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}

/**	public void setAttemptList(List<Attempt> attemptList) {
		this.attemptList = attemptList;
	}/

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}*/

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	
	
}
