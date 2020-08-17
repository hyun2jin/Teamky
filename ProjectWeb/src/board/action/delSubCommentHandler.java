package board.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;

public class delSubCommentHandler implements CommandHandler{

	private static final String FORM_VIEW = "/WEB-INF/board/commentEx.jsp";
	CommentService	cService = new CommentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// TODO Auto-generated method stub
		return processSubmit(req, res);	
		
	}

	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		
		HttpSession sess = req.getSession();
		ArrayList<CommentExModel> list = (ArrayList<CommentExModel>) sess.getAttribute("commentex");
		BoardModel bm = null;			
		bm = (BoardModel)sess.getAttribute("viewinfo");
		String s = (String)req.getParameter("cmindex");
		int sindex = Integer.parseInt(s);
		
		
		
		
		if(list != null)
		{
			CommentExModel	comm = list.get(sindex);
			
			cService.deleteEx(comm.getComment_num(), comm.getComment_parent(), comm.getComment_commnum());
		}
		
		
		String uri = "view.do?boardnum=" + bm.getBoard_num();		
		
		return uri;	

	}
}
