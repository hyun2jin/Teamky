package member.command;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.LoginService;
import member.service.UserLogService;
import mvc.controller.CommandHandler;
import project.model.UserLogModel;

public class UserLogHandler implements CommandHandler{
	
	
	private static final String FORM_VIEW = "/WEB-INF/member/userlog.jsp";
	private UserLogService LogService = new UserLogService();

	
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
		
	
		
		ArrayList<UserLogModel> list = LogService.GetList();
		req.getSession().setAttribute("userlog", list);
		
				
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		

		
		return FORM_VIEW;
	}
	
}
