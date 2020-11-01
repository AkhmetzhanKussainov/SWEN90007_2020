package presentation;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datasource.ExamDataMapper;
import datasource.UserDataMapper;
import domain.Exam;
import domain.Teacher;
import service.ExamService;
import service.ScriptbookService;

/**
 * Servlet implementation class CreateExam
 */
@WebServlet("/UpdateScriptBookTotalMark")
public class UpdateScriptBookTotalMark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateScriptBookTotalMark() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ScriptbookService sbs = new ScriptbookService();
			
			String subjectId = request.getParameter("subjectCode");
			String year = request.getParameter("year");
			String semester = request.getParameter("semester");
			String examType = request.getParameter("examType");
			String studentId = request.getParameter("studentNumber");
			
			int newTotalMark = Integer.parseInt(request.getParameter("newTotalMark"));

			sbs.updateScriptBookTotalMark(newTotalMark, subjectId, year, semester, examType, studentId);	
			
			//Redirect
			response.sendRedirect("TeacherExamScriptbooks.jsp?subjectCode="+subjectId+"&year="+year+"&semester="+semester+"&examType="+examType);
			
			
		}	
		
	catch(Exception e) {
		System.out.println(e.getMessage());
	}
		
		
	
	}
}

