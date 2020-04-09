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
import javax.servlet.http.HttpSession;

import dao.membersDao_c1;
import model.members_c1;

/**
 * Servlet implementation class queryShow
 */
@WebServlet("/queryShow_c1")
public class queryShow_c1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public queryShow_c1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("qemail");
		String name = request.getParameter("qname");
		String mobile = request.getParameter("qmobile");
		String address = request.getParameter("qaddress");
		String bday = request.getParameter("qbday");
		String status = request.getParameter("qstatus");
		boolean isNull=(email=="" ? (name=="" ? (mobile=="" ? (address=="" ? (bday=="" ? (status==""):false):false):false):false):false);
		
		if(isNull) {
			showMembers_c1.who = "query";
			request.getSession().setAttribute("isNull", "");
			request.getSession().setAttribute("list", new membersDao_c1().getAll(""));
		} else {

		
		members_c1 m = new members_c1();
		m.setEmail(email);
		m.setName(name);
		m.setMobile(mobile);
		m.setAddress(address);
		LocalDate ld;
		String bdayRG = "^\\d{4}-\\d{2}-\\d{2}$";
		if (bday.matches(bdayRG)) {
			ld = LocalDate.parse(bday);
			m.setBday(ld);
		} else {
			ld = LocalDate.parse("1900-01-01");
			m.setBday(ld);
		}
		m.setStatus(status);
		LocalDateTime ldt = LocalDateTime.now();// ��
		m.setPassword("");// ��
		m.setType("");// ��
		m.setCreateUser("");// ��
		m.setCreateTime(ldt);// ��
		m.setUpdateUser("");// ��
		m.setUpdateTime(ldt);// ��

		List qlist = new membersDao_c1().query(m);
		showMembers_c1.who = "query";
		String[] showform = {email,name,address,mobile,status,bday};
		request.getSession().setAttribute("showform", showform);
		request.getSession().setAttribute("isNull", "notnull");
		request.getSession().setAttribute("list", qlist);
		}

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
