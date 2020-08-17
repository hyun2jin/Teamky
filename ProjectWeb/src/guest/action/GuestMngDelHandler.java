package guest.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.controller.CommandHandler;

public class GuestMngDelHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/member/guestmng.jsp";
	GuestService	gService = new GuestService();

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
		
		String bnum = req.getParameter("gnum");
		int num = Integer.parseInt(bnum);
		
		gService.Delete(num);
		
		ArrayList<GuestModel> list = gService.read(1);
		
		if(list != null) {
			
			req.getSession().setAttribute("guestlist", list);
		}
				
		
		
		return FORM_VIEW;
	}

}
