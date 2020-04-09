package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn_b1 {

	public static void main(String[] args) {
		System.out.println(DbConn_b1.get());

	}
	
	public static Connection get() 
	{
		Connection conn=null;
		String url="jdbc:mysql://localhost:3306/AI_Bartender";
		String user="root";
		String password="1234";
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {
			System.out.println("error:"+e.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}

}
