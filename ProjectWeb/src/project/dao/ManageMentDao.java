package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import guest.action.GuestModel;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import project.model.*;

public class ManageMentDao {
	
	
	public int UpdateMember(String nick, int att) {
		
		String str = "update member set member_st = ? where member_nick=?";
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			
			pstmt.setInt(1, att);
			pstmt.setString(2,  nick);	
			
			int rt = pstmt.executeUpdate();
			
			return rt;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}	
		
		return 0;		
	}
	
	public ArrayList<Member> getUserList() {
		
		Connection conn = null;
		String str = "select * from member";
		ArrayList<Member> list = new ArrayList<Member>();
		
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(str);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Member member = new Member(
						rs.getInt("member_seq"),
						rs.getString("member_id"),
						rs.getString("member_nick"),
						rs.getString("passwd"),		
						rs.getString("email"),
						rs.getString("phone"),
						rs.getInt("member_st"),
						rs.getInt("member_att"));
				
				list.add(member);
			}
			
		
			return list;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return null;		
	}

}
