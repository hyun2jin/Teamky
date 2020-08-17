package member.command;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.JoinService;
import member.service.ManageMentService;
import mvc.controller.CommandHandler;
import project.model.Member;

public class MemberDeleteHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/member/manage.jsp";
	private JoinService jService = new JoinService();
	private ManageMentService	mService = new ManageMentService();

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
		
		String nick = req.getParameter("nick");
		nick = nick.replace("/", "");
	
		jService.DelMember(nick);
		
		ArrayList<Member> list = mService.getList();		
		
		if(list != null && list.size() > 0)
		{
			for(int i=0;i<list.size();i++)
			{
				Member mem = list.get(i);
				
				if(mem != null)
				{
					//관리자는 빼자
					if(mem.getMem_att() == 1)
					{
						list.remove(i);
					}
				}
			}
			
			req.getSession().setAttribute("memlist", list);
		}		
		
		
		return FORM_VIEW;
	}
	
	private String processForm(HttpServletRequest req,
			HttpServletResponse res) {
		
	
		return "membermana.do";
	}	
	
	

}
