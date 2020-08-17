package member.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.LoginService;
import mvc.controller.CommandHandler;
import project.model.Member;

public class LoginHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/view/login.jsp";
	private LoginService loginService = new LoginService();
	
	
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
		
		
		Member member = new Member(req.getParameter("id"), 
				req.getParameter("nick"), req.getParameter("password"), 
				req.getParameter("email"), req.getParameter("phone"), 0, 0);

		member = loginService.login(member);
		
	
		HttpSession sess = req.getSession();
		
		if(member != null && member.getMem_st() != 1)
		{
			return "/WEB-INF/view/login.jsp?msg=-1";			
		}
		if(member != null) {
			member.setLogin(true);
			//req.getSession().setAttribute(name, value);
			sess.setAttribute("userinfo",  member);
			
			return "/index.jsp";			
		}
		
		if(member == null)
		{
			return "/WEB-INF/view/login.jsp?msg=-2";		}
		
	
		
		
		sess.setAttribute("userinfo",  member);
	
		return "/WEB-INF/view/login.jsp?msg=-1";
	}

	private String processForm(HttpServletRequest req,
			HttpServletResponse res) {
		
		
		return FORM_VIEW;
	}	

}
