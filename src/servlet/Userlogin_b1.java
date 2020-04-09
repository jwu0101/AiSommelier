package servlet;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao_b1;
import model.User_b1;

/**
 * Servlet implementation class Userlogin2
 */
@WebServlet("/Userlogin_b1")
public class Userlogin_b1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Userlogin_b1() {
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
		String auto = request.getParameter("auto");
		
		if(email == null || password == null) {
			request.getRequestDispatcher("login_b1.jsp").forward(request, response);
		}else {
			User_b1 user_b1 = new UserDao_b1().CheckUser(email, password);
			if(user_b1 == null) {
				response.getWriter().append("帳號或密碼錯誤");
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("Users", user_b1);
				if(auto != null && auto.equals("auto")) {
					Cookie cookie = new Cookie("UserEmail" , email);
					cookie.setMaxAge(7*24*60*60);
					response.addCookie(cookie);
				}
				System.out.println(user_b1.getType());
				response.getWriter().append(user_b1.getType());
			}
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
