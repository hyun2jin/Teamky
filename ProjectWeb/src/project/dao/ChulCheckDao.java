package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.org.apache.bcel.internal.generic.ReturnInstruction;

import board.action.CommentModel;
import chulcheck.action.ChulCheckModel;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ChulCheckDao {
	
	ChulCheckModel		ChDao = new ChulCheckModel();
	
	public void AllDelete() {
		
		String str = "delete from chulcheck";
		Connection conn = null;
		
		try {
			
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			ResultSet rs = pstmt.executeQuery();
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtil.close(conn);
		}	
	}
	
	//날짜 가져오는 함수
	public String getDate(Connection conn) {
		
		String str = "SELECT DATE_FORMAT(NOW(),'%p %H시%i분') AS DATE FROM DUAL"; 

		try {
			PreparedStatement pstmt = conn.prepareStatement(str);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				return rs.getString(1);
			}
		} catch(SQLException e ) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	

	public int GetTodayEnd(String nick) {
		// TODO Auto-generated method stub
		String str = "select count(chul_nick) from chulcheck where chul_nick = ?";
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);

			pstmt.setString(1,  nick);
			ResultSet rs = pstmt.executeQuery();
			
			int n = 0;
			if(rs.next())
				n = rs.getInt(1);
			
			System.out.println(n);
		
			return n;			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}		
		
		
		return 0;
	}
	
		
	public int GetLastNum()
	{
		String str = "select chul_idx from chulcheck order by chul_idx desc limit 1";
		Connection conn = null;
		
	
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			

			ResultSet rs = pstmt.executeQuery();

			int n = 1;
			if(rs.next())
				n = rs.getInt(1) + 1;
			else n = 1;
			
			
			return n;			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		
		
		
		return -1;		
		
	}
	
	
	public ArrayList<ChulCheckModel> getChullist() {
		
		String str = "select * from chulcheck ORDER BY chul_idx DESC ";
		
		ArrayList<ChulCheckModel> list = new ArrayList<ChulCheckModel>();
		Connection conn = null;
		
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			ResultSet rs = pstmt.executeQuery();
			
		
			while(rs.next())
			{
				ChulCheckModel cm = new ChulCheckModel();
				
				cm.setChul_no(rs.getInt("chul_idx"));
				cm.setChul_id(rs.getString("chul_nick"));
				cm.setChul_cont(rs.getNString("chul_content"));
				cm.setChul_date(rs.getString("chul_date"));
				cm.setChul_ip(rs.getString("chul_ip"));
				cm.setChul_day(rs.getInt("chul_day"));
				cm.setChul_ip(rs.getString("chul_ip"));
				

				
				list.add(cm);				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
	
		return list;		
	}
	
	public int UpdateChul(String nick, int day) {
		
		String str = "update member set chulday=? where member_nick=?";
		Connection conn = null;
		
		try {

			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
		
			pstmt.setInt(1, day);
			pstmt.setString(2,  nick);
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtil.close(conn);
		}
		
				
		return 1;
		
	}
	
	public int GetChul_Day(String nick)
	{
		String str = "select chulday from member where member_nick = ?";
		Connection conn = null;
		int Day = 1;
		
		try {

			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
		
			pstmt.setString(1, nick);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				Day = rs.getInt(1) + 1;
				UpdateChul(nick, Day);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtil.close(conn);
		}
		
		
		
		return Day;	
	}
	
	public int Reset()
	{
		String str = "delete from chulcheck";
		Connection conn = null;
		int rs = 0;
		
		try {

			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			rs = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);			
		}
		
		return rs;
	}
		
	public int Write(ChulCheckModel cm)
	{
		String str = "insert into chulcheck (chul_idx, chul_nick, chul_content, chul_date, chul_ip, chul_day) value(?,?,?,?,?,?)";
		Connection conn = null;
		
		try {

			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			int Day = 1;
			String date = getDate(conn);
			cm.setChul_date(date);
		
			
			pstmt.setInt(1,  cm.getChul_no());
			pstmt.setString(2,  cm.getChul_id());
			pstmt.setString(3,  cm.getChul_cont());
			pstmt.setString(4,  cm.getChul_date());
			pstmt.setString(5,  cm.getChul_ip());
			pstmt.setInt(6,  GetChul_Day(cm.getChul_id()));
			int rs = pstmt.executeUpdate();
			
			return rs;		
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtil.close(conn);
		}
		
		
		
		return 1;
	}



}
