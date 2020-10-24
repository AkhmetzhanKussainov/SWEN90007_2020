package Security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import datasource.UserDataMapper;
import domain.Student;
import domain.Admin;
import domain.User;
import domain.Teacher;

public class AuthorizationEnforcer {

	public static boolean checkAuthorization(HttpServletRequest request) {
		
		System.out.println("Checking Authorization");
		
		HttpSession session = request.getSession();
		
		//String action = request.getParameter("action");
		
		//Replace this code later
		String action = "Create Subject";
		
		System.out.println(action);
		
		String user = (String) session.getAttribute("usertype");
		
		return AuthorizationProvider.checkPermitted(user, action);
		
	}
}
