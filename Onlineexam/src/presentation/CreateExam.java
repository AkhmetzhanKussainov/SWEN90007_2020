package presentation;

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
@WebServlet("/CreateExam")
public class CreateExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateExam() {
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
		String year = request.getParameter("year");
		String semester = request.getParameter("semester");
		String examName = request.getParameter("exam-name");
		String examType = request.getParameter("exam-type");
		Integer totalMarks = Integer.parseInt(request.getParameter("total-marks"));
		String subjectId = request.getParameter("subject-id");
		String published = "N";
		String closed = "N";
		String startDate = request.getParameter("start-time");
		String endDate = request.getParameter("end-time");
		HttpSession session = request.getSession();
		
		String user_id = (String)session.getAttribute("userid");
		System.out.println(user_id);
		String examCreator = "";
		
		UserDataMapper um = new UserDataMapper();
		um.loadAllUsers(); 
		
		for (Teacher teacher : um.getAllTeachers()) {
			System.out.println(teacher.getUserId());
			if (teacher.getUserId().equals(user_id)) {
				examCreator = teacher.getTeacherId();
			}
		}
		
		System.out.println(examCreator);
		
		ExamDataMapper em = new ExamDataMapper();
		
		Exam exam = new Exam(subjectId, year, semester, examType, examName, examCreator, totalMarks, published, closed);
		
		System.out.println(exam);
			
		String status = em.publishExam(exam);
//		String status = "Failure";
		System.out.println(status);
		if (status.equals("Success")){
			response.sendRedirect("TeacherExam.jsp" + "?subjectCode=" + subjectId);
			return;
		}
		else {
			response.sendRedirect("TeacherExamDetail.jsp" + "?subjectCode=" + subjectId);
//			response.sendRedirect("asdad.jsp");
			return;
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
