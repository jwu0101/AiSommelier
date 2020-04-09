package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.membersDao_c1;

/**
 * Servlet implementation class showMembers
 */
@WebServlet("/showMembers_c1")
public class showMembers_c1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String who = "select";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public showMembers_c1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (who.equals("select")) {
			List list = new membersDao_c1().getAll("");
			request.setAttribute("list", list);
			request.getRequestDispatcher("jsp/back/members_c1.jsp").forward(request, response);
		}
		
		if (who.equals("query")) {
			request.setAttribute("showform",request.getSession().getAttribute("showform"));
			request.setAttribute("isNull",request.getSession().getAttribute("isNull"));
			request.setAttribute("list",request.getSession().getAttribute("list"));
			//request.getSession().removeAttribute("list");
			who = "select";
			request.getRequestDispatcher("jsp/back/members_c1.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
