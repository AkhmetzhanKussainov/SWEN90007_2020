package presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datasource.ExamDataMapper;
import domain.Exam;
import domain.MultipleQuestion;
import domain.ShortQuestion;
import service.QuestionService;

/**
 * Servlet implementation class AddShortQuestion
 */
@WebServlet("/AddShortQuestion")
public class AddShortQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShortQuestion() {
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
		
		String subjectCode = request.getParameter("subjectCode");
		String year = request.getParameter("year");
		String semester = request.getParameter("semester");
		String examType = request.getParameter("examType");
		String questionText = request.getParameter("question-text");
		String baseURL = request.getParameter("url");
		int marks = Integer.parseInt(request.getParameter("possible-mark"));
		
		//MultipleQuestion(String id, String subjectCode, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int answerNumber)
		//ExamDataMapper em = new ExamDataMapper();
		ShortQuestion ms = new ShortQuestion("99", subjectCode, year, semester, examType, questionText, marks);
		Exam exam = new Exam(subjectCode,year,semester,examType,null,null,0);
		QuestionService es = new QuestionService();
		es.addShortQuestion(exam, ms);
		
		
		System.out.println("--");
		System.out.println(ms);
		System.out.println(ms.getQuestionText());
		System.out.println(ms.getPossibleMark());
		System.out.println("--");
		
		String status = "Success";
		
		if (status.equals("Success")){
			response.sendRedirect(baseURL);
			return;
		}
	}

}
