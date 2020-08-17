package board.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;

public class BoardCommentMngHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/member/mngComment.jsp";
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
		
		String bm = req.getParameter("boardnum");
		req.getSession().setAttribute("cmboard", bm);
		
		int bnum = Integer.parseInt(bm);
		ArrayList<CommentModel> list = cService.read(bnum);
		ArrayList<CommentExModel> listcm = cService.readcomm(bnum);
		
		req.getSession().setAttribute("comment", list);
		req.getSession().setAttribute("commentex", listcm);
		
		
		return FORM_VIEW;	
	}
	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
				
		return ""; 
	}		

}
