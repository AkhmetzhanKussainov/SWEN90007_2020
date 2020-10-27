package presentation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MultipleQuestion;
import domain.ShortQuestion;
import service.QuestionService;

/**
 * Servlet implementation class detailPageController
 */
@WebServlet("/questions")
public class detailPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static QuestionService uowService = new QuestionService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public detailPageController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String view = "/MarkingDetail.jsp";
	     
		ServletContext servletContext = getServletContext();
	    RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(view);
	    requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get data according to name tag in submitted form.
				String qtype = request.getParameter("qtype");
				
				// check the form type
				if(qtype.equals("multi")) {
					String id = request.getParameter("id");
					int mark = Integer.parseInt(request.getParameter("mark"));
					MultipleQuestion mq = new MultipleQuestion(id, null, null, null, null, null, null, null,null,null,null, mark, 0);
					uowService.updateUow(mq);
				} else if(qtype.equals("short")) {
					String id = request.getParameter("id");
					int mark = Integer.parseInt(request.getParameter("mark"));
					ShortQuestion sq = new ShortQuestion(id, null, null,null,null,null, mark);
					uowService.updateUow(sq);
				} else if(qtype.equals("commit")) {
					uowService.commitUow();
				}
		doGet(request,response);
		
		
	}

}
