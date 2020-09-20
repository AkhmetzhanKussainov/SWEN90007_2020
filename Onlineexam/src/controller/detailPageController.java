	package controller;

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

/**
 * Servlet implementation class detailPageController
 */
@WebServlet("/questions/")
public class detailPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		String id = request.getParameter("id");
		String mark = request.getParameter("mark");

		// check the form type
		if(qtype.equals("multi")) {
			
			MultipleQuestion.changeMark(id, mark);
			
			
		} else if(qtype.equals("short")) {
			ShortQuestion.changeMark(id, mark);
		}
		
		doGet(request,response);
		
		
	}

}
