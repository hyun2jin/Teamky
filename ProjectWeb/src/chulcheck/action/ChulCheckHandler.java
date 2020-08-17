package chulcheck.action;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guest.action.GuestService;
import mvc.controller.CommandHandler;
import project.model.Member;

public class ChulCheckHandler implements CommandHandler{

	private static final String FORM_VIEW = "/WEB-INF/chul/chulForm.jsp";
	ChulCheckService	cService = new ChulCheckService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub

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
		
		 ArrayList<ChulCheckModel> list = cService.GetList();
		 req.getSession().setAttribute("chullist", list);
		 
		 Member mem = null; 
		mem = (Member)req.getSession().getAttribute("userinfo");
		
	
		
		if(mem != null)
		{
			int check = cService.TodayCheck(mem.getMem_nick());
			if(check >= 1)
				req.getSession().setAttribute("check1", 1);
		}				 
		
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) {
		
		String ipAddress = null;
		
		String id = req.getParameter("name");
		String cont = req.getParameter("content");
		
		ChulCheckModel cm = new ChulCheckModel();
		
		
	
		
		try {

			ipAddress=req.getRemoteAddr();
			if(ipAddress.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
			    InetAddress inetAddress=InetAddress.getLocalHost();
			    ipAddress=inetAddress.getHostAddress();	
			}			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		cm.setChul_id(id);
		cm.setChul_cont(cont);
		cm.setChul_ip(ipAddress);		
		

		int rs = cService.Write(cm);
		
		
		ArrayList<ChulCheckModel> list = cService.GetList();
		req.getSession().setAttribute("chullist", list);
		
		
		int check = cService.TodayCheck(id);
		if(check >= 1)
			req.getSession().setAttribute("check1", 1);
		
		System.err.println(id + "  " + check);
		
		
		return FORM_VIEW;
	}
		
	

}
