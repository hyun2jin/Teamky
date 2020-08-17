package board.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;

public class BoardManageHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/member/boardmanage.jsp";
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
	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		
		HttpSession sess = req.getSession();
		
		String pg = (String)req.getParameter("page");
		int page = 1;
		if(pg != null)
		{
			page = Integer.parseInt(pg);			
		}
		
		ArrayList<BoardModel> list = bService.read(page);
		
	
		sess.removeAttribute("managerlist");
		sess.setAttribute("managerlist", list);
		
		int pagecount = bService.getBcount();
		sess.setAttribute("pagecount",  pagecount);
		
		
		return FORM_VIEW;
	}
	
	private String processForm(HttpServletRequest req,
			HttpServletResponse res) {
		
	HttpSession sess = req.getSession();
		
		String pg = (String)req.getParameter("page");
		int page = 1;
		if(pg != null)
		{
			page = Integer.parseInt(pg);			
		}
		
		ArrayList<BoardModel> list = bService.read(page);
		
	
		sess.removeAttribute("managerlist");
		sess.setAttribute("managerlist", list);
		
		int pagecount = bService.getBcount();
		sess.setAttribute("pagecount",  page);
		
		
		return FORM_VIEW;
	}	

}
