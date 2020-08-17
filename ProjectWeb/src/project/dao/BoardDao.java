package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.action.BoardModel;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import project.model.Member;

public class BoardDao {
	
	//날짜 가져오는 함수
	public String getDate(Connection conn) {
		
		String str = "SELECT NOW()";
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
	

	//게시글 마지막번호 가져오는 함수
	public int getNext(Connection conn) {

		String str = "SELECT board_num FROM freeboard ORDER BY board_num DESC";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(str);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				return rs.getInt(1)+1;
			
			return 1;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int GetNextNum(Connection conn)
	{
		return getNext(conn);		
	}
	
	
	public int SetLock(Connection conn, int no, boolean lock) {
		
		String str = "update freeboard set board_lock=? where board_num =?";
		
		try {
			
			PreparedStatement pstmt = conn.prepareStatement(str);
			pstmt.setBoolean(1,  lock);
			pstmt.setInt(2,  no);
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0;
	}

	public int Write(Connection conn, String title, String id, String content)
	{
		String str = "insert into freeboard (board_num, board_id, board_subject, board_content, board_date)"
				+ "VALUES(?,?,?,?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(str);
			pstmt.setInt(1,  getNext(conn));
			pstmt.setString(2, id);
			pstmt.setString(3,  title);
			pstmt.setString(4,  content);
			pstmt.setString(5,  getDate(conn));
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int Update(Connection conn, int board_num, String title, String content)
	{
		String str = "update freeboard set board_subject=?, board_content=? where board_num=?";
		
		
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(str);
			pstmt.setString(1,  title);
			pstmt.setString(2,  content);
			pstmt.setInt(3,  board_num);
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		}
		
		return -1;
	}	
	
	
	public int getListCount() {
		
		String str = "select count(*) from freeboard";
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				int mcount = rs.getInt(1);
				int count = mcount / 10;
				if((mcount % 10) > 0) count++;				
				
				return count;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtil.close(conn);
		}
		
		return 0;
	}
	
	
	public ArrayList<BoardModel> getList(int page) {
		
		String str = "select * from freeboard where board_num >= ? and board_num < ? ORDER BY board_num DESC LIMIT 10";
		
		
		
		
		ArrayList<BoardModel> list = new ArrayList<BoardModel>();
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			int be = 0;
			int af = 0;
			
			be = (page-1) * 10;
			af = page * 10;
			
			
			if(page == 1)
				be = 0;
			
					
			//pstmt.setInt(1,  getNext(conn)-(page-1)*10);
			pstmt.setInt(1,  be);
			pstmt.setInt(2,  af);
			ResultSet rs = pstmt.executeQuery();
			
			
			
			while(rs.next())
			{
				BoardModel bm = new BoardModel();
				bm.setBoard_num(rs.getInt("board_num"));
				bm.setBoard_id(rs.getString("board_id"));
				bm.setBoard_title(rs.getString("board_subject"));
				bm.setBoard_content(rs.getString("board_content"));
				bm.setBoard_date(rs.getString("board_date"));
				bm.setBoard_lock(rs.getBoolean("board_lock"));
				bm.setBoard_read(rs.getInt("board_count"));
				list.add(bm);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return list;
	}
	
	//게시글 삭제할때 댓글도 삭제하게
	public int DeleteComment(int bnum) {
		
		String str = "delete from commentboard where comment_parent = ?";
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
	
	//게시글 삭제
	public String Delete(int num) {
		
		String str = "delete from freeboard where board_num = ?";
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			pstmt.setInt(1,  num);
			int rs = pstmt.executeUpdate();
			
			if(rs == 1)
			{
				DeleteComment(num);
				return "1";			
			}
			
			return "0";			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return "-1";
	}
	
	public BoardModel viewBoard(int num) {
		
		String str = "select * from freeboard where board_num =?";
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			pstmt.setInt(1, num);			
			ResultSet rs = pstmt.executeQuery();
			
		
			if(rs.next())
			{
				BoardModel Bm = new BoardModel();
				
			
				Bm.setBoard_num(rs.getInt("board_num"));
				Bm.setBoard_title(rs.getString("board_subject"));
				Bm.setBoard_id(rs.getString("board_id"));
				Bm.setBoard_content(rs.getString("board_content"));
				Bm.setBoard_date(rs.getString("board_date"));
				Bm.setBoard_lock(rs.getBoolean("board_lock"));
				Bm.setBoard_read(rs.getInt("board_count"));
				
				return Bm;
			}		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	
	public boolean nextPage(int pageNumber) {
		
		String str = "select * from freeboard where board_num < ? orderby board_num limit 10";
		ArrayList<BoardModel> list = new ArrayList<BoardModel>();
		Connection conn = null;
		
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			pstmt.setInt(1,  getNext(conn)-(pageNumber-1)*10);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return false;
	}
}
