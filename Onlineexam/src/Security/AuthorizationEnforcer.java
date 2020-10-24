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
		
		String action = request.getParameter("action");
		
		String user = request.getParameter("usertype");
		
		return AuthorizationProvider.checkPermitted(user, action);
		
	}
}
