package presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exceptions.AlreadySubmittedException;
import domain.MultipleAttempt;
import domain.ShortAttempt;
import domain.Scriptbook;
import domain.Question.choice;
import service.ExamService;
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
		
		//Check if the exam has been closed
		ExamService es = new ExamService();
		boolean closed = es.checkClosed(subjectCode, year, semester, examType);
		boolean submitted = false;
		
		//Submit exam with information
		if (closed == false) {
			
			submitted = true;
		
			System.out.println("The exam is open");
			for (int i=0; i< multipleQuestionCount; i++) {
				
				System.out.println("M ans: "+request.getParameter("multiple-answer-" + i));
				
				String multipleQuestionId = request.getParameter("multiple-question-id-" + i);
				String multipleQuestionAnswer = request.getParameter("multiple-answer-" + i);
				
				MultipleAttempt multiple = new MultipleAttempt(multipleQuestionId, subjectCode, year, semester, examType, studentId, multipleQuestionAnswer);
				
				multiple.setChoiceAsString(multipleQuestionAnswer);
				
				multipleAttempts.add(multiple);
				
			}
			
			for (int j=0; j< shortQuestionCount; j++) {
				
				System.out.println("S ans: "+request.getParameter("short-answer-" + j));
				
				String shortQuestionId = request.getParameter("short-question-id-" + j);
				String shortQuestionAnswer = request.getParameter("short-answer-" + j);
				
				ShortAttempt shrt = new ShortAttempt(shortQuestionId, subjectCode, year, semester, examType, studentId, shortQuestionAnswer);
				
				//Because the code is unreliable
				shrt.setShortAnswer(shortQuestionAnswer);
				
				shortAttempts.add(shrt);
				
			}
		
		}
	
		else //Submit exam with 'not answered' or 'n'
			 {
				
				System.out.println("The exam is closed");
				for (int i=0; i< multipleQuestionCount; i++) {
					
					
					String multipleQuestionId = request.getParameter("multiple-question-id-" + i);
					String multipleQuestionAnswer = "N";
					
					MultipleAttempt multiple = new MultipleAttempt(multipleQuestionId, subjectCode, year, semester, examType, studentId, multipleQuestionAnswer);
					
					multiple.setChoiceAsString(multipleQuestionAnswer);
					
					multipleAttempts.add(multiple);
					
				}
				
				for (int j=0; j< shortQuestionCount; j++) {
					
					
					String shortQuestionId = request.getParameter("short-question-id-" + j);
					String shortQuestionAnswer = "Unanswered";
					
					ShortAttempt shrt = new ShortAttempt(shortQuestionId, subjectCode, year, semester, examType, studentId, shortQuestionAnswer);
					
					//Because the code is unreliable
					shrt.setShortAnswer(shortQuestionAnswer);
					
					shortAttempts.add(shrt);
					
				}
			
			}
		
		
		sb.setShortAttemptList(shortAttempts);
		sb.setMultipleAttemptList(multipleAttempts);
		sb.setSubmitted(submitted);
		
		System.out.println("Is it submitted? " + submitted);

		System.out.println("Is it set as submitted? " + sb.isSubmitted());
		
		Boolean succeeded = false;
		
		//sbs.initiateScriptbook(sb);
		try {
		 
		 sbs.submitScriptbook(sb);
			
		 succeeded = true;
		
		}
		
		catch (AlreadySubmittedException e) {
			
			System.out.println(e.getMessage());
			
		}
		
		catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		}
		
		//Redirect to display subject
		if (submitted){
			
			response.sendRedirect("SubjectDisplay.jsp");
			return;
		}
		
		//Did not submit due to closed, so redirect
		response.sendRedirect("Closed.jsp");
		
	}

}
