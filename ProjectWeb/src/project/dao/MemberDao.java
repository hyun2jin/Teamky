package project.dao;

import java.sql.*;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import project.model.*;


public class MemberDao {
	
	
	
	public int CheckId(String id) {
		
		String str = "select count(member_id) from member where member_id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int n = 0;
		
		try {
			
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(str);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				n = rs.getInt(1);			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtil.close(conn);
		}
		
		return n;		
	}
	
	public int CheckNick(String nick) {
		
		String str = "select count(member_nick) from member where member_nick=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		int n = 0;
		
		try {
			
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(str);
			pstmt.setString(1, nick);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				n = rs.getInt(1);			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtil.close(conn);
		}
		
		return n;		
	}
	
	
	public Member selectById(Connection conn, String id)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(
					"SELECT * FROM member where member_id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			Member member = null;

			if (rs.next()) {
				member = new Member(
						rs.getInt("member_seq"),
						rs.getString("member_id"),
						rs.getString("member_nick"),
						rs.getString("passwd"),		
						rs.getString("email"),
						rs.getString("phone"),
						rs.getInt("member_st"),
						rs.getInt("member_att"));
			}
			return member;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	
	

	public void insert(Connection conn, Member mem)
			throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("INSERT INTO member (member_id, member_nick, passwd, email, phone)"
						+ " VALUES (?, ?, ?, ?, ?) ")) {
			pstmt.setString(1, mem.getMem_id());
			pstmt.setString(2, mem.getMem_nick());
			pstmt.setString(3, mem.getMem_pwd());
			pstmt.setString(4,  mem.getEmail());
			pstmt.setString(5,  mem.getPhone());
			
		
			
			
			pstmt.executeUpdate();
		}
	}
	
	public void Logout(Connection conn, String nick) {
		
	}
	
	public int loginatt(Connection conn, String nick, int att) {
		
		String str = "update member set member_st = ? where member_nick = ?";
		PreparedStatement pstmt = null;
		int rs = 0;
		
		try {
			
			pstmt = conn.prepareStatement(str);
			pstmt.setInt(1, att);
			pstmt.setString(2,  nick);
			
			rs = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return rs;		
	}

	
	public int delete(Connection conn, String nick) throws SQLException {
		
		String str = "Delete from member where member_nick = ?";
		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = conn.prepareStatement(str);
			pstmt.setString(1,  nick);
			
			int rs = pstmt.executeUpdate();
			
			return rs;			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return 0;
	}
	
	public void update(Connection conn, Member member)
			throws SQLException {
		String sql = "UPDATE member SET member_nick=?, passwd=?, email=?, phone=?"
				+ " WHERE member_id=?";
		
		try (PreparedStatement pstmt = conn
				.prepareStatement(sql)) {

			pstmt.setString(1, member.getMem_nick());
			pstmt.setString(2, member.getMem_pwd());
			pstmt.setString(3,  member.getEmail());
			pstmt.setString(4,  member.getPhone());
			pstmt.setString(5, member.getMem_id());
			pstmt.executeUpdate();
		}
	}

}
