package board.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;

public class BoardViewHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/board/view.jsp";
	BoardService	bService = new BoardService();
	CommentService	cService = new CommentService();
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return processSubmit(req, res);
	} 
	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		
	
		//String pg = (String)sess.getAttribute("page");
		String num = (String)req.getParameter("boardnum");
		int bnum = Integer.parseInt(num);
		BoardModel bModel = bService.view(bnum);
		
		
		HttpSession sess = req.getSession();
		sess.setAttribute("viewinfo", bModel);
		
		
		
		////////////////////

		ArrayList<CommentModel> list = cService.read(bnum);
		ArrayList<CommentExModel> listcm = cService.readcomm(bnum);
	
	
		sess.setAttribute("comment", list);
		sess.setAttribute("commentex", listcm);
		
		
		
			
				
		
		return "/WEB-INF/board/view.jsp"; 
	}
	

}
