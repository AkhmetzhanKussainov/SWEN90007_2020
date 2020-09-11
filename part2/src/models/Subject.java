package models;

public class Subject {
	
	private int ID;
	private String name;
	private String status;
	private Instructor instructors[];
	private Exam exams[];
	
	public Subject(int ID, String name, String status, Instructor[] instructors, Exam[] exams)
	{
		this.ID = ID;
		this.name = name;
		this.status = status;
		this.instructors = instructors;
		this.exams = exams;
	}
	
	
	private void sendNotification()
	{
		
	}
	
}
