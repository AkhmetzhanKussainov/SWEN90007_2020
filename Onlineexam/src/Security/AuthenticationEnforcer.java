package Security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import datasource.UserDataMapper;
import domain.Student;
import domain.Admin;
import domain.User;
import domain.Teacher;

public class AuthenticationEnforcer {

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
