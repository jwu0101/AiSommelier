package servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.google.gson.Gson;

import model.User_b1;
import java.util.*;

/**
 * Servlet implementation class addCustomer
 */
@WebServlet("/addCustomer_b1")
public class addCustomer_b1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCustomer_b1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String url = "jdbc:mysql://localhost:3306/ai_bartender?useUnicode=true&characterEncoding=utf-8";
		String user = "root";
		String password = "1234";
		
		model.User_b1 order = new model.User_b1();
		String str=request.getParameter("data");
		Gson g=new Gson();
		User_b1[ ]  add=g.fromJson(str, User_b1[ ].class) ;
		
		String email=request.getParameter("email");
		String name=request.getParameter("name");
		String pass=request.getParameter("password");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
	    String bday=request.getParameter("bday");
	    String usertype="一般會員"; 
	    String userstatus="active";
	    String createUser=request.getParameter("email");
	    String createTime=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()); 
	    String updateUser=request.getParameter("email");
	    String updateTime=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		String sql = "insert into ai_bartender.users( email , name,password,mobile,address,bday,type,status,createUser,createTime, updateUser,updateTime)" +"values ( ? , ? ,?,?,?,?,?,?,?,?,?,?) ";
		
		String msg = "";
		try {
		     Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException ex) {
			System.out.println("SQL Driver Excpetion:" + ex.getMessage());
			response.getWriter().append(ex.getMessage());
			return;
		}
		try (Connection cn = DriverManager.getConnection(url, user, password) ){
		
			cn.setAutoCommit(false);
			Statement st=cn.createStatement();
			
			PreparedStatement st1 = cn.prepareStatement(sql);
		    st1.setString(1,email);
			st1.setString(2, name);
			st1.setString(3, pass);
			st1.setString(4,  mobile);
			st1.setString(5, address);
			st1.setString(6, bday);
			st1.setNString(7,usertype);
			st1.setString(8,userstatus);
			st1.setString(9,createUser);
			st1.setString(10,createTime);
			st1.setString(11,updateUser);
			st1.setString(12,updateTime);
			int r1 = st1.executeUpdate();
			if (!(r1 > 0)) {
				cn.rollback();
				msg = "Failed";
				response.setContentType("text/html;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				response.getWriter().append(msg);
				return;
			}
			
			
			cn.commit();
			cn.setAutoCommit(true);

			
			response.getWriter().append("註冊完成!!!請先登入會員");
			
		} catch (SQLException  ex) {
			System.out.println("Product Servlet Excpetion:" + ex.getMessage());
			

			response.getWriter().append("已經註冊過囉!!!");
			
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
