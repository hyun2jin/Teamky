package member.service;

import java.net.InetAddress;
import java.util.ArrayList;

import project.dao.UserHistoryDao;
import project.model.UserLogModel;

public class UserLogService {
	
	private UserHistoryDao UserDao = new UserHistoryDao();
	
	public void SetUserLog(String nick, String cont)
	{
		UserLogModel md = new UserLogModel();
		String ip = "";
		
		try {
			InetAddress local = InetAddress.getLocalHost();
			ip = local.getHostAddress();			
			
		} catch (Exception e) {
			// TODO: handle exception
		}	


		md.setIp(ip);		
		md.setNick(nick);
		md.setCont(cont);
		
		UserDao.InsertLog(md);
	}
	
	public ArrayList<UserLogModel> GetList() {
		
		return UserDao.GetList();		
	}

}
