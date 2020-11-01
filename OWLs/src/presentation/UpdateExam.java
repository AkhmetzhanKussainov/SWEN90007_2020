package presentation;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Concurrency.ExclusiveLockManager;
import datasource.ExamDataMapper;
import datasource.UserDataMapper;
import domain.Exam;
import domain.Teacher;
import service.ExamService;

/**
 * Servlet implementation class CreateExam
 */
@WebServlet("/UpdateExam")
public class UpdateExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateExam() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ExamService es = new ExamService();
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); 
//			Date startDateRaw = format.parse(request.getParameter("start-time"));  
//			Timestamp startTimestamp = new java.sql.Timestamp(startDateRaw.getTime());
//			
//			Date endDateRaw = format.parse(request.getParameter("start-time"));  
//			Timestamp endTimestamp = new java.sql.Timestamp(endDateRaw.getTime());
		
			
			String year = request.getParameter("year");
			String semester = request.getParameter("semester");
			String examName = request.getParameter("exam-name");
			String examType = request.getParameter("exam-type");
			Integer totalMarks = Integer.parseInt(request.getParameter("total-marks"));
			String subjectId = request.getParameter("subject-id");
			//Timestamp startDate = startTimestamp;
			//Timestamp endDate = endTimestamp;
			String examCreator = request.getParameter("exam-creator");
			String published = request.getParameter("published");
			String closed = request.getParameter("closed");
			
			System.out.println(subjectId);
			System.out.println(year);
			System.out.println(semester);
			System.out.println(examName);
			System.out.println(examType);
			System.out.println(totalMarks);
			
			//System.out.println(startDate);
			//System.out.println(endDate);
			
			ExamDataMapper em = new ExamDataMapper();
			
			Exam exam = new Exam(subjectId, year, semester, examType, examName, examCreator, totalMarks, published, closed,null,null);
			
			System.out.println(exam);
			System.out.println(exam.getExamName());
			System.out.println(exam.getYear());
			System.out.println(exam.getExamType());
			System.out.println(exam.getSemester());
			System.out.println(exam.getSubjectId());
			System.out.println(exam.getTotalMarks());
			System.out.println(exam.getPublished());
			System.out.println(exam.getClosed());
			
			
			Boolean realStatus = es.updateExam(exam);
	
			//Start concurrency - release lock
			
			//Create unique key
			String uniqueKey = subjectId+year+semester+examType;
			
			//Get info for access to lock
			HttpSession session = request.getSession();
			String sessionID = (String) session.getAttribute("userid");
			ExclusiveLockManager lock = ExclusiveLockManager.getLock();
			
			//Transaction has ended, so release the lock
			lock.releaseLock(uniqueKey, sessionID, "ExamLock");
			System.out.println("lock released");
			//End concurrency
			
			String status = "Success";
			System.out.println(status);
			if (status.equals("Success")){
				response.sendRedirect("TeacherExam.jsp" + "?subjectCode=" + subjectId);
				return;
			}
			else {
				response.sendRedirect("TeacherExamDetailEdit.jsp" + "?subjectCode=" + subjectId +
						"&year=" + year +
						"&semester" + semester +
						"&examType=" + examType);
	
				return;
			}
			
			
		}	
	catch(Exception e) {
		System.out.println(e.getMessage());
	}

	}
}


