package board.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import mvc.controller.CommandHandler;
import project.model.Member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import javax.servlet.http.Part;

import com.mysql.cj.Session;


public class BoardWriteHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/board/write.jsp";
	BoardService	bService = new BoardService();
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
	
	private String printPartInfo(HttpServletRequest req,
			PrintWriter writer, String no)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Collection<Part> parts = req.getParts();
		req.setCharacterEncoding("UTF-8");
		
		String imgsrc = "<br />";
		
		
		for (Part part : parts) {
			writer.println("<br> name = " + part.getName());
			String contentType = part.getContentType();
			writer.println("<br> contentType = " + contentType);		
			

			if (part.getHeader("Content-Disposition")
					.contains("filename=")) {
				writer.println("<br> size = " + part.getSize());
				String fileName = part.getSubmittedFileName();
				
				
				
				if(fileName.isEmpty())
					return null;
				
				
				int pos = fileName.lastIndexOf( "." );
				String fileExt = fileName.substring( pos + 1 );
				

				String t1 = null;
				
				if(fileExt.equals("mp4"))
					t1 = "<embed src=\"/images/" + no + "/" + fileName + "\">" + "<br />";
				else				
					t1 = "<img src=\"/images/" + no + "/" + fileName + "\">" + "<br />";
				
				imgsrc += t1;
				
				
				writer.println("<br> filename = " + fileName);
				
				String path = "c:\\tempfiles\\" + no + "\\";
				File fp = new File(path);
				fp.mkdirs();
				if (part.getSize() > 0) {
					
					part.write( path + fileName);
					part.delete();
				}
			} else {
				String value = req.getParameter(part.getName());
				writer.println("<br> value = " + value);
			}

			writer.println("<hr>");
		}
		
		return imgsrc;
	}	
	
	public String FileUpload(HttpServletRequest request, HttpServletResponse response, String no) 
											throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html; charset=UTF-8");			
		
		PrintWriter writer = response.getWriter();
		
		writer.println("<html><body>");
		String contentType = request.getContentType();
		
		String conex = null;
		

		if (contentType != null && contentType.toLowerCase()
				.startsWith("multipart/")) {

			conex = printPartInfo(request, writer, no);
		} else {
			writer.println("multipart媛� �븘�떂");
		}
		writer.println("</body></html>");
		
		
		
		return conex;				
	}
	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws SQLException, ServletException, IOException {
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy년MM월dd일");
		Date time = new Date();
	
		HttpSession sess = req.getSession();
		Member mem = null;
		mem = (Member)sess.getAttribute("userinfo");
		
		if(mem == null)
			return "";
		
		String d1 = req.getParameter("editordata");
				
	
		String id = mem.getMem_nick();
		String title = req.getParameter("bbsTitle");
		String content = req.getParameter("bbsContent");
		String time1 = format1.format(time);
		////////////////////////////////////////////
		
		String no = String.valueOf(bService.GetNextNumber());
		String conex = FileUpload(req, res, no);
		//////////////////////////////////////////
		if(conex != null)
			content += conex;
		
	
		BoardModel bModel = new BoardModel(0, id, title, content, time1, 0, false); 		
		if( bService.insert(bModel) == 1)
			return "/index.jsp";		
			
		return "/index.jsp"; 
	}

	private String processForm(HttpServletRequest req,
			HttpServletResponse res) {
		
		try {
			
			res.setCharacterEncoding("UTF-8"); 
			res.setContentType("text/html; charset=UTF-8");			
			
		} catch (Exception e) {
			// TODO: handle exception
		}		
		
		return FORM_VIEW;
	}	
	
	
	
}
