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
			
			
			MultipleAttempt multiple = new MultipleAttempt(multipleQuestionId, subjectCode, year, semester, examType, studentId, multipleAttemptAns, multipleMark);
			
			multipleAttempts.add(multiple);
			totalMarks += multipleMark;
			
		}
		
		for (int j=0; j< shortQuestionCount; j++) {
			String shortQuestionId = request.getParameter("short-attempt-qid-" + j);
			int shortMark = Integer.parseInt(request.getParameter("short-attempt-mark-" + j));
			String shortAttemptAns = request.getParameter("short-attempt-ans-" + j);
			
			ShortAttempt shrt = new ShortAttempt(shortQuestionId, subjectCode, year, semester, examType, studentId, shortAttemptAns, shortMark);
			
			shortAttempts.add(shrt);
			
			totalMarks += shortMark;
			
		}
		
		Scriptbook sb = new Scriptbook(subjectCode, year, semester, examType, studentId, totalMarks, true);
		
		sbs.markScriptbook(sb);
		sbs.submitScriptbookMarks(multipleAttempts, shortAttempts);
		
		
		String status = "Success";
		
		if (status.equals("Success")){
			response.sendRedirect("TeacherExamScriptbooks.jsp?" + "subjectCode=" + subjectCode + "&year=" + year + "&semester=" + semester + "&examType=" + examType);
			return;
		}
	}

}
