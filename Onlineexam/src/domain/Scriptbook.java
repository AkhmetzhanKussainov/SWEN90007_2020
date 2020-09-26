package domain;

import java.util.ArrayList;
import java.util.List;

public class Scriptbook {

	private int totalMark;
	
	private boolean marked = false;
	
	private Exam exam;
	
	private List<Attempt> attemptList;
	
	public Scriptbook(String subjectId, String year, String semester, String examType, String studentId, int scriptMark, boolean marked) {
		
		setMarked(marked);
		setTotalMark(scriptMark);
		
		attemptList = new ArrayList<>();
				
	}
	
	public void setTotalMark(int mark) {
		
		totalMark = mark;
		setMarked(true);
		
	}
	
	public void setMarked(boolean marked) {
		
		this.marked = marked;
	
	}
	
	public int summedMark() {
		
		int summedMark = 0;
		
		for (Attempt attempt : attemptList) {
			if (attempt.isMarked()) {
				
				summedMark += attempt.getMark();
				
			}
		}	
		
		return summedMark;
		
	}
	
	
}
