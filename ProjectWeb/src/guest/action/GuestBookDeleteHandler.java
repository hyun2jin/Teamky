package guest.action;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.controller.CommandHandler;

public class GuestBookDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		return processSubmit(req, res);
	}

	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException {
		
		GuestService	gService = new GuestService();
		
		String ns = req.getParameter("delnum");
		int num = Integer.parseInt(ns);
		
		gService.Delete(num);
		
		ArrayList<GuestModel> list = gService.read(1);
		
		if(list != null) {
			
			req.getSession().setAttribute("guestlist", list);
		}
		

		return "/WEB-INF/guest/guest.jsp"; 
	}
}
