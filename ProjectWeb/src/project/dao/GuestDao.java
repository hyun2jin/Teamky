package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.action.BoardModel;
import guest.action.GuestModel;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;



public class GuestDao {
	
	//게시글 마지막번호 가져오는 함수
	public int getNext(Connection conn) {

		String str = "SELECT guest_no FROM guestbook ORDER BY guest_no DESC";
		
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
	
	
		
	public ArrayList<GuestModel> getList(int page) {
		
		String str = "select * from guestbook ORDER BY guest_no DESC";
		
			
		ArrayList<GuestModel> list = new ArrayList<GuestModel>();
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
		
			
			ResultSet rs = pstmt.executeQuery();							
			
			while(rs.next())
			{
				GuestModel gm = new GuestModel();
				
				gm.setGuestNo(rs.getInt("guest_no"));
				gm.setGuestNick(rs.getString("guest_nick"));
				gm.setGuestPwd(rs.getString("guest_content"));
				gm.setGuestCont(rs.getString("guest_content"));
				gm.setGuestImage(rs.getString("guest_image"));
				gm.setGueestDate(rs.getString("guest_date"));
				
				list.add(gm);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return list;
	}
	
	
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
	

	public int Write(Connection conn, String id, String passwd, String cont, String imgurl)
	{
		String str = "insert into guestbook (guest_no, guest_nick, guest_passwd, guest_content, guest_image, guest_date)"
				+ "VALUES(?,?,?,?,?,?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(str);
			pstmt.setInt(1,  getNext(conn));
			pstmt.setString(2, id);
			pstmt.setString(3,  passwd);
			pstmt.setString(4,  cont);
			pstmt.setString(5,  imgurl);
			pstmt.setString(6,  getDate(conn));
			
			return pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception	
			e.printStackTrace();
		}
		
		return -1;
	}
	
	//게시글 삭제
	public int Delete(int num) {
		
		String str = "delete from guestbook where guest_no = ?";
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			pstmt.setInt(1,  num);
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
	
	

}
