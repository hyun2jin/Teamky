package board.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import mvc.controller.CommandHandler;
import project.dao.BoardDao;
import project.model.Member;

public class BoardListHandler implements CommandHandler{

	
	private static final String FORM_VIEW = "/WEB-INF/board/boardlist.jsp";
	BoardService	bService = new BoardService(); 
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		return processSubmit(req, res);
		
		/*
		
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
		*/			
	}
	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		
		HttpSession sess = req.getSession();
		
		String pg = (String)req.getParameter("page");
		int page = 1;
		if(pg != null)
		{
			page = Integer.parseInt(pg);
			if(page == 0)
				page = 1;
		}
		
		sess.removeAttribute("boardlist");
		ArrayList<BoardModel> list = bService.read(page);		
		sess.setAttribute("boardlist", list);
		
		
		
		int pagecount = bService.getBcount();
		sess.setAttribute("pagecount",  pagecount);
		
		
		return "/WEB-INF/board/boardlist.jsp"; 
	}

	private String processForm(HttpServletRequest req,
			HttpServletResponse res) {
		
		
		return FORM_VIEW;
	}	

}


/*
	BoardModel 	bModel = new BoardModel(0, req.getParameter("id"), req.getParameter("title"), 
				req.getParameter("content"), "", 0);

		
		if( bService.insert(bModel) == 1)
			return "/WEB-INF/board/boardsucess.jsp";			
	
		return "/WEB-INF/board/boardlist.jsp"; 
*/