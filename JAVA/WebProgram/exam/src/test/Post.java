package test;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Post
 */
@WebServlet("/Post")
public class Post extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		// TODO Auto-generated method stub
			String name = request.getParameter("NAME");
			String title = request.getParameter("TITLE");
			String content = request.getParameter("CONTENT");
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<HTML>");
			out.println("<HEAD><TITLE> 게시판 </TITLE></HEAD>");
			out.println("<BODY>");
			out.printf("이름: %s <BR>", name);
			out.printf("제목: %s <BR>", title);
			out.println("--------------<BR>");
			out.printf("<PRE>%s</PRE>",content);
			out.println("--------------<BR>");
			out.println("</BODY>");
			out.println("</HTML>");
			out.println("<form action= /exam/NewWriter.html>"); 
			out.println("<input type= submit value='게시판'>");  
			out.println("</form>");
		}

}
