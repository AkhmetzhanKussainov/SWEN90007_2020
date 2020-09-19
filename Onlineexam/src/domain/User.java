package domain;

abstract class User {
	
	private String userId;
	private String username;
	private String password;
	
	public String getUsername() {
		
		return username;
		
	}
	
	public boolean match(String password) {
		
		if (this.password.equals(password)) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	public String getPassword() {
		
		
		return password;
		
	}

}
