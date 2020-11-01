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
@WebServlet("/DoneEditingExamQuestions")
public class DoneEditingExamQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoneEditingExamQuestions() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			String year = request.getParameter("year");
			String semester = request.getParameter("semester");
			String examType = request.getParameter("exam-type");
			String subjectCode = request.getParameter("subjectCode");
			
			System.out.println(year);
			System.out.println(subjectCode);
			
			//Start concurrency - release lock
			
			//Create unique key
			String uniqueKey = subjectCode+year+semester+examType;
			
			//Get info for access to lock
			HttpSession session = request.getSession();
			String sessionID = (String) session.getAttribute("userid");
			ExclusiveLockManager lock = ExclusiveLockManager.getLock();
			
			//Transaction has ended, so release the lock
			lock.releaseLock(uniqueKey, sessionID, "ExamLock");
			System.out.println("lock released");
			//End concurrency
			
			response.sendRedirect("TeacherExam.jsp" + "?subjectCode=" + subjectCode);

	}
}


