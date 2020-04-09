package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn_c1 {
	
	public static Connection getConn() {
		String url="jdbc:mysql://localhost:3306/ai_bartender?useUnicode=true&characterEncoding=utf-8";
		String user="root";
		String password="1234";
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
