package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import project.dao.*;
import project.model.*;

public class JoinService {

	private MemberDao memberDao = new MemberDao();
	private UserLogService LogService = new UserLogService();
	
	
	
	public int CheckId(String id) {
		return memberDao.CheckId(id);
	}
	
	public int CheckNick(String nick) {
		return memberDao.CheckNick(nick);
	}
	
	
	public void Modify(Member modify) {
		
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDao
					.selectById(conn, modify.getMem_id());
			if (member == null) {
				JdbcUtil.rollback(conn);
				throw new SQLException();
			}
			
			memberDao.update(conn, new Member(
					modify.getMem_id(),
					modify.getMem_nick(),
					modify.getMem_pwd(),
					modify.getEmail(),
					modify.getPhone(),
					modify.getMem_st(),
					modify.getMem_att()));
			
		
			conn.commit();
			
			String value = modify.getMem_id() + "님 회원정보 수정";
			LogService.SetUserLog(modify.getMem_nick(), value);

		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}		
		
		
	}
	
	
	public int LoginAtt(String nick, int att) {
		
		Connection conn = null;
		int rs = 0;
		
		try {
			
			conn = ConnectionProvider.getConnection();
			rs = memberDao.loginatt(conn, nick, att);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtil.close(conn);
		}
		
		return rs;
	}
	
	
	
	public int DelMember(String nick) {
		
		Connection conn = null;
		int rs = 0;

		try {
			conn = ConnectionProvider.getConnection();			
			rs = memberDao.delete(conn, nick);
			
			String value = nick + "님 회원삭제";
			LogService.SetUserLog("관리자", value);	

		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		
		
		return rs;		
	}

	public void join(Member joinReq) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Member member = memberDao
					.selectById(conn, joinReq.getMem_id());
			if (member != null) {
				JdbcUtil.rollback(conn);
				throw new SQLException();
			}
			
			memberDao.insert(conn, new Member(
					joinReq.getMem_id(),
					joinReq.getMem_nick(),
					joinReq.getMem_pwd(),
					joinReq.getEmail(),
					joinReq.getPhone(),
					joinReq.getMem_st(),
					joinReq.getMem_att()));
			
			
			conn.commit();
			
			String value = joinReq.getMem_id() + "님 회원가입";
			LogService.SetUserLog(joinReq.getMem_nick(), value);	

		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	
}
