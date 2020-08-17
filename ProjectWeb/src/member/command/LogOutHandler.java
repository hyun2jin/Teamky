package member.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.LogOutService;
import mvc.controller.CommandHandler;
import project.model.Member;

public class LogOutHandler implements CommandHandler{

	private static final String FORM_VIEW = "/WEB-INF/view/logout.jsp";
	private LogOutService logOutService = new LogOutService();
	
	
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
		

		String nick = (String)req.getParameter("name");
		
		HttpSession sess = req.getSession();
		sess.invalidate();
		
		logOutService.MemberLogOut(nick);
		
		
		return "/index.jsp";		
	}

	private String processForm(HttpServletRequest req,
			HttpServletResponse res) {
		
		return FORM_VIEW;
	}	
	

}
