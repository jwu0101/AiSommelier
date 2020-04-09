package servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao_b1;

/**
 * Servlet implementation class ForgetPassServlet
 */
@WebServlet("/ForgetPassServlet_b1")
public class ForgetPassServlet_b1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPassServlet_b1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html ; charset=utf-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(password == null) {
			request.setAttribute("email", email);
			request.getRequestDispatcher("jsp/front/updatePass_b1.jsp").forward(request, response);
			return;
		}
		boolean b = new UserDao_b1().updatePassword(email , password);
		if(b == false) {
			response.getWriter().append("資料輸入錯誤，變更密碼失敗");
		}else {
			response.getWriter().append("已成功變更密碼");
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
