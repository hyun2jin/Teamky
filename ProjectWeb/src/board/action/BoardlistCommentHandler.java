package board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.descriptor.web.ContextService;

import mvc.controller.CommandHandler;


public class BoardlistCommentHandler implements CommandHandler{

	CommentService	cService = new CommentService();	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
	
		return processSubmit(req, res);		
	}
		
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) {
		
		HttpSession sess = req.getSession();
		
				
		
		String num = (String)req.getParameter("boardnum");
		int bnum = Integer.parseInt(num);
		ArrayList<CommentModel> list = cService.read(bnum);
		
		sess.setAttribute("comment", list);
		
		
		return "/comment.jsp";	
	}
	
	

}
