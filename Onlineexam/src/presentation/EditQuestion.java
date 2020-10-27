package presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MultipleQuestion;
import domain.Question.choice;
import service.QuestionService;

/**
 * Servlet implementation class EditQuestion
 */
@WebServlet("/EditQuestion")
public class EditQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditQuestion() {
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
		
		QuestionService qs = new QuestionService();
		String questionId = request.getParameter("question-id");
		String subjectCode = request.getParameter("subjectCode");
		String year = request.getParameter("year");
		String semester = request.getParameter("semester");
		String examType = request.getParameter("examType");
		String questionText = request.getParameter("question-text");
		String ansA = request.getParameter("choice-a");
		String ansB = request.getParameter("choice-b");
		String ansC = request.getParameter("choice-c");
		String ansD = request.getParameter("choice-d");
		String baseURL = request.getParameter("url");
		int marks = Integer.parseInt(request.getParameter("possible-mark"));
		String answer = request.getParameter("answer");
		
		//MultipleQuestion(String id, String subjectCode, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int answerNumber)
		
		MultipleQuestion ms = new MultipleQuestion(questionId, subjectCode, year, semester, examType, questionText, ansA, ansB, ansC, ansD, answer, marks, 2);
		
		System.out.println("--");
		System.out.println(ms);
		System.out.println(ms.getId());
		System.out.println(ms.getQuestionText());
		System.out.println(ms.getPossibleMark());
		System.out.println(ms.getCorrectAnswer());
		System.out.println("--");
		
		Boolean realStatus = qs.updateMultipleQuestion(ms);
		
		String status = "Success";
		
		if (status.equals("Success")){
			response.sendRedirect(baseURL);
			return;
		}
		
	}

}
