package models;

public class Administrator {
	
	private int ID;
	private String name;
	private Subject subjects[];
	private String status;
	
	public Administrator(int inID, String inName, Subject[] inSubjects, String inStatus)
	{
		this.ID = inID;
		this.name = inName;
		this.subjects = inSubjects;
		this.status = inStatus;
	}
	
	public void createSubject()
	{
		
	}
	
	public void addInstructor()
	{
		
	}
	
	public void removeInstructor()
	{
		
	}
	
	public void removeSubject()
	{
		
	}
	
	public void accessSubjectDetails()
	{
		
	}
}
