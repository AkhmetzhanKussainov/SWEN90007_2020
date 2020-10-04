package service;


import datasource.SubjectDataMapper;
import domain.Subject;
import uow.SubjectUow;

public class SubjectService {
	
	public SubjectService() {
		
		SubjectDataMapper sdm = new SubjectDataMapper();
		
		//multipleQuestions = qdm.loadMultipleChoiceQuestions();
		//shortQuestions = qdm.loadShortQuestions();
		
	}
	
	public void createUow(Subject s) {
		SubjectUow.registerNew(s);
	}
	
	public void updateUow(Subject s) {
		SubjectUow.registerDirty(s);
	}
	
	public void deleteUow(Subject s) {
		SubjectUow.registerDelete(s);
	}
	
	public void commitUow() {
		SubjectUow.commitAll();
	}


}
