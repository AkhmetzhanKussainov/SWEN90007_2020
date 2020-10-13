package presentation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import datasource.UserDataMapper;
import domain.Student;
import domain.Admin;
import domain.User;
import domain.Teacher;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = "S";
		
		UserDataMapper um = new UserDataMapper();
		um.loadAllUsers(); 
		for (Student student : um.getAllStudents()) {
			if (student.getUserName().equals(username)) {
				if (student.getPassword().equals(password)) {
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					session.setAttribute("usertype", "S");
					session.setAttribute("userid", student.getUserId());
					response.sendRedirect("StudentSubjectDisplay.jsp");
					return;
				}
			}
		}
		
		for (Teacher teacher : um.getAllTeachers()) {
			if (teacher.getUserName().equals(username)) {
				if (teacher.getPassword().equals(password)) {
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					session.setAttribute("usertype", "T");
					session.setAttribute("userid", teacher.getUserId());
					response.sendRedirect("TeacherSubjectDisplay.jsp");
					return;
				}
			}
		}
		
//		for (Admin admin : um.getAllAdmins()) {
//			if (admin.getUserName().equals(username)) {
//				if (admin.getPassword().equals(password)) {
//					HttpSession session = request.getSession();
//					session.setAttribute("username", username);
//					session.setAttribute("usertype", "A");
//					session.setAttribute("userid", admin.getUserId());
//					response.sendRedirect("TeacherSubjectDisplay.jsp");
//					return;
//				}
//			}
//		}
		
		response.sendRedirect("Login.jsp");
		
	}

}
