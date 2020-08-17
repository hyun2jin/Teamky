package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import board.action.CommentModel;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class CommentDao {

	
	public ArrayList<CommentModel> getComment(int num) {
		
		String str = "select * from commentboard where comment_parent = ?";
		
		ArrayList<CommentModel> list = new ArrayList<CommentModel>();
		Connection conn = null;
		
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			pstmt.setInt(1,  num);
			ResultSet rs = pstmt.executeQuery();
			
		
			while(rs.next())
			{
				CommentModel cm = new CommentModel();
				
				cm.setComment_num(rs.getInt("comment_num"));
				cm.setComment_board(rs.getInt("comment_board"));
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
	
	public int DelteCmtAll(int bdnum) {
		
		String str = "delete from commentboard where comment_parent = ?";
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			pstmt.setInt(1,  bdnum);
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
	
	public int DeleteCm(int num, int parent) {
		
		String str = "delete from commentboard where comment_num = ? and comment_parent = ?";
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			pstmt.setInt(1,  num);
			pstmt.setInt(2,  parent);
			int rs = pstmt.executeUpdate();
			
			
			
			if(rs == 1)
			{
				return 1;			
			}
			
			return 0;			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return -1;
	}
	

	
	public int GetLastNum(int num)
	{
		String str = "select comment_num from commentboard where comment_parent = ? order by comment_num desc limit 1";
		Connection conn = null;
		
	
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
		
			pstmt.setInt(1,  num);
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
	
		
	public int Write(CommentModel data)
	{
		String str = "insert into commentboard " + 
				" (comment_num, comment_board, comment_nick, comment_date, comment_parent, comment_content) " +
				"value(?,?,?,?,?,?) ";
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			
			pstmt.setInt(1, GetLastNum(data.getComment_parent()));
			pstmt.setInt(2,  data.getComment_board());
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
	
	
	public int Update(int boardNum, int comment_num, String content)
	{
		String str = "update commentboard set comment_content=? where comment_num=? and comment_parent = ?";
		Connection conn = null;		
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			pstmt.setString(1,  content);
			pstmt.setInt(2,  comment_num);
			pstmt.setInt(3,  boardNum);
			
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
