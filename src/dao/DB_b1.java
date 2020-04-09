package dao;

import java.sql.*;

public class DB_b1 {

	private Connection conn = null;
	
	public DB_b1() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ai_bartender?useUnicode=true&characterEncoding=utf-8","root","1234");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		return conn;
	}
}
