package presentation;

import domain.Question.choice;
import service.ExamService;
import service.QuestionService;
import domain.Exam;
import domain.MultipleQuestion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datasource.ExamDataMapper;

/**
 * Servlet implementation class AddQuestion
 */
@WebServlet("/AddQuestion")
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		String ansA = request.getParameter("choice-a");
		String ansB = request.getParameter("choice-b");
		String ansC = request.getParameter("choice-c");
		String ansD = request.getParameter("choice-d");
		String baseURL = request.getParameter("url");
		int marks = Integer.parseInt(request.getParameter("possible-mark"));
		String answer = request.getParameter("answer");
		
		QuestionService es = new QuestionService();
		//choice answer = choice.valueOf(request.getParameter("answer"));
		//int answer = 2;
		
		//MultipleQuestion(String id, String subjectCode, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC, String ansD, choice correctAnswer, int possibleMark, int answerNumber)
		// MultipleQuestion(String id, String subjectId, String year, String semester, String examType, String questionText, String ansA, String ansB, String ansC,String ansD, String correctAnswer,int possibleMark, int answerNumber)
		MultipleQuestion ms = new MultipleQuestion("99", subjectCode, year, semester, examType, questionText, ansA, ansB, ansC, ansD, answer, marks, 2);
		Exam exam = new Exam(subjectCode,year,semester,examType,null,null,0);
		es.addMultipleQuestion(exam, ms);
		
		System.out.println("--");
		System.out.println(ms);
		System.out.println(ms.getQuestionText());
		System.out.println(ms.getPossibleMark());
		System.out.println(ms.getCorrectAnswer());
		System.out.println("--");
		
		String status = "Success";
		
		if (status.equals("Success")){
			response.sendRedirect(baseURL);
			return;
		}
	}

}
