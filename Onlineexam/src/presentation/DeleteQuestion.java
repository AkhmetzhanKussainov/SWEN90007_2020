package presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.QuestionService;
import domain.Exam;
import domain.MultipleQuestion;
import domain.Question.choice;

/**
 * Servlet implementation class DeleteQuestion
 */
@WebServlet("/DeleteQuestion")
public class DeleteQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteQuestion() {
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
		String baseURL = request.getParameter("url");
		
		//MultipleQuestion(String id, String subjectCode, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int answerNumber)
		
		System.out.println("--");
		System.out.println("delete " + questionId);
		System.out.println("--");
		//public Exam(String subjectId, String year, String semester, String examType)
		Exam exam = new Exam(subjectCode,year,semester,examType);
		Boolean realStatus = qs.deleteMultipleQuestion(exam,questionId);
		
		String status = "Success";
		
		if (status.equals("Success")){
			response.sendRedirect(baseURL);
			return;
		}
		
	}

}
