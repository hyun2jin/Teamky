package chulcheck.action;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import project.dao.ChulCheckDao;

public class ChulCheckService {
	
	public ChulCheckDao		checkDao = new ChulCheckDao();
	
	
	public ArrayList<ChulCheckModel> GetList() {
		
		Connection conn = null;
		
		try {
			return checkDao.getChullist();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

		return null;		
	}
	
	
	public int TodayCheck(String Nick) {
		
		return checkDao.GetTodayEnd(Nick);		
	}
	
	public int Reset()
	{
		return checkDao.Reset();
	}
	
	public int Write(ChulCheckModel cm) {
		
		Connection conn = null;
		
		try {			
			conn = ConnectionProvider.getConnection();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		cm.setChul_no(checkDao.GetLastNum());
		cm.setChul_date(checkDao.getDate(conn));
		
		
		
		return checkDao.Write(cm);
	}
	
	public boolean isToday(Calendar wdate) {
		
		Calendar now = Calendar.getInstance();
		
		if(now.get(Calendar.YEAR) == wdate.get(Calendar.YEAR) &&
			now.get(Calendar.MONTH) == wdate.get(Calendar.MONTH) &&
			now.get(Calendar.DATE) == wdate.get(Calendar.DATE))
			return true;
		else
			return false;				
	}

}
