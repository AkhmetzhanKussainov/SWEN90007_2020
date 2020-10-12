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

import datasource.ExamDataMapper;
import datasource.UserDataMapper;
import domain.Exam;
import domain.Teacher;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); 
			Date startDateRaw = format.parse(request.getParameter("start-time"));  
			Timestamp startTimestamp = new java.sql.Timestamp(startDateRaw.getTime());
			
			Date endDateRaw = format.parse(request.getParameter("start-time"));  
			Timestamp endTimestamp = new java.sql.Timestamp(endDateRaw.getTime());
		
			
			String year = request.getParameter("year");
			String semester = request.getParameter("semester");
			String examName = request.getParameter("exam-name");
			String examType = request.getParameter("exam-type");
			Integer totalMarks = Integer.parseInt(request.getParameter("total-marks"));
			String subjectId = request.getParameter("subject-id");
			Timestamp startDate = startTimestamp;
			Timestamp endDate = endTimestamp;
			String examCreator = request.getParameter("exam-creator");
			String published = request.getParameter("published");
			String closed = request.getParameter("closed");
			
			System.out.println(startDate);
			System.out.println(endDate);
			
			ExamDataMapper em = new ExamDataMapper();
			
			Exam exam = new Exam(subjectId, year, semester, examType, examName, examCreator, totalMarks, published, closed, startDate, endDate);
			
			System.out.println(exam);
			System.out.println(exam.getExamName());
			System.out.println(exam.getTotalMarks());
			System.out.println(exam.getPublished());
			System.out.println(exam.getClosed());
			System.out.println(exam.getStartDateString());
			System.out.println(exam.getEndDateString());
			
			
				
	//		String status = em.changeExam(exam);
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
	//			response.sendRedirect("asdad.jsp");
				return;
			}
			
			
		}
	catch(Exception e) {
		System.out.println(e.getMessage());
	}

}
}






////protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////	// TODO Auto-generated method stub
////	String username = request.getParameter("username");
////	String password = request.getParameter("password");
////	String usertype = "S";
////	
////	UserDataMapper um = new UserDataMapper();
////	um.loadAllUsers(); 
////	for (Student student : um.getAllStudents()) {
////		if (student.getUserName().equals(username)) {
////			if (student.getPassword().equals(password)) {
////				HttpSession session = request.getSession();
////				session.setAttribute("username", username);
////				session.setAttribute("usertype", "S");
////				session.setAttribute("userid", student.getUserId());
////				response.sendRedirect("StudentSubjectDisplay.jsp");
////				return;
////			}
////		}
////	}
