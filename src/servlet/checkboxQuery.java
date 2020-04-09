package servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dao.DBConn_b2;
import model.wine_b2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;

/**
 * Servlet implementation class checkboxQuery
 */
@WebServlet("/checkboxQuery")
public class checkboxQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public checkboxQuery() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson json = new Gson();
		String list = request.getParameter("checkboxList");

		System.out.println(list);

		List<String> l = json.fromJson(list, List.class);
		String sql = "select * from ai_bartender.wine where (";
		if(l.size()==0) {
			sql = "select * from ai_bartender.wine";
		} else {
			List<String> lwine = new ArrayList<String>();
			List<String> lplace = new ArrayList<String>();
			List<String> lprice = new ArrayList<String>();
			for (int i = 0; i < l.size(); i++) {
				String who = l.get(i);
				if (who.equals("France") || who.equals("USA") || who.equals("Chlie") || who.equals("Australia")) {
					lplace.add(who);
				} else if (who.equals("1000down") || who.equals("between1000and2000") || who.equals("2000up")) {
					lprice.add(who);
				} else if (who.equals("紅酒") || who.equals("白酒")) {
					lwine.add(who);
				}
			}
			for (int i = 0; i < lwine.size(); i++) {
				sql += " type='" + lwine.get(i) + "' or";
				if( i==lwine.size()-1 && (lprice.size()>0 || lplace.size()>0)) {
					sql+=" '1'='2') and (";
				} else if(i==lwine.size()-1){
					sql+=" '1'='2')";
				}
			}
			for (int i = 0; i < lplace.size(); i++) {
				sql += " place='" + lplace.get(i) + "' or";
				if( i==lplace.size()-1 && (lprice.size()>0)) {
					sql+=" '1'='2') and (";
				} else if(i==lplace.size()-1) {
					sql+=" '1'='2')";
				}
			}
			for (int i = 0; i < lprice.size(); i++) {
				if(lprice.get(i).equals("1000down")) {
					sql += " price<1000 or";
				} else if(lprice.get(i).equals("between1000and2000")) {
					sql += " (price>=1000 and price<2000) or";
				} else if(lprice.get(i).equals("2000up")) {
					sql += " price>=2000 or";
				}
				if( i==lprice.size()-1) {
					sql+=" '1'='2') ";
				} 
			}
		}
		
		System.out.println(sql);
		ArrayList<wine_b2>  data=new ArrayList<>();
		try {
			Statement st = DBConn_b2.getConn().createStatement();
			ResultSet rs = st.executeQuery(sql);
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
			DBConn_b2.getConn().close();
			request.setAttribute("qlist", data);
			request.getRequestDispatcher("jsp/front/checkQueryShow.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String test = request.getParameter("test");
		//System.out.println(test);
		//System.out.println(l.get(1));
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
