package guest.action;


import java.sql.Connection;
import java.util.ArrayList;

import board.action.CommentModel;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import project.dao.GuestDao;

public class GuestService {
	
	private GuestDao	guDao = new GuestDao();
	
	public ArrayList<GuestModel> read(int page) {
		
		Connection conn = null;
		
		try {
			return guDao.getList(page);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

		return null;		
	}
	
	
	public int Write(GuestModel gm)
	{
		Connection conn = null;
		
		try {

			conn = ConnectionProvider.getConnection();
					
			return guDao.Write(conn, gm.getGuestNick(), gm.getGuestPwd(), gm.getGuestCont(), gm.getGuestImage());			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return 0;		
	}
	
	public int Delete(int num)
	{
		return guDao.Delete(num);
	}

}
