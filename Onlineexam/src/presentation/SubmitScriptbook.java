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
import domain.ShortAttempt;
import domain.Scriptbook;
import domain.Question.choice;

import service.ScriptbookService;

/**
 * Servlet implementation class SubmitScriptbook
 */
@WebServlet("/SubmitScriptbook")
public class SubmitScriptbook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitScriptbook() {
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
		
		// public MultipleAttempt(String questionId, String subjectId, String year, String semester, String examType,
		// String studentNumber, String attemptedAns)
		List<MultipleAttempt> multipleAttempts = new ArrayList<>();
		List<ShortAttempt> shortAttempts = new ArrayList<>();
		Scriptbook sb = new Scriptbook(subjectCode, year, semester, examType, studentId, 0, false);
		
		
		
		System.out.println("submit scriptbook");
		for (int i=0; i< multipleQuestionCount; i++) {
			String multipleQuestionId = request.getParameter("multiple-question-id-" + i);
			String multipleQuestionAnswer = request.getParameter("multiple-answer-" + i);
			
			MultipleAttempt multiple = new MultipleAttempt(multipleQuestionId, subjectCode, year, semester, examType, studentId, multipleQuestionAnswer);
			
			multipleAttempts.add(multiple);
			
		}
		
		for (int j=0; j< shortQuestionCount; j++) {
			String shortQuestionId = request.getParameter("short-question-id-" + j);
			String shortQuestionAnswer = request.getParameter("short-answer-" + j);
			
			ShortAttempt shrt = new ShortAttempt(shortQuestionId, subjectCode, year, semester, examType, studentId, shortQuestionAnswer);
			
			shortAttempts.add(shrt);
			
		}
		
		sbs.initiateScriptbook(sb);
		sbs.submitScriptbook(multipleAttempts, shortAttempts);
		
		
		String status = "Success";
		
		if (status.equals("Success")){
			response.sendRedirect("Login.jsp");
			return;
		}
		
		
	}

}
