package presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Security.AuthorizationEnforcer;
import datasource.SubjectDataMapper;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/CreateSubject")
public class CreateSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSubject() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Request to create subject");
		
		request.setAttribute("action", "Create Subject");
		
		//Check if authorised
		if (AuthorizationEnforcer.checkAuthorization(request)) {
		
			SubjectDataMapper datamapper = new SubjectDataMapper();
			datamapper.createSubject("ELF101", "A guide to socks of the World");
			System.out.println("Subject created");
			response.sendRedirect("Admin.jsp");
			
		}
		
		else {
			
			System.out.println("You lack appropriate authorisation");
			response.sendRedirect("Admin.jsp");
			
		}
		
	}

}
