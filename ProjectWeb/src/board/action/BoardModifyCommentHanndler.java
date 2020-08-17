package board.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;

public class BoardModifyCommentHanndler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/board/comment";
	CommentService	cService = new CommentService();
	
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// TODO Auto-generated method stub
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}	
	}
	
	
	private String processForm(HttpServletRequest req,
			HttpServletResponse res) {
		
		BoardModel bm = null;		
		HttpSession sess = req.getSession();
		String num = (String)req.getParameter("mnum");
		int mnum = Integer.parseInt(num);
		bm = (BoardModel)sess.getAttribute("viewinfo");
		sess.setAttribute("modifycomment", mnum);	
		sess.setAttribute("modifyuser", bm.getBoard_id());	
		
	
		
		String uri = "view.do?boardnum=" + bm.getBoard_num();
		
		
		return uri; 
	}	

	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		
		HttpSession sess = req.getSession();
		BoardModel bm = null;
		
		bm = (BoardModel)sess.getAttribute("viewinfo");	
		int mm = (int)sess.getAttribute("modifycomment");
		String cont = req.getParameter("comment_modify");
		
		int rs = cService.update(bm.getBoard_num(), mm, cont);
		
		
	
		String uri = "view.do?boardnum=" + bm.getBoard_num();
		
		sess.removeAttribute("modifyuser");
				
		return uri; 
	}	
	
	
}
