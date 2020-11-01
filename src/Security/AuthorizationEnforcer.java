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
		
		//Verify logged in
		if (!AuthenticationEnforcer.checkLoggedIn(request)) {
			
			return false;
			
		}
		
		String action = (String) request.getAttribute("action");
		
		System.out.println("Action to be authorised: " + action);
		
		String user = (String) session.getAttribute("usertype");
		
		return AuthorizationProvider.checkPermitted(user, action);
		
	}
}
