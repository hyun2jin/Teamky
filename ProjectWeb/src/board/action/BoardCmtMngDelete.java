package board.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;

public class BoardCmtMngDelete implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/member/mngComment.jsp";
	private CommentService	cService = new CommentService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//TODO Auto-generated method stub
		return processSubmit(req, res);		
	}

	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		
		HttpSession sess = req.getSession();
	
		
		String sboard = req.getParameter("boardnum");
		String sComm = req.getParameter("comnum");
		
		sboard = sboard.replace("/", "");
		sComm = sComm.replace("/", "");
		
		int board = Integer.parseInt(sboard);
		int Comm = Integer.parseInt(sComm);

		cService.delete(Comm, board);
		
		ArrayList<CommentModel> list = cService.read(board);
		ArrayList<CommentExModel> listcm = cService.readcomm(board);
		
		req.getSession().setAttribute("comment", list);
		req.getSession().setAttribute("commentex", listcm);
		
	
				
		return FORM_VIEW; 
	}			
	
}
