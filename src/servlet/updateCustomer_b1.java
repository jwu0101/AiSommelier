package servlet;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
 * Servlet implementation class updateCustomer2
 */
@WebServlet("/updateCustomer_b1")
public class updateCustomer_b1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateCustomer_b1() {
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
		HttpSession session = request.getSession();
		User_b1 user = (User_b1) session.getAttribute("Users");
		
		

		String name = request.getParameter("name");
		String password=request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		
		String updateTime=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		
		if(user == null) {
			//response.getWriter().append("請先登入會員");
			response.getWriter().print("<script>alert('請先登入會員');window.location.href='jsp/front/login_b1.jsp'</script>");
			
			
		}else {
			if(name == null || password == null ||mobile == null || address == null) {
				request.setAttribute("user", user);
				request.getRequestDispatcher("jsp/front/updateCustomer_b1.jsp").forward(request, response);
				
			}else {

				user.setName(name);
				user.setPassword(password);
				user.setMobile(mobile);
				user.setAddress(address);
				user.setUpdateTime(updateTime);
				boolean b = new UserDao_b1().updateUser(user);
				if(b == true) {
					response.getWriter().append("資料修改成功");
				}else {
					response.getWriter().append("資料修改失敗，請檢查輸入格式是否正確");
				}
			}
		}}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
