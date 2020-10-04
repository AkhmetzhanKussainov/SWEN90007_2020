package service;


import datasource.UserDataMapper;
import domain.Teacher;
import uow.TeacherUow;

public class TeacherService {
	
	public TeacherService() {
		
		UserDataMapper udm = new UserDataMapper();
		
		//multipleQuestions = qdm.loadMultipleChoiceQuestions();
		//shortQuestions = qdm.loadShortQuestions();
		
	}
	
	public void createUow(Teacher t) {
		TeacherUow.registerNew(t);
	}
	
	public void updateUow(Teacher t) {
		TeacherUow.registerDirty(t);
	}
	
	public void deleteUow(Teacher t) {
		TeacherUow.registerDelete(t);
	}
	
	public void commitUow() {
		TeacherUow.commitAll();
	}


}
