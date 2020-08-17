package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;

public class BoardManageLockHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/member/boardmanage.jsp";
	BoardService	bService = new BoardService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return processSubmit(req, res);
	}
	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) {
		
		String bnum = req.getParameter("boardnum");
		bnum = bnum.replace("/", "");
		HttpSession sess = req.getSession();
		int no = Integer.parseInt(bnum);
		
		bService.SetLock(no, true);
		
		/////////////////////////////////////////////
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
		/////////////////////////////////////////////
		
		return FORM_VIEW;	
	}

}
