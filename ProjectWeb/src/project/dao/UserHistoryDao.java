package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Result;

import board.action.BoardModel;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import project.model.UserLogModel;

public class UserHistoryDao {
	
	
	
	public ArrayList<UserLogModel> GetList() {
		
		ArrayList<UserLogModel>	list = new ArrayList<UserLogModel>();
		String str = "select * from loghistory";
		Connection conn = null;
		
		try {
			
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			ResultSet rs = pstmt.executeQuery();
			
			
			
			while(rs.next())
			{
				UserLogModel mD = new UserLogModel();
				mD.setNum(rs.getInt("log_num"));
				mD.setNick(rs.getString("log_nick"));
				mD.setCont(rs.getString("log_cont"));
				mD.setDate(rs.getString("log_date"));
				mD.setIp(rs.getString("log_ip"));
				
				list.add(mD);
				
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
		
		return list;
	}
	
	
	public int InsertLog(UserLogModel ld)
	{
		String str = "insert into loghistory(log_num, log_nick, log_cont, log_date, log_ip) " +
					"value (?,?,?,?,?) ";
		Connection conn = null;
		int rs = 0;
		
		ld.setNum(GetLastNum());
		ld.setDate(getDate());

		try {
			
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);			
			pstmt.setInt(1, ld.getNum());
			pstmt.setString(2,  ld.getNick());
			pstmt.setString(3,  ld.getCont());
			pstmt.setString(4,  ld.getDate());
			pstmt.setString(5,  ld.getIp());		
			rs = pstmt.executeUpdate();	
			
						
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtil.close(conn);
		}
			
		return rs;
	}

	public int GetLastNum()
	{
		String str = "select log_num from loghistory order by log_num desc"; 
		Connection conn = null;
		int n = 1;
		
	
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			ResultSet rs = pstmt.executeQuery();

			
			if(rs.next())
				n = rs.getInt(1) + 1;
		
			
			
			return n;			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		
		return n;		
		
	}
	
	//날짜 가져오는 함수
	public String getDate() {
		
		Connection conn = null;		
		String str = "SELECT NOW()";
		String Date = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				Date = rs.getString(1);
			}
		} catch(SQLException e ) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return Date;
	}
	

}
