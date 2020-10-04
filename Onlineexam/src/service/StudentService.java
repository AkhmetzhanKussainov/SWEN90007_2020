package service;


import datasource.UserDataMapper;
import domain.Student;
import uow.StudentUow;

public class StudentService {
	
	public StudentService() {
		
		UserDataMapper udm = new UserDataMapper();
		
		//multipleQuestions = qdm.loadMultipleChoiceQuestions();
		//shortQuestions = qdm.loadShortQuestions();
		
	}
	
	public void createUow(Student s) {
		StudentUow.registerNew(s);
	}
	
	public void updateUow(Student s) {
		StudentUow.registerDirty(s);
	}
	
	public void deleteUow(Student s) {
		StudentUow.registerDelete(s);
	}
	
	public void commitUow() {
		StudentUow.commitAll();
	}


}
