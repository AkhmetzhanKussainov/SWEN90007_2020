package presentation;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Exceptions.DatabaseException;
import datasource.ExamDataMapper;
import datasource.UserDataMapper;
import domain.Exam;
import domain.Teacher;
import service.AdminService;

/**
 * Servlet implementation class
 */
@WebServlet("/CreateSubjectDetail")
public class CreateSubjectDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSubjectDetail() {
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
		
		/*try {
			System.out.println("hello");
			throw new Exception("dooop");
			
		}
		
		catch (Exception e) {
			System.out.println("confused");
			System.out.println(e.toString());
			
		}*/
		
		try {
		
		String code = request.getParameter("code");
		String title = request.getParameter("title");
		String year = request.getParameter("year");
		String semester = request.getParameter("semester");
		
		System.out.println(code);
		System.out.println(title);
		System.out.println(year);
		System.out.println(semester);
		
		String[] selectedStudentIds = request.getParameterValues("studentCheckbox");
		
		String[] selectedTeacherIds = request.getParameterValues("teacherCheckbox");
		
		/*if (selectedStudentIds!=null) {
			for (String studentId : selectedStudentIds) {
				System.out.println(studentId);
			}
		}
		if (selectedTeacherIds!=null) {
			for (String teacherId : selectedTeacherIds) {
				System.out.println(teacherId);
			}
		}*/
		
		
		
		AdminService am = new AdminService(); 
		
		am.createSubject(code, title, selectedStudentIds, selectedTeacherIds, year, semester);
		
		response.sendRedirect("AdminSubject.jsp");
		
		}
		
			
		//Handle error
		catch( Exception e ) {
			
			request.setAttribute("correctCreation", false);
			request.setAttribute("alertIssue", e.getMessage());
			request.getRequestDispatcher("AdminSubjectDetail.jsp").forward(request, response);
			
		}
		
	}

}