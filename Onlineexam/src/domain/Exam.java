package domain;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import datasource.ExamDataMapper;
import datasource.SubjectDataMapper;

public class Exam {

	private List<Question> questionList;

	private List<Scriptbook> scriptbookList;

	private List<Student> studentList;

	private int totalMarks;

	private String subjectId;
	private String examId;

	private int studentsTaking;

	private String subjectID;

	private String year;

	private String examType;

	private String examName;

	private String examCreator;

	private String semester;

	private Date startDate = null;

	private Date endDate = null;

	public void addQuestion(Question question) {

		questionList.add(question);

	}

	public Exam(String examId, String subjectId, String year, String semester, String examType, String examName,
			String examCreator, int totalMarks) {

		this.subjectId = subjectId;
		this.examId = examId;
		this.questionList = new ArrayList<>();
		this.scriptbookList = new ArrayList<>();
		this.studentList = new ArrayList<>();
		this.setTotalMarks(totalMarks);
		this.studentsTaking = 0;
		this.subjectID = subjectId;
		this.year = year;
		this.semester = semester;
		this.examType = examType;
		this.examName = examName;
		this.examCreator = examCreator;
	}

	public Exam(String subjectId, String year, String semester, String examType, String examName, String examCreator,
			int totalMarks, Date startDate, Date endDate) {

		this.questionList = new ArrayList<>();
		this.scriptbookList = new ArrayList<>();
		this.studentList = new ArrayList<>();
		this.setTotalMarks(totalMarks);
		this.studentsTaking = 0;
		this.subjectID = subjectId;
		this.year = year;
		this.semester = semester;
		this.examType = examType;
		this.examName = examName;
		this.examCreator = examCreator;
		this.startDate = startDate;
		this.endDate = endDate;
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

	public String getSubjectId() {
		return this.subjectId;
	}

	public String getExamCreator() {
		return this.examCreator;
	}

	public String getExamId() {
		return this.examId;
	}

	public Boolean canEdit() {
		if (studentsTaking == 0)
			return true;
		else
			return false;
	}

	public void studentTakes() {
		this.studentsTaking++;
	}

	public String getSubjectID() {
		return this.subjectID;
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

	public String getexamCreator() {
		return this.examCreator;
	}

	public String getExamName() {
		return this.examName;
	}

	public List<Question> getQuestionList() {
		return this.questionList;
	}

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

	public void updateQuestions(List<MultipleQuestion> multipleQuestionList, List<ShortQuestion> shortQuestionList) {
		ExamDataMapper datamapper = new ExamDataMapper();
		datamapper.updateQuestions(this, multipleQuestionList, shortQuestionList);

	}

	public Boolean hasStudentTakenExam(String studentID) {
		ExamDataMapper datamapper = new ExamDataMapper();
		this.scriptbookList = datamapper.loadScriptbooksForExam(subjectID, examType, studentID, studentID);
		for (Scriptbook s : this.scriptbookList) {
			if (s.getStudentNumber().equals(studentID)) {
				return true;
			}
		}
		return false;
	}

	private void subjectDidNotSubmitScriptbook(Student student) {
		Scriptbook scriptbook = new Scriptbook(this.subjectID, this.year, this.semester, this.examType,
				student.getStudentId(), 0, false);
		this.scriptbookList.add(scriptbook);
	}

	public void close() {
		SubjectDataMapper subjectDataMapper = new SubjectDataMapper();
		ExamDataMapper examDataMapper = new ExamDataMapper();
		this.studentList = subjectDataMapper.loadStudentsBySubject(this.subjectID);
		for (Student student : this.studentList) {
			Boolean foundStudent = false;
			for (Scriptbook scriptbook : this.scriptbookList) {
				if (scriptbook.getStudentNumber() == student.getStudentId()) {
					foundStudent = true;
					if (!scriptbook.isSubmitted()) {
						// reset attemptList if the exam is closed
						scriptbook.resetAttemptList();
					}
					break;
				}
			}
			if (!foundStudent) {
				this.subjectDidNotSubmitScriptbook(student);
			}
		}
		examDataMapper.closeExam(this);
		for (Scriptbook scriptbook : this.scriptbookList) {
			examDataMapper.addScriptbook(scriptbook);
		}
	}

	public List<Scriptbook> getAllScriptbooks() {
		ExamDataMapper mapper = new ExamDataMapper();
		this.scriptbookList = mapper.loadScriptbooksForExam(this.subjectID, this.examType, this.year, this.semester);
		return this.scriptbookList;
	}

	public List<Attempt> getAllAttempts(String studentId) {
		List<Attempt> attempts = new ArrayList<>();
		ExamDataMapper mapper = new ExamDataMapper();
		List<Scriptbook> temp = mapper.loadScriptbooksForExam(this.subjectID, this.examType, this.year, this.semester);
		for (Scriptbook s : temp) {
			if (s.getStudentNumber().equals(studentId)) {
				if (s.isSubmitted()) {
					return mapper.getAllAttempts(this, studentId);
				} else {
					return null;
				}
			}
		}
		return attempts;
	}

	public void createNewExam() {
		ExamDataMapper dm = new ExamDataMapper();
		dm.publishExam(this);
	}

	public Boolean checkSubmissionTimeValid() {
		ExamTimeRange etr = new ExamTimeRange();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTimeString = dateFormat.format(this.getStartDate());
		String endTimeString = dateFormat.format(this.getEndDate());
		etr.setStart_time(startTimeString);
		etr.setEnd_time(endTimeString);
		Adjudicator adjudicator = new Adjudicator();
		if (adjudicator.checkExamTime(etr)) {
			return true;
		} else {
			return false;
		}
	}

}
