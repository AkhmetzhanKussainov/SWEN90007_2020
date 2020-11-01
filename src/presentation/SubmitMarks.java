package presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MultipleAttempt;
import domain.Scriptbook;
import domain.ShortAttempt;
import service.ScriptbookService;

/**
 * Servlet implementation class SubmitMarks
 */
@WebServlet("/SubmitMarks")
public class SubmitMarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitMarks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		System.out.println(request.getParameter("short-attempt-qid-"+0));
		System.out.println(request.getParameter("short-attempt-qid-"+1));
		System.out.println(request.getParameter("short-attempt-qid-"+2));
		
		ScriptbookService sbs = new ScriptbookService();
		System.out.println(request.getParameter("multipleQuestionCount"));
		Integer multipleQuestionCount =  Integer.parseInt(request.getParameter("multipleQuestionCount"));
		Integer shortQuestionCount =  Integer.parseInt(request.getParameter("shortQuestionCount"));
		String subjectCode = request.getParameter("subjectCode");
		String year = request.getParameter("year");
		String semester = request.getParameter("semester");
		String examType = request.getParameter("examType");
		String studentId = request.getParameter("studentId");
		int totalMarks = 0;
		
		// public MultipleAttempt(String questionId, String subjectId, String year, String semester, String examType,
		// String studentNumber, String attemptedAns)
		List<MultipleAttempt> multipleAttempts = new ArrayList<>();
		List<ShortAttempt> shortAttempts = new ArrayList<>();
		
		
		for (int i=0; i< multipleQuestionCount; i++) {
			String multipleQuestionId = request.getParameter("multiple-attempt-qid-" + i);
			int multipleMark =  Integer.parseInt(request.getParameter("multiple-attempt-mark-" + i));
			String multipleAttemptAns = request.getParameter("multiple-attempt-ans-" + i);
			
			int multipleVersion = Integer.parseInt(request.getParameter("multiple-attempt-version-" + i));
			
			System.out.println("M Question id");
			System.out.println(multipleQuestionId);
			
			/*public MultipleAttempt(String questionId, String subjectId, String year, String semester, String examType,
					String studentNumber, String attemptedAns)*/
			MultipleAttempt multiple = new MultipleAttempt(multipleQuestionId, subjectCode, year, semester, examType, studentId, multipleAttemptAns);
			multiple.setMark(multipleMark);
			
			multiple.setVersion(multipleVersion);
			
			multipleAttempts.add(multiple);
			totalMarks += multipleMark;
			
		}
		
		for (int j=0; j< shortQuestionCount; j++) {
			String shortQuestionId = request.getParameter("short-attempt-qid-" + j);
			int shortMark = Integer.parseInt(request.getParameter("short-attempt-mark-" + j));
			String shortAttemptAns = request.getParameter("short-attempt-ans-" + j);
			
			int shortVersion = Integer.parseInt(request.getParameter("short-attempt-version-" + j));
			
			
			System.out.println("S Question id");
			System.out.println(shortQuestionId);
			
			ShortAttempt shrt = new ShortAttempt(shortQuestionId, subjectCode, year, semester, examType, studentId, shortAttemptAns);
			shrt.setMark(shortMark);
			shrt.setVersion(shortVersion);
			
			shortAttempts.add(shrt);
			
			totalMarks += shortMark;
			
		}
		
		Scriptbook sb = new Scriptbook(subjectCode, year, semester, examType, studentId, totalMarks, true);
		sb.setMultipleAttemptList(multipleAttempts);
		sb.setShortAttemptList(shortAttempts);
		
		
		boolean noconflict = true;
		
		String multipleconflicts ="";
		
		String shortconflicts ="";
		
		
		//Mark all the short attempts
		for (ShortAttempt shortAttempt: shortAttempts) {
			
			//Get the database version number
			
			
			int shortQuestionIdTemp = Integer.parseInt(shortAttempt.getQuestionId());
			
			int databaseVersion = sbs.getVersionShort(subjectCode, year, semester, examType, shortQuestionIdTemp, studentId);
			
			//Get the sa version number
			int currentVersion = shortAttempt.getVersion();
			
			//Compare the two
			//If equal, update database
			
			System.out.println("short current" + currentVersion);
			System.out.println("short database" + databaseVersion);
			
			if (currentVersion == databaseVersion) {
			
				sbs.updateShort(subjectCode, year, semester, examType, shortQuestionIdTemp, studentId, shortAttempt);
					
				
			}	
			
			else {	
				//Else set noconflict to false and append issue to list
				shortconflicts = shortconflicts + Integer.toString(shortQuestionIdTemp) + ",";
				noconflict = false;
			}	
				
		}
		
		
		//Mark all the multiple attempts
		for (MultipleAttempt multipleAttempt: multipleAttempts) {
			
			//Get the database version number
			int multipleQuestionIdTemp = Integer.parseInt(multipleAttempt.getQuestionId());
			
			int databaseVersion = sbs.getVersionMultiple(subjectCode, year, semester, examType, multipleQuestionIdTemp, studentId);
			
			//Get the sa version number
			int currentVersion = multipleAttempt.getVersion();
			
			//Compare the two
			
			System.out.println("multiple current" + currentVersion);
			System.out.println("multiple database" + databaseVersion);
			
			//If equal, update database
			if (currentVersion == databaseVersion) {
			
				sbs.updateMultiple(subjectCode, year, semester, examType, multipleQuestionIdTemp, studentId, multipleAttempt);
					
				
			}	
			
			else {	
				//Else set noconflict to false and append issue to list
				multipleconflicts = multipleconflicts + Integer.toString(multipleQuestionIdTemp) + ",";
				noconflict = false;
			}	
			
		}
				
		String status = "Success";
		
		if (noconflict){
			
			response.sendRedirect("TeacherExamScriptbooks.jsp?" + "subjectCode=" + subjectCode + "&year=" + year + "&semester=" + semester + "&examType=" + examType);
			
		}
		
		//Send to place where tell them some marks lost
		else {
			
			response.sendRedirect("MarkingConflict.jsp?" + "shortconflicts=" + shortconflicts + "&multipleconflicts=" + multipleconflicts);
						
		}
	}

}
