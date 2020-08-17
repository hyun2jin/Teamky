package board.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;
import project.model.Member;

public class addCommentHandler implements CommandHandler{

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
		
		BoardModel bm = null;		
		HttpSession sess = req.getSession();
		bm = (BoardModel)sess.getAttribute("viewinfo");
		
		String cmnum = (String)req.getParameter("cmnum");		
		sess.setAttribute("addcommentex", cmnum);
		
		String uri = "view.do?boardnum=" + bm.getBoard_num();
		
		
		return uri; 
	}
	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy년MM월dd일");
		Date time = new Date();
		String time1 = format1.format(time);
		
		/////////////////////
		BoardModel bm = null;
		HttpSession sess = req.getSession();
		bm = (BoardModel)sess.getAttribute("viewinfo");
		Member mem = (Member)sess.getAttribute("userinfo");		
		
		CommentExModel	cmt = new CommentExModel();
		int bnum = bm.getBoard_num();
		
		String scommnum = (String)sess.getAttribute("addcommentex");
		sess.removeAttribute("addcommentex");
		int commnum = Integer.parseInt(scommnum);
				
		
		
		cmt.setComment_nick(mem.getMem_nick());
		cmt.setComment_parent(bnum);
		cmt.setComment_board(bnum);
		cmt.setContent(req.getParameter("comment_addreply"));
		cmt.setDate(time1);
		cmt.setComment_commnum(commnum);
		
		cService.write(cmt);
		
		String uri = "view.do?boardnum=" + bm.getBoard_num();					
		return uri; 
	}	
		
}
