package domain;

public abstract class Question {

	protected int possibleMark;	
	protected String id;
	//protected String examId;
	protected String subjectId;
	protected String year;
	protected String semester;
	protected String examType;
	protected String questionText;
	
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

	public enum choice {
		
		A,
		B,
		C,
		D
		
	}
	
	/*public String getExamId() {
		
		return examId;
		
	}*/
	
	public String getId() {
    	return id;
    }
    
    public String getQuestionText() {
    	return questionText;
    }
	
    public int getPossibleMark() {
    	
    	return possibleMark;
    	
    }

	public void setPossibleMark(int possibleMark) {
		this.possibleMark = possibleMark;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	/*public void setExamId(String examId) {
		this.examId = examId;
	}
*/
    
}
