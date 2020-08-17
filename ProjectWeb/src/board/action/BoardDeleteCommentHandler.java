package board.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;

public class BoardDeleteCommentHandler implements CommandHandler{

	
	CommentService	cService = new CommentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		
		return processSubmit(req, res);		
	}
	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		
		HttpSession sess = req.getSession();
		BoardModel bm = null;
		
		bm = (BoardModel)sess.getAttribute("viewinfo");
				
	
		String num = (String)req.getParameter("cnum");
		int bnum = Integer.parseInt(num);
		cService.delete(bnum, bm.getBoard_num());		
				
		String uri = "view.do?boardnum=" + bm.getBoard_num();
		
				
		return uri; 
	}	

}
