package servlet;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.membersDao_c1;
import model.members_c1;

/**
 * Servlet implementation class updateShow
 */
@WebServlet("/updateShow_c1")
public class updateShow_c1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateShow_c1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("uemail");
		String who = request.getParameter("who");
		if(who.equals("update")) {
		System.out.println(email);
		members_c1 m = (members_c1)(new membersDao_c1().getAll(email)).get(0);
		System.out.println(m);
		request.setAttribute("members",m);
		request.getRequestDispatcher("jsp/back/updatePage_c1.jsp").forward(request,response);
		}
		if(who.equals("detail")) {
			members_c1 m = (members_c1)(new membersDao_c1().getAll(email)).get(0);
			request.setAttribute("members",m);
			request.getRequestDispatcher("jsp/back/detailPage_c1.jsp").forward(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
