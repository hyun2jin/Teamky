package board.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;

public class delMngSubCommentHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/member/mngComment.jsp";
	CommentService	cService = new CommentService();
	


	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return processSubmit(req, res);		
	}

	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		
		HttpSession sess = req.getSession();
	
		
		String sboard = req.getParameter("boardnum");
		String sComm = req.getParameter("comparent");
		String sComnum = req.getParameter("comnum");
		
		sboard = sboard.replace("/", "");
		sComm = sComm.replace("/", "");
		sComnum = sComnum.replace("/", "");
		
		int board = Integer.parseInt(sboard);
		int Commp = Integer.parseInt(sComm);
		int comnum = Integer.parseInt(sComnum);

		System.out.println(comnum);
		System.out.println(board);
		System.out.println(Commp);
		cService.deleteEx(comnum, board, Commp);
		
		ArrayList<CommentModel> list = cService.read(board);
		ArrayList<CommentExModel> listcm = cService.readcomm(board);
		
		req.getSession().setAttribute("comment", list);
		req.getSession().setAttribute("commentex", listcm);		
		
		
	return FORM_VIEW; 
	}			
		
}
