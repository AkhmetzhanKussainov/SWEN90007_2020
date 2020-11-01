package presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Concurrency.ExclusiveLockManager;
import Security.AuthorizationEnforcer;
import datasource.SubjectDataMapper;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/ClearLocks")
public class ClearLocks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearLocks() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Request to clear locks");
		
		request.setAttribute("action", "Clear Locks");
		
		//Check if authorised
		if (AuthorizationEnforcer.checkAuthorization(request)) {
		
			ExclusiveLockManager em = ExclusiveLockManager.getLock();
			em.releaseAllLocks();
			response.sendRedirect("Admin.jsp");
			
		}
		
		else {
			
			request.setAttribute("correctPermission", false);
			request.setAttribute("alertPermission", "You lack authorisation to clear locks");
			System.out.println("You lack appropriate authorisation");
			request.getRequestDispatcher("Permissions.jsp").forward(request, response);
			
		}
		
	}

}
