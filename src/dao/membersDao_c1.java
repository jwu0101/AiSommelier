package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.members_c1;

public class membersDao_c1 {
	public static void main(String[] args) {
		//new membersDao().getAll();
	}
	String table = "users";
	String typeAdmin = "管理者";
	public List getAll(String email) {
		List list = new ArrayList();
		Connection conn = DBConn_c1.getConn();
		String sql = "SELECT * FROM "+table+" where not type='"+typeAdmin+"'";
		if(!email.equals("")) {
			sql= "SELECT * FROM "+table+" where email= '"+email+"' and not type= '"+typeAdmin+"'";
		}
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql); 
			while(rs.next()) {
				members_c1 m = new members_c1();
				//String a11=(rs.getDate(6).toLocalDate()).toString();
				//System.out.println(a11);
				m.setEmail(rs.getString(1));
				m.setName(rs.getString(2));
				m.setPassword(rs.getString(3));
				m.setMobile(rs.getString(4));
				m.setAddress(rs.getString(5));
				m.setBday(rs.getDate(6).toLocalDate());
				m.setType(rs.getString(7));
				m.setStatus(rs.getString(8));
				m.setCreateUser(rs.getString(9));
				m.setCreateTime(rs.getTimestamp(10).toLocalDateTime());
				m.setUpdateUser(rs.getString(11));
				m.setUpdateTime(rs.getTimestamp(12).toLocalDateTime());
				list.add(m);
			}
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(list.get(0).toString());
		//System.out.println(list.get(1).toString());
		return list;
		
	}
	
	public List query(Object o) {
		List list = new ArrayList();
		Connection conn = DBConn_c1.getConn();
		members_c1 m = (members_c1)o;
		int i =0;
		String sql = "select * from "+table+" where not type='"+typeAdmin+"' and";
		
		if(!m.getEmail().equals("")) {
			sql+=" (email like '%"+m.getEmail()+"%') and";
			i++;
		}
		if(!m.getName().equals("")) {
			sql+=" (name like '%"+m.getName()+"%') and";
			i++;
		}
		if(!m.getAddress().equals("")) {
			sql+=" (address like '%"+m.getAddress()+"%') and";
			i++;
		}
		if(!m.getMobile().equals("")) {
			sql+=" (mobile like '%"+m.getMobile()+"%') and";
			i++;
		}
		if(!m.getStatus().equals("")) {
			sql+=" (status = '"+m.getStatus()+"') and";
			i++;
		}
		if(!m.getBday().toString().equals("1900-01-01")) {
			sql+=" (bday like '%"+m.getBday()+"%') and";
			i++;
		}
		if(i==0) {
			sql+=" ('%1%' like '%2%')";
		} else {
			sql+=" ('%1%' like '%1%')";
		}
		
		
		
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				members_c1 mq = new members_c1();
				//String a11=(rs.getDate(6).toLocalDate()).toString();
				//System.out.println(a11);
				mq.setEmail(rs.getString(1));
				mq.setName(rs.getString(2));
				mq.setPassword(rs.getString(3));
				mq.setMobile(rs.getString(4));
				mq.setAddress(rs.getString(5));
				mq.setBday(rs.getDate(6).toLocalDate());
				mq.setType(rs.getString(7));
				mq.setStatus(rs.getString(8));
				mq.setCreateUser(rs.getString(9));
				mq.setCreateTime(rs.getTimestamp(10).toLocalDateTime());
				mq.setUpdateUser(rs.getString(11));
				mq.setUpdateTime(rs.getTimestamp(12).toLocalDateTime());
				list.add(mq);
			}
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	public String update(Object o) {
		Connection conn = DBConn_c1.getConn();
		members_c1 m = (members_c1)o;
		String sql = "update "+table+" set name=?,"+ "mobile=?,"
				+ "address=?,"
				+ "type=?,"
				+ "status=?,"
				+ "updateUser=?,"
				+ "updateTime=? "
				+ "where email= '"+m.getEmail()+"'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, m.getName());
			//ps.setString(2, m.getPassword());
			ps.setString(2, m.getMobile());
			ps.setString(3, m.getAddress());
			ps.setString(4, m.getType());
			ps.setString(5, m.getStatus());
			ps.setString(6, m.getUpdateUser());
			Timestamp ts = Timestamp.valueOf(m.getUpdateTime());
			ps.setTimestamp(7, ts);
			
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "update success";
		
	}
	public void del(String email) {
		String sql="update "+table+" set status='inactive' where email='"+email+"'";
		Connection conn = DBConn_c1.getConn();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
