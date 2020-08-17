package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import board.action.CommentExModel;
import board.action.CommentModel;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class CommentExDao {

public ArrayList<CommentExModel> getComment(int num) {
		
		String str = "select * from commentboardex where comment_parent = ?";
		
		ArrayList<CommentExModel> list = new ArrayList<CommentExModel>();
		Connection conn = null;
		
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			pstmt.setInt(1,  num);
			ResultSet rs = pstmt.executeQuery();
		
	
			while(rs.next())
			{
				CommentExModel cm = new CommentExModel();
				
				cm.setComment_num(rs.getInt("comment_num"));
				cm.setComment_board(rs.getInt("comment_board"));
				cm.setComment_commnum(rs.getInt("comment_commnum"));
				cm.setComment_nick(rs.getString("comment_nick"));
				cm.setComment_parent(rs.getInt("comment_parent"));
				cm.setDate(rs.getString("comment_date"));
				cm.setContent(rs.getString("comment_content"));
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

	public int DeletecmAllEx(int bnum) {
		
		String str = "delete from commentboardex where comment_parent = ?";
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			pstmt.setInt(1,  bnum);
			int rs = pstmt.executeUpdate();			
						
			if(rs == 1)
				return 1;
			
			return 0;			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return -1;
	}


	public int DeleteCmAll(int bnum, int cmnum) {
		
		String str = "delete from commentboardex where comment_board = ? and comment_commnum = ?";
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			pstmt.setInt(1,  bnum);
			pstmt.setInt(2,  cmnum);
			int rs = pstmt.executeUpdate();			
						
			if(rs == 1)
				return 1;
			
			return 0;			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return -1;
	}
	
	
	public int DeleteCmEx(int num, int parent, int cmparent) {
		
		String str = "delete from commentboardex where comment_num = ? and comment_parent = ? and comment_commnum=?";
		Connection conn = null;
		
		System.out.println(num);
		System.out.println(parent);
		System.out.println(cmparent);
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			pstmt.setInt(1,  num);
			pstmt.setInt(2,  parent);
			pstmt.setInt(3,  cmparent);
			int rs = pstmt.executeUpdate();
			
			return 1;
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return -1;
	}
	

	
	public int GetLastNum(int num, int comparent)
	{
		String str = "select commentboardex from commentboard where comment_parent = ? and comment_commnum = ? order by comment_num desc limit 1";
		Connection conn = null;
		
	
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			
			System.out.println(str);
			System.out.println(num);
			System.out.println(comparent);
		
			pstmt.setInt(1,  num);
			pstmt.setInt(2,  comparent);
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
		
		
		
		
		return 1;		
		
	}
	
		
	public int Write(CommentExModel data)
	{
		String str = "insert into commentboardex " + 
				" (comment_board, comment_commnum, comment_nick, comment_date, comment_parent, comment_content) " +
				"value(?,?,?,?,?,?) ";
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			
			pstmt.setInt(1,  data.getComment_board());
			pstmt.setInt(2,  data.getComment_commnum());
			pstmt.setString(3, data.getComment_nick());
			pstmt.setString(4, data.getDate());
			pstmt.setInt(5,  data.getComment_parent());
			pstmt.setString(6, data.getContent());
						
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return 0;
	}
	
	
	public int Update(CommentExModel mod)
	{
		String str = "update commentboardex set comment_content=? where comment_num=? and comment_parent = ? and comment_commnum = ?";
		Connection conn = null;		
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			pstmt.setString(1,  mod.getContent());
			pstmt.setInt(2,  mod.getComment_num());
			pstmt.setInt(3,  mod.getComment_parent());
			pstmt.setInt(4,  mod.getComment_commnum());
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return -1;
	}
	
	
}
