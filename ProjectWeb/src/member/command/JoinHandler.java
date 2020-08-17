package member.command;

import mvc.controller.CommandHandler;
import project.model.Member;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
import member.service.*;

public class JoinHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "/WEB-INF/view/member.jsp";
	private JoinService joinService = new JoinService();
	
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
		
		String m1 = req.getParameter("mail1");
		String m2 = req.getParameter("mail2");
		String email = m1 + "@" + m2;
		
		
		Member member = new Member(req.getParameter("id"), 
				req.getParameter("nick"), req.getParameter("password"), 
				email, req.getParameter("phone"), 0, 0);

		joinService.join(member);
		
		
		req.setAttribute("usernick", member.getMem_nick());		
	
		return "/WEB-INF/view/joinSuccess.jsp";
	}

	private String processForm(HttpServletRequest req,
			HttpServletResponse res) {
		
		return FORM_VIEW;
	}	

}
