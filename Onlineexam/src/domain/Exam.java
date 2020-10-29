package domain;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import datasource.ExamDataMapper;
import datasource.SubjectDataMapper;

public class Exam {
	
	//private List<Question> questionList;
	
	private List<MultipleQuestion> multipleQuestionList;
	
	private List<ShortQuestion> shortQuestionList;
	
	private List<Scriptbook> scriptbookList;
	
	private List<Student> studentList;
	
	private int totalMarks;
	
	private int studentsTaking;
	
	private String subjectID;
	
	private String year;
	
	private String examType;
	
	private String examName;
	
	private String examCreator;
	
	private String semester;
	
	private Date startDate = null;
	
	private Date endDate = null;
	
	private String published = null;
	
	public String getPublished() {
		return published;
	}
	
	public void setPublished(String published) {
		this.published = published;
	}


	private String closed = null;
	
	
	/*public void addQuestion(Question question) {
		
		questionList.add(question);
		
	}*/
	
	public String getExamCreator() {
		return examCreator;
	}

	public void setExamCreator(String examCreator) {
		this.examCreator = examCreator;
	}

	public String getClosed() {
		return closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}

	public void addMultipleQuestion(MultipleQuestion q)
	{
		this.multipleQuestionList.add(q);
	}
	
	public void addShortQuestion(ShortQuestion q)
	{
		this.shortQuestionList.add(q);
	}
	
	public Exam(String subjectId, String year, String semester, String examType, String examName, String examCreator, int totalMarks) {
		
		//this.questionList = new ArrayList<>();
		this.multipleQuestionList = new ArrayList<>();
		this.shortQuestionList = new ArrayList<>();
		this.scriptbookList = new ArrayList<>();
		this.studentList = new ArrayList<>();
		this.setTotalMarks(totalMarks);	
		this.studentsTaking = 0;
		this.subjectID = subjectId;
		this.year = year;
		this.semester= semester;
		this.examType = examType;
		this.examName = examName;
		this.examCreator = examCreator;
	}
	
	//Exam exam = new Exam(subjectId, year, semester, examType, examName, examCreator, totalMarks, published, closed, startDate, endDate);
	
	public Exam(String subjectId, String year, String semester, String examType, String examName, String examCreator, int totalMarks, String published, String closed, String startDate, String endDate)
	{
		this.multipleQuestionList = new ArrayList<>();
		this.shortQuestionList = new ArrayList<>();
		this.scriptbookList = new ArrayList<>();
		this.studentList = new ArrayList<>();
		this.setTotalMarks(totalMarks);	
		this.studentsTaking = 0;
		this.subjectID = subjectId;
		this.year = year;
		this.semester= semester;
		this.examType = examType;
		this.examName = examName;
		this.examCreator = examCreator;
		this.published = published;
		this.closed = closed;
	}
	
	public Exam(String subjectId, String year, String semester, String examType)
	{
		this.multipleQuestionList = new ArrayList<>();
		this.shortQuestionList = new ArrayList<>();
		this.scriptbookList = new ArrayList<>();
		this.studentList = new ArrayList<>();
		this.setTotalMarks(totalMarks);	
		this.studentsTaking = 0;
		this.subjectID = subjectId;
		this.year = year;
		this.semester= semester;
		this.examType = examType;
	}
	
	
	/*public Exam(String subjectId, String year, String semester, String examType, String examName, String examCreator, int totalMarks, Date startDate, Date endDate) {
		
		//this.questionList = new ArrayList<>();
		this.multipleQuestionList = new ArrayList<>();
		this.shortQuestionList = new ArrayList<>();
		this.scriptbookList = new ArrayList<>();
		this.studentList = new ArrayList<>();
		this.setTotalMarks(totalMarks);	
		this.studentsTaking = 0;
		this.subjectID = subjectId;
		this.year = year;
		this.semester= semester;
		this.examType = examType;
		this.examName = examName;
		this.examCreator = examCreator;
		try
		{
			this.startDate = startDate;
			this.endDate = endDate;
		}catch(Exception e)
		{
			this.startDate = null;
			this.endDate = null;
		}

	}*/
	
	public Exam(String subjectId, String year, String semester, String examType, String examName, String examCreator, Integer totalMarks, String published, String closed) {
		
		//this.questionList = new ArrayList<>();
		this.multipleQuestionList = new ArrayList<>();
		this.shortQuestionList = new ArrayList<>();
		this.scriptbookList = new ArrayList<>();
		this.studentList = new ArrayList<>();
		this.setTotalMarks(totalMarks);	
		this.studentsTaking = 0;
		this.subjectID = subjectId;
		this.year = year;
		this.semester= semester;
		this.examType = examType;
		this.examName = examName;
		this.examCreator = examCreator;
		this.published = published;
		this.closed = closed;
	}
	
	
	public void addScriptbookToExam(Scriptbook scriptbook)
	{
		this.scriptbookList.add(scriptbook);
	}
	
	public void addScriptbook(Scriptbook scriptbook) {
		
		ExamDataMapper dm = new ExamDataMapper();
		dm.addScriptbook(scriptbook);
		
	}

	public String getType() {
		return examType;
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
	
	public String getSubjectID()
	{
		return this.subjectID;
	}
	
	public String getSubjectId()
	{
		return this.subjectID;
	}
	
	public String getYear()
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
	
	public List<MultipleQuestion> getMultipleQuestionList()
	{
		return this.multipleQuestionList;
	}
	
	public List<ShortQuestion> getShortQuestionList()
	{
		return this.shortQuestionList;
	}
	
	/*public List<Question> getQuestionList()
	{
		return this.questionList;
	}*/
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void updateQuestions(List<MultipleQuestion> multipleQuestionList, List<ShortQuestion> shortQuestionList)
	{
		ExamDataMapper datamapper = new ExamDataMapper();
		datamapper.updateQuestions(this,multipleQuestionList,shortQuestionList);
		
	}
	
	
	public Boolean hasStudentTakenExam(String studentID)
	{
		ExamDataMapper datamapper = new ExamDataMapper();
		this.scriptbookList = datamapper.loadScriptbooksForExam(subjectID, examType, studentID, studentID);
		for(Scriptbook s:this.scriptbookList)
		{
			if(s.getStudentNumber().equals(studentID))
			{
				if(s.isSubmitted())
				{
					return true;
				}else
				{
					return false;
				}

			}
		}		
		return false;
	}
	
	private void subjectDidNotSubmitScriptbook(Student student)
	{
		Scriptbook scriptbook = new Scriptbook(this.subjectID, this.year, this.semester, this.examType, student.getStudentId(),0,false);
		this.scriptbookList.add(scriptbook);
	}
	
	
	public void close()
	{
		SubjectDataMapper subjectDataMapper = new SubjectDataMapper();
		ExamDataMapper examDataMapper = new ExamDataMapper();
		this.studentList = subjectDataMapper.loadStudentsBySubject(this.subjectID);
		for(Student student: this.studentList)
		{
			Boolean foundStudent = false;
			for(Scriptbook scriptbook: this.scriptbookList)
			{
				if(scriptbook.getStudentNumber() == student.getStudentId())
				{
					foundStudent = true;
					if(!scriptbook.isSubmitted())
					{
						//reset attemptList if the exam is closed
						scriptbook.resetAttemptList();
					}
					break;
				}
			}
			if(!foundStudent)
			{
				this.subjectDidNotSubmitScriptbook(student);
			}				
		}
		examDataMapper.closeExam(this);
		for(Scriptbook scriptbook: this.scriptbookList)
		{
			examDataMapper.addScriptbook(scriptbook);
		}
	}
	
	public List<Scriptbook> getAllScriptbooks()
	{
		ExamDataMapper mapper = new ExamDataMapper();
		this.scriptbookList = mapper.loadScriptbooksForExam(this.subjectID, this.examType, this.year, this.semester);
		return this.scriptbookList;
	}
	
	/*public List<Attempt> getAllAttempts(String studentId)
	{
		List<Attempt> attempts = new ArrayList<>();
		ExamDataMapper mapper = new ExamDataMapper();
		List<Scriptbook> temp = mapper.loadScriptbooksForExam(this.subjectID, this.examType, this.year, this.semester);
		for(Scriptbook s: temp)
		{
			if(s.getStudentNumber().equals(studentId))
			{
				if(s.isSubmitted())
				{
					return mapper.getAllAttempts(this, studentId);
				}else
				{
					return null;
				}
			}
		}
		return attempts;
	}*/
	
	public List<MultipleAttempt> getMultipleAttempts(String studentID)
	{
		List<MultipleAttempt> attempts = new ArrayList<>();
		ExamDataMapper mapper = new ExamDataMapper();
		return mapper.getMultipleAttempt(this, studentID);
		
	}
	
	public List<ShortAttempt> getShortAttempts(String studentID)
	{
		List<ShortAttempt> attempts = new ArrayList<>();
		ExamDataMapper mapper = new ExamDataMapper();
		return mapper.getShortAttempts(this, studentID);
		
	}
	
	public void createNewExam()
	{
		ExamDataMapper dm = new ExamDataMapper();
		dm.addExam(this);
	}
	
	public Boolean checkSubmissionTimeValid()
	{
		if(this.startDate == null || this.endDate == null)
		{
			return true;
		}
		ExamTimeRange etr = new ExamTimeRange();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String startTimeString = dateFormat.format(this.getStartDate());
		String endTimeString = dateFormat.format(this.getEndDate());  
		etr.setStart_time(startTimeString);
		etr.setEnd_time(endTimeString);
		Adjudicator adjudicator = new Adjudicator();
		if(adjudicator.checkExamTime(etr))
		{
			return true;
		}else
		{
			return false;
		}
	}
	
	public void studentTakesExam(String studentId)
	{
		if(studentCanTakeExam(studentId))
		{
			this.studentTakes();
			this.addScriptbook(new Scriptbook(this.subjectID, this.year,this.semester,this.examType,studentId,this.totalMarks, false));
		}
	}
	
	public Boolean studentCanTakeExam(String studentId)
	{
		if(this.hasStudentTakenExam(studentId))
		{
			System.out.println("Student has already taken the exam");
			return false;
		}else
		{
			if(this.checkSubmissionTimeValid())
			{
				return true;
			}else
			{
				System.out.println("Wrong time to take the exam");
				return false;
			}
		}
	}
	
	public void studentSubmitExam(Scriptbook scriptbook)
	{
		ExamDataMapper edm = new ExamDataMapper();
		edm.studentSubmitsExam(scriptbook);
	}
	
	
	//Scriptbook should already contain updated marks
	public void updateMarks(Scriptbook scriptbook)
	{
		ExamDataMapper edm = new ExamDataMapper();
		edm.updateMarks(scriptbook);
	}
	
	
}
