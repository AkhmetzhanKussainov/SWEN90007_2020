package domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import datasource.ExamDataMapper;

public class Exam {
	
	private List<Question> questionList;
	
	private List<Scriptbook> scriptbookList;
	
	private int totalMarks;
	
	private int studentsTaking;
	
	private int subjectID;
	
	private int year;
	
	private String examType;
	
	private String examName;
	
	private String examCreator;
	
	private String semester;
	
	private Date startDate;
	
	private Date endDate;
	
	
	public void addQuestion(Question question) {
		
		questionList.add(question);
		
	}
	
	public Exam(String subjectId, String year, String semester, String examType, String examName, String examCreator, int totalMarks) {
		
		this.questionList = new ArrayList<>();
		this.scriptbookList = new ArrayList<>();
		this.setTotalMarks(totalMarks);	
		this.studentsTaking = 0;
		this.subjectID = Integer.parseInt(subjectId);
		this.year = Integer.parseInt(year);
		this.semester= semester;
		this.examType = examType;
		this.examName = examName;
		this.examCreator = examCreator;
	}
	
	public void addScriptbook(Scriptbook scriptbook) {
	
		scriptbookList.add(scriptbook);
		
	}

	public int getTotalMarks() {
		return this.totalMarks;
	}

	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	
	public Boolean canEdit()
	{
		if(studentsTaking == 0)
			return true;
		else
			return false;
	}
	
	public void studentTakes()
	{
		this.studentsTaking++;
	} 
	
	public int getSubjectID()
	{
		return this.subjectID;
	}
	
	public int getYear()
	{
		return this.year;
	}
	
	public String getSemester()
	{
		return this.semester;
	}
	
	public String getExamType()
	{
		return this.examType;
	}
	
	public String getexamCreator()
	{
		return this.examCreator;
	}
	
	public String getExamName()
	{
		return this.examName;
	}
	
	public List<Question> getQuestionList()
	{
		return this.questionList;
	}
	
	public void saveExam(List<MultipleQuestion> multipleQuestionList, List<ShortQuestion> shortQuestionList)
	{
		
	}

	
	
}
