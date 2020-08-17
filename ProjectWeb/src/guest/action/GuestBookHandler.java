package guest.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.action.BoardModel;
import mvc.controller.CommandHandler;

public class GuestBookHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/guest/guest.jsp";
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
		
		ArrayList<GuestModel> list = gService.read(1);
		
		if(list != null) {
			
			req.getSession().setAttribute("guestlist", list);
		}
		
	
		
		return FORM_VIEW;
	}	
	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		
		GuestModel gm = new GuestModel();
		
		gm.setGuestNick(req.getParameter("guestbook_id"));
		gm.setGuestCont(req.getParameter("guestbook_content"));
		gm.setGuestImage(req.getParameter("selimage"));
		gm.setGuestPwd(req.getParameter("guestbook_password"));
	
		
		
		gService.Write(gm);	
		
		ArrayList<GuestModel> list = gService.read(1);
		
		if(list != null) {		
			
			req.getSession().setAttribute("guestlist", list);
		}
		

		return "/WEB-INF/guest/guest.jsp"; 
	}	
	
	

}
