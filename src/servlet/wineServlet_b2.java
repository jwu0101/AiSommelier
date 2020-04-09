package servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.wine_b2;
import java.util.*;
import com.google.gson.*;
import java.sql.*;

/**
 * Servlet implementation class winesServlet
 */
@WebServlet("/wineshop_b2")
public class wineServlet_b2 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public wineServlet_b2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		  String url="jdbc:mysql://localhost:3306/AI_Bartender?serverTimezone=CST";
		  String user="root";
		  String password="1234";
		  String pid = request.getParameter("id");
		  //System.out.println(pid);
		  String sql="select id,enName,chName,type,percent,ml,price,unit,place,grape,feature,imgPath from AI_Bartender.wine";
		  if(pid != null) {
			  sql="select * from AI_Bartender.wine where id='"+pid+"'";
		  }
		  ArrayList<wine_b2>  data=new ArrayList<>();
		  try {
			  Class.forName("com.mysql.jdbc.Driver");
			  Connection cn=DriverManager.getConnection(url, user, password);
			   Statement st=cn.createStatement();
			   ResultSet rs=st.executeQuery(sql);
			   while(rs.next()) {
				    String id=rs.getString("id");
				    String enName=rs.getString("enName");
				    String chName=rs.getString("chName");
				    String type=rs.getString("type");
				    String percent=rs.getString("percent");
				    int ml=rs.getInt("ml");
				    int price=rs.getInt("price");
				    String unit=rs.getString("unit");
				    String place=rs.getString("place");
				    String grape=rs.getString("grape");
				    String feature=rs.getString("feature");
				    String imgPath=rs.getString("imgPath");
				    
				    wine_b2 pt=new wine_b2(id,enName,chName,type,percent,ml,price,unit,place,grape,feature,imgPath);
				    data.add(pt);
			   }			   
			   cn.close();
			   //Gson g=new Gson();
			   //String str=g.toJson(data);
			   response.setContentType("text/html;charset=utf-8");
			   response.setCharacterEncoding("utf-8");
			   request.setAttribute("list", data);
			   if(pid != null) {
				   request.getRequestDispatcher("jsp/front/detail_b2.jsp").forward(request, response);
				   return;
				   //response.sendRedirect("jsp/front/detail_b2.jsp");
			   }
			   request.getRequestDispatcher("jsp/front/catalog_b2.jsp").forward(request, response);
			   //response.getWriter().append(str);
			   //response.sendRedirect("jsp/front/catalog_b2.jsp");
		  }catch(SQLException | ClassNotFoundException ex) {
			    System.out.println("Product Servlet Excpetion:"+ex.getMessage());
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
