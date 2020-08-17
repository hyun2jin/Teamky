package member.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.java.swing.plaf.windows.resources.windows;

import member.service.JoinService;
import mvc.controller.CommandHandler;
import project.model.Member;

public class CheckMemIdHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/view/member.jsp";
	private JoinService joinService = new JoinService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		
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
		
		
	
		return FORM_VIEW;
	}

	private String processForm(HttpServletRequest req,
			HttpServletResponse res) {
		
		String s1 = req.getParameter("id");
		String s2 = req.getParameter("nick");
		String s3 = req.getParameter("password");
		String s4 = req.getParameter("mail1");
		String s5 = req.getParameter("phone");
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);

		
		Member member = new Member(s1, s2, s3, s4, s5, 0, 0);
		req.getSession().setAttribute("memberinfo", member);
		
		int rs = joinService.CheckId(member.getMem_id());
		if(rs == 0)
			req.getSession().setAttribute("resultmsgid", "사용가능한 아이디입니다");
		else
			req.getSession().setAttribute("resultmsgid", "사용중인 아이디입니다");			
		
		
		return FORM_VIEW;
	}		

}
