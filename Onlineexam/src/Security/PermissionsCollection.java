package Security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionsCollection {
	

	//This acts as permissions collection
	//This is how Java does dictionaries
	private Map<String, List<String>> permissions = new HashMap<String, List<String>>();
	
	
	public PermissionsCollection() {
		
		
		//Admin permisions			
		permissions.put("A" , Arrays.asList("Create Subject", "View Subjects"));
		
		//Headmaster permissions
		permissions.put("H" , Arrays.asList("View Subjects"));
		
	}
	
	public Map<String, List<String>> getPermissions() {
		
		return permissions;
		
	}	

}
