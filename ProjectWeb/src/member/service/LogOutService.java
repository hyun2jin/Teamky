package member.service;

import java.sql.Connection;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import project.dao.MemberDao;
import project.model.Member;

public class LogOutService {
	
	private MemberDao memberDao = new MemberDao();
	private UserLogService LogService = new UserLogService();
	Connection conn = null;
	
	public void MemberLogOut(String nick) {
		
		try {
			conn = ConnectionProvider.getConnection();
			
			memberDao.Logout(conn, nick);
			
			String value = nick + "님 로그아웃";
			LogService.SetUserLog(nick, value);			
	

		} catch (Exception e) {
			e.printStackTrace();			
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}		
	}

}
