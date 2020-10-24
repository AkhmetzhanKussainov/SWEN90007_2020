package Security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import datasource.UserDataMapper;
import domain.Student;
import domain.Admin;
import domain.User;
import domain.Teacher;

public class AuthorizationProvider {
	
		//If the action belongs in the permissions (for that class of user) return true
		public static boolean checkPermitted(String userClass, String action) {
			
			PermissionsCollection pc = new PermissionsCollection();
			
			Map<String, List<String>> permissions = pc.getPermissions();
			
			//System.out.println(permissions);
			
			//System.out.println(userClass);
			
			List<String> userPermissions = permissions.get(userClass);
			
			//System.out.println(userPermissions);

			//System.out.println(action);
			
			if (userPermissions.contains(action)) {
				
				return true;
				
			}
			
			return false;
			
		}
	
}
