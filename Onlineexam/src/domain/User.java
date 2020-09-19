package domain;



abstract class User {
	
	enum houses {
		
		
		Slytherin,
		Hufflepuff,
		Gryffindor,
		Ravenclaw
		
	}
	
	protected String userId;
	protected String username;
	protected String password;
	
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
