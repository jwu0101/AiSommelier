package servlet;


import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.membersDao_c1;
import model.members_c1;

/**
 * Servlet implementation class updateMembers
 */
@WebServlet("/updateMembers_c1")
public class updateMembers_c1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateMembers_c1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("uemail");
		String name = request.getParameter("uname");
		//name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		String mobile = request.getParameter("umobile");
		String address = request.getParameter("uaddress");
		//String password = request.getParameter("upassword");
		String type = request.getParameter("utype");
		//type = new String(type.getBytes("ISO-8859-1"), "UTF-8");
		String status = request.getParameter("ustatus");
		String updateUser = request.getParameter("uupdateUser");
		//updateUser = new String(updateUser.getBytes("ISO-8859-1"), "UTF-8");
		LocalDateTime ldt = LocalDateTime.now();
		
		members_c1 m = new members_c1();
		m.setEmail(email);
		m.setName(name);
		m.setPassword("");
		m.setMobile(mobile);
		m.setAddress(address);
		m.setBday(ldt.toLocalDate());//��
		m.setType(type);
		m.setStatus(status);
		m.setCreateUser("");//��
		m.setCreateTime(ldt);//��
		m.setUpdateUser(updateUser);
		m.setUpdateTime(ldt);
		new membersDao_c1().update(m);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
