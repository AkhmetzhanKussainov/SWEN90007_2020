package service;

import datasource.ExamDataMapper;
import domain.Exam;

public class ExamService {
	
	ExamDataMapper examDataMapper;
	
	public ExamService()
	{
		 examDataMapper=new ExamDataMapper();		 
		 
	}
	
	public void createExam(Exam newExam)
	{
		examDataMapper.publishExam(newExam);
	}
	
	public void updateExam(Exam updatedExam)
	{
		examDataMapper.changeExam(updatedExam);
	}
	
	

	
	

}
