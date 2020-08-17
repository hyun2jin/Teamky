package board.action;

import java.sql.Connection;
import java.util.ArrayList;

import project.dao.CommentDao;
import project.dao.CommentExDao;

public class CommentService {
	
	private CommentDao	cmDao = new CommentDao();
	private CommentExDao cmExDao = new CommentExDao();
	
	
	
	
	public ArrayList<CommentModel> read(int num) {
		
		Connection conn = null;
		
		try {
			return cmDao.getComment(num);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

		return null;		
	}
	
	
	public int write(CommentModel cmt)
	{
		return cmDao.Write(cmt);
	}
	
	public int deleteAllCmt(int bdnum)
	{
		int rs = cmDao.DelteCmtAll(bdnum);
		System.out.println(rs);
		if(rs == 1)
		{
			return cmExDao.DeletecmAllEx(bdnum);
		}
		
		return 0;
	}
	
	public int delete(int num, int bnum)
	{
		int rs = cmDao.DeleteCm(num, bnum);
		if(rs == 1)
			return cmExDao.DeleteCmAll(bnum, num);

		return 0;
	}
	
	public int update(int boardnum, int cmnum, String cont)
	{
		return cmDao.Update(boardnum, cmnum, cont);		
	}
	
	////////////////////////////////////////////////////////////////
	
	public ArrayList<CommentExModel> readcomm(int num) {
		
		Connection conn = null;
		
		try {
			return cmExDao.getComment(num);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

		return null;		
	}
	
	public int write(CommentExModel cmt)
	{
		return cmExDao.Write(cmt);
	}
	
	public int deleteEx(int bnum, int parent, int cmparent)
	{
		return cmExDao.DeleteCmEx(bnum, parent, cmparent);
	}
	
	public int update(CommentExModel mod)
	{
		return cmExDao.Update(mod);	
	}	
	


}
