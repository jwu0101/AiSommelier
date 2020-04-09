package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User_b1;

public class UserDao_b1 {

	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public UserDao_b1() {
		conn = new DB_b1().getConn();
	}
	
	public boolean addUser(User_b1 user_b1) {
		String sql = "insert into users(email ,name,password,mobile,address,bday,type,status,createUser,createTime, updateUser,updateTime) values(?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_b1.getEmail());
			ps.setString(2, user_b1.getName());
			ps.setString(3, user_b1.getPassword());
			ps.setString(4, user_b1.getMobile());
			ps.setString(5, user_b1.getAddress());
			ps.setString(6, user_b1.getBday());
			ps.setString(7,user_b1.getType());
			ps.setString(8,user_b1.getStatus());
			ps.setString(9,user_b1.getCreateUser());
			ps.setString(10,user_b1.getCreaterTime());
			ps.setString(11,user_b1.getUpdateUser());
			ps.setString(12,user_b1.getUpdateTime());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public User_b1 findUser(String email) {
		String sql = "select * from users where email=?";
		User_b1 user_b1 = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				user_b1 = new User_b1();
				user_b1.setEmail(email);
				user_b1.setName(rs.getString("name"));
				user_b1.setPassword(rs.getString("password"));
				user_b1.setMobile(rs.getString("mobile"));
				user_b1.setAddress(rs.getString("address"));
				user_b1.setBday(rs.getString("bday"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user_b1;
	}
	
	public User_b1 CheckUser(String email , String password) {
		String sql = "select * from users where email=? and password=?";
		User_b1 user_b1 = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				user_b1 = new User_b1();
				user_b1.setEmail(email);
				user_b1.setName(rs.getString("name"));
				user_b1.setMobile(rs.getString("mobile"));
				user_b1.setAddress(rs.getString("address"));
				user_b1.setBday(rs.getString("bday"));
				user_b1.setType(rs.getString("type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user_b1;
	}
	
	public boolean updateUser(User_b1 user_b1) {
		String sql = "update users set name=? , password=?,mobile=?, address=? ,updateTime=? where email=?";
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, user_b1.getName());
			ps.setString(2, user_b1.getPassword());
			ps.setString(3, user_b1.getMobile());
			ps.setString(4, user_b1.getAddress());
			ps.setString(5, user_b1.getUpdateTime());
			ps.setString(6, user_b1.getEmail());
			
			
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updatePassword(String email , String password) {
		String sql = "update users set password=? where email=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,  email.substring(0,email.indexOf("@"))+"123");
			ps.setString(2, email);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
