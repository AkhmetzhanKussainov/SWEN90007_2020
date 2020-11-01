package presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Concurrency.ExclusiveLockManager;
import service.ExamService;
import service.QuestionService;
import domain.Exam;
import domain.MultipleQuestion;
import domain.Question.choice;

/**
 * Servlet implementation class DeleteQuestion
 */
@WebServlet("/DeleteExam")
public class DeleteExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteExam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("Delete exam called");
		String subjectId = request.getParameter("subjectId");
		String year = request.getParameter("year");
		String semester = request.getParameter("semester");
		String examType = request.getParameter("examType");
		System.out.println(subjectId);
		System.out.println(year);
		System.out.println(semester);
		System.out.println(examType);
		ExamService es = new ExamService();
		
		try {
			es.deleteExam(subjectId, year, semester, examType);
		}
		catch (Exception e) {
			
		}
		
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
		
	}

}
