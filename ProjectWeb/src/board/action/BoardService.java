package board.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import project.dao.BoardDao;


public class BoardService {
	
	private BoardDao boardDao = new BoardDao();
	private CommentService	cService = new CommentService();
	
	public int GetNextNumber() {
		
		Connection conn = null;
		
		try {
			
			conn = ConnectionProvider.getConnection();			
			return boardDao.GetNextNum(conn);
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtil.close(conn);
		}
	
		return 0;
	}
	
	public int insert(BoardModel board) {
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			
			
			int rs = boardDao.Write(conn, board.getBoard_title(), board.getBoard_id(), board.getBoard_content());
			
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return 0;
	}
	
	
	public int SetLock(int no, boolean lock) {
		
		Connection conn = null;
		int rs = 0;
		
		try {
			
			conn = ConnectionProvider.getConnection();
			rs = boardDao.SetLock(conn, no, lock);			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtil.close(conn);			
		}
		
		return rs;
	}
	
	public int update(BoardModel board) {
		
		Connection conn = null;

		try {
			conn = ConnectionProvider.getConnection();
			
			
			int rs = boardDao.Update(conn, board.getBoard_num(), board.getBoard_title(), board.getBoard_content());
			
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return 0;		
	}
	
	public int getBcount() {
		
		return boardDao.getListCount();
	}
	
///////////////////////////////////////////////
	public ArrayList<BoardModel> read(int Page) {
		
		Connection conn = null;
		
		try {
			return boardDao.getList(Page);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;		
	}
	/////////////////////////////////////////////////
	public int delete(int num)
	{
		String rs = null;
		rs = boardDao.Delete(num);
		if(rs == "1")
		{
			return cService.deleteAllCmt(num);
		}
		
		return 0;
	}
	
	
	/////////////////////////////////////////////////
	public BoardModel view(int num) {
		
		try {
			return boardDao.viewBoard(num);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return null;
	}
	
	

}
