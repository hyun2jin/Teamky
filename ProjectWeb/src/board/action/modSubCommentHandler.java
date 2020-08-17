package board.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;
import board.action.CommentExModel;

public class modSubCommentHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/board/commentEx.jsp";
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
		
		String s = (String)req.getParameter("cmindex");
		int sindex = Integer.parseInt(s);
		String s1 = (String)req.getParameter("commnum");
		BoardModel bm = null;		
		HttpSession sess = req.getSession();
		bm = (BoardModel)sess.getAttribute("viewinfo");
		
		
		ArrayList<CommentExModel> list = (ArrayList<CommentExModel>) sess.getAttribute("commentex");
		if(list != null)
		{
			CommentExModel	comm = list.get(sindex);
			sess.setAttribute("modisubcom", comm);
		}
		
		
		String uri = "view.do?boardnum=" + bm.getBoard_num();		
		
		return uri; 
	}

	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		
		HttpSession sess = req.getSession();
		String s = (String)req.getParameter("comment_modify");
		CommentExModel cmmod = null;
		cmmod = (CommentExModel)sess.getAttribute("modisubcom");
		BoardModel bm = null;			
		bm = (BoardModel)sess.getAttribute("viewinfo");
		
		cmmod.setContent(s);		
		cService.update(cmmod);		
		
		String uri = "view.do?boardnum=" + bm.getBoard_num();
		sess.removeAttribute("modisubcom");
		
		
		return uri; 
	}
	
}
