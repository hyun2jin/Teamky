package member.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chulcheck.action.ChulCheckService;
import member.service.JoinService;
import mvc.controller.CommandHandler;
import project.model.Member;

public class ModifyInfoHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/view/modifyinfo.jsp";
	private JoinService jService = new JoinService();

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
				

		return FORM_VIEW;
	}	

	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		
		String m1 = req.getParameter("mail1");
		String m2 = req.getParameter("mail2");
		String email = m1 + "@" + m2;
		
		
		Member modify = new Member(req.getParameter("id"), 
				req.getParameter("nick"), req.getParameter("password"), 
				email, req.getParameter("phone"), 0, 0);


		jService.Modify(modify);
		req.getSession().setAttribute("userinfo",  modify);
		
		return "";
	
	}
	
}
