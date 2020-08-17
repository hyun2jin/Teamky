package board.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;

public class BoardUpdateHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/board/update.jsp";
	BoardService	bService = new BoardService();

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
		
		HttpSession sess = req.getSession();
		
		String num = (String)req.getParameter("boardnum");
		int bnum = Integer.parseInt(num);
		BoardModel	bm = bService.view(bnum);		
		sess.setAttribute("boardnum", bm);		
		
		
		return FORM_VIEW;
	}	

	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		
		BoardModel	bm = null;
		bm = new BoardModel();
		
		HttpSession sess = req.getSession();
		bm = (BoardModel)sess.getAttribute("boardnum");

		String title = req.getParameter("bbsTitle");
		String content = req.getParameter("bbsContent");
		
		bm.setBoard_title(title);
		bm.setBoard_content(content);		
		
		
		int rs = bService.update(bm);
		sess.setAttribute("boardnum", bm);
		
		String uri = "view.do?boardnum=" + bm.getBoard_num();
		
		return uri; 
	}	
	
}
