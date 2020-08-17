package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import project.dao.MemberDao;
import project.dao.UserHistoryDao;
import project.model.Member;
import project.model.UserLogModel;

public class LoginService {
	
	private MemberDao memberDao = new MemberDao();
	private UserLogService uService = new UserLogService(); 
	
	public Member login(Member loginReq) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			
			Member member = memberDao.selectById(conn, loginReq.getMem_id());
			if (member != null) {
			
				if(loginReq.getMem_pwd().equals(member.getMem_pwd()))				
				{
					String value = member.getMem_id() + "님 로그인";
					uService.SetUserLog(member.getMem_nick(), value);				
					return member;				
				}
				else
					return null;				
			}		

		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		return null;
	}
	

}
