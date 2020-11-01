package presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Security.AuthenticationEnforcer;
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
		
		//Give the Session to the authenticator enforcer
		
		//If the authenticator returns true
		if (AuthenticationEnforcer.checkAuthentication(request)) { 
		//
			//Get the Session
			HttpSession session = request.getSession();
			
			String userType = (String) session.getAttribute("usertype");
			
			//Set the login to correct
			request.setAttribute("correctLogin", true);
			
			switch (userType) {
				case "S":
					response.sendRedirect("StudentSubjectDisplay.jsp");
					break;
				case "T":	
					response.sendRedirect("TeacherSubjectDisplay.jsp");
					break;
				case "A":	
					response.sendRedirect("Admin.jsp");
					break;
				case "H":	
					response.sendRedirect("Admin.jsp");
					break;	
				default:
					//Hasn't found a valid user type
					System.out.println("This usertype not implemented by system");
					request.setAttribute("correctLogin", false);
					response.sendRedirect("Login.jsp");
			}
		}
		
		else {
			//Incorrect username or password
			
			System.out.println("Incorrect username or password");
			
			//response.sendRedirect("LoginError.jsp");
			request.setAttribute("correctLogin", false);
			request.getRequestDispatcher("Login.jsp").forward(request, response);
			
		}
		
		
		
	}

}
