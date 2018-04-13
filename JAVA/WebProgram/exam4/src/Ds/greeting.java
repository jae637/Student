package Ds;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class greeting
 */
@WebServlet("/greeting")
public class greeting extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter logFile;

	public void init() throws ServletException {
		try {
			logFile = new PrintWriter(new FileWriter("c:\\data\\log.txt", true));
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public greeting() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("NAME");
		String greeting = "안녕하세요, " + name + "님. ";
		if (logFile != null) {
			GregorianCalendar now = new GregorianCalendar();
			logFile.printf("%TF %TT - %s %n ", now, now, name);
		}
		response.setContentType("text/html;charset=euc-kr ");
		PrintWriter out = response.getWriter();
		out.println("<HEAD><TITLE>인사하기</TITLE></HEAD> ");
		out.println("<BODY>");
		out.println(greeting);
		out.println("</BODY>");
		out.println("</HTML>");
	}

	public void destroy() {
		if (logFile != null)
			logFile.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
