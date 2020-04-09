package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBConn;

public class AiSommelierDAO_b1{

	public static void main(String[] args) {
		ResultSet rs=new AiSommelierDAO_b1().query("Gift/Sad","羊肉",4000);
		try {
			rs.next();
			String a=rs.getString("imgPath");
			System.out.println(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*new TWorderDAO().delete(4);*/
		
		/*TWorder T=new TWorder();
		T.setPork(100);
		T.setFish(100);
		T.setBeer(100);
		T.setSpirit(100);
		
		new AiSommelierDAO().update(2, T);*/

		/*TWorder T=new TWorder();
		T.setTableID(14);
		T.setPork(1);
		T.setFish(1);
		T.setBeer(1);
		T.setSpirit(1);
		
		new TWorderDAO().add(T);*/

	}
	
	
	
	public ResultSet query(String purpose, String dish, int price) {	
		String sql="";

		String sql1="SELECT Wine.id, Wine.imgPath, Wine.enName, Wine.chName, Wine.price"
				+ " FROM AI_Bartender.SommelierChoice " + 
				"inner join Wine on Wine.id=SommelierChoice.id " + 
				"where SommelierChoice.purpose=? and "+
				"SommelierChoice.dish=? and " + 
				"Wine.price<1000;";
		
		String sql2="SELECT Wine.id, Wine.imgPath, Wine.enName, Wine.chName, Wine.price"
				+ " FROM AI_Bartender.SommelierChoice " + 
				"inner join Wine on Wine.id=SommelierChoice.id " + 
				"where SommelierChoice.purpose=? and "+
				"SommelierChoice.dish=? and " + 
				"Wine.price>=1000 and Wine.price<2000;";
		
		String sql3="SELECT Wine.id, Wine.imgPath, Wine.enName, Wine.chName, Wine.price"
				+ " FROM AI_Bartender.SommelierChoice " + 
				"inner join Wine on Wine.id=SommelierChoice.id " + 
				"where SommelierChoice.purpose=? and "+
				"SommelierChoice.dish=? and " + 
				"Wine.price>=2000;";


		if(price<1000)sql=sql1;
		if(price>=1000 && price<2000)sql=sql2;
		if(price>=2000)sql=sql3;
		
		//System.out.println(sql);
		
		Connection conn=DBConn.getConn();
		ResultSet rs=null;
		
		try 
		{
			PreparedStatement st=conn.prepareStatement(sql);
			st.setNString(1, purpose);
			st.setNString(2, dish);
			rs=st.executeQuery();
			System.out.println(sql);
			System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(rs);
		return rs;
	}
	
	//================================ 用id搜尋
	/*public ResultSet read(String id) {
		String sql="select * from Wine where id="+id;
		Connection conn=DbConn.get();
		ResultSet rs=null;
		
		try 
		{
			Statement st=conn.createStatement();
			rs=st.executeQuery(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;
	}*/

	
	//================================ 新增 修改 刪除
	/*@Override
	public void add(Object o) {
		TWorder T=(TWorder)o;
		String sql="insert into TWorder(TableID,Pork,Fish,Beer,Spirit,Sum) values(?,?,?,?,?,?)";
		Connection conn=DbConn.get();
		
		try 
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, T.getTableID());
			ps.setInt(2, T.getPork());
			ps.setInt(3, T.getFish());
			ps.setInt(4, T.getBeer());
			ps.setInt(5, T.getSpirit());
			ps.setInt(6, T.getSum());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	@Override
	public void update(int TableID, Object o) {
		TWorder T=(TWorder) o;
		
		Connection conn=DbConn.get();
		String sql="update TWorder set Pork=?, Fish=?, Beer=?, Spirit=?, Sum=? where TableID=?";
		try 
		{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, T.getPork());
			ps.setInt(2, T.getFish());
			ps.setInt(3, T.getBeer());
			ps.setInt(4, T.getSpirit());
			ps.setInt(5, T.getSum());
			ps.setInt(6, TableID);
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public void delete(int id) {
		Connection conn=DbConn.get();
		String sql="delete from TWorder where TableID="+id;
		
		try {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	

}
