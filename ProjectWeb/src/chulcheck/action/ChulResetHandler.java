package chulcheck.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.controller.CommandHandler;
import project.model.Member;

public class ChulResetHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/chul/chulForm.jsp";
	ChulCheckService	cService = new ChulCheckService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return processForm(req, res);
	}
	
	private String processForm(HttpServletRequest req,
			HttpServletResponse res) {
		
		cService.Reset();
		
		 ArrayList<ChulCheckModel> list = cService.GetList();
		 req.getSession().setAttribute("chullist", list);
		 
		 Member mem = null; 
		mem = (Member)req.getSession().getAttribute("userinfo");
		if(mem != null)
		{
			int check = cService.TodayCheck(mem.getMem_nick());
			
			if(check >= 1)
				req.getSession().setAttribute("check1", 0);
		}				 
		
		return FORM_VIEW;		
	
	}

}
