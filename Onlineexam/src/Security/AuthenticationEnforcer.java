package Security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import datasource.UserDataMapper;
import domain.Student;
import domain.Admin;
import domain.Headmaster;
import domain.User;
import domain.Teacher;

public class AuthenticationEnforcer {
	
	public static boolean checkLoggedIn(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Object username  = session.getAttribute("username");
		
		if (username == null) {return false;}
		
		return true;
		
	}

	public static boolean checkAuthentication(HttpServletRequest request) {
		
		//Get username and password
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//Get the Session
		HttpSession session = request.getSession();
		
		//Create the datamapper
		UserDataMapper um = new UserDataMapper();
		um.loadAllUsers();
		
		for (Student student : um.getAllStudents()) {
			if (student.getUserName().equals(username)) {
				if (student.getPassword().equals(password)) {
					
					session.setAttribute("username", username);
					session.setAttribute("usertype", "S");
					session.setAttribute("userid", student.getUserId());
					
					return true;
				}
			}
		}
		
		for (Teacher teacher : um.getAllTeachers()) {
			if (teacher.getUserName().equals(username)) {
				if (teacher.getPassword().equals(password)) {
					
					session.setAttribute("username", username);
					session.setAttribute("usertype", "T");
					session.setAttribute("userid", teacher.getUserId());
					
					return true;
				}
			}
		}
		
		for (Admin admin : um.getAllAdmins()) {
			if (admin.getUserName().equals(username)) {
				if (admin.getPassword().equals(password)) {
					
					session.setAttribute("username", username);
					session.setAttribute("usertype", "A");
					session.setAttribute("userid", admin.getUserId());
					
					return true;
				}
			}
		}
		
		//Match for Headmasters
		for (Headmaster headmaster : um.getAllHeadmasters()) {
			
			if (headmaster.getUserName().equals(username)) {
				if (headmaster.getPassword().equals(password)) {
					
					session.setAttribute("username", username);
					session.setAttribute("usertype", "H");
					session.setAttribute("userid", headmaster.getUserId());
					
					return true;
				}
			}
		}
		
		return false;
	}
}
