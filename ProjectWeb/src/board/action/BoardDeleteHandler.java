package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import mvc.controller.CommandHandler;
import project.model.Member;

public class BoardDeleteHandler implements CommandHandler{

	private static final String FORM_VIEW = "/WEB-INF/board/delete.jsp";
	BoardService	bService = new BoardService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		return processSubmit(req, res);		
	}
	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) {
		
		HttpSession sess = req.getSession();
		
		
		Member mem = null;
		BoardModel bm = null;
		
		mem = (Member)sess.getAttribute("userinfo");
		bm = (BoardModel)sess.getAttribute("viewinfo");
		
			
		if(mem == null || bm == null)
			return "";
		
		if(!mem.getMem_nick().equals(bm.getBoard_id()))
			return "";
		
				
		String num = (String)req.getParameter("boardnum");
		int bnum = Integer.parseInt(num);
		String result = bService.delete(bnum);
		
		return "/index.jsp"; 
	}		

}
