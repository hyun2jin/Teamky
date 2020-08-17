package board.action;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.controller.CommandHandler;
import project.model.Member;

public class BoardWriteCommentHandler implements CommandHandler{

	
	CommentService	cService = new CommentService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		
		return processSubmit(req, res);	
		
	
	}
	
	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) {
		
		HttpSession sess = req.getSession();
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy년MM월dd일");
		Date time = new Date();
		String time1 = format1.format(time);
		
		String cont1 = (String)req.getParameter("comment_content");
		Member mem = (Member)sess.getAttribute("userinfo");
		BoardModel bm = (BoardModel)sess.getAttribute("viewinfo");
		int bnum = bm.getBoard_num();
		
		
	
		
		CommentModel cmt = new CommentModel();
		
		cmt.setComment_nick(mem.getMem_nick());
		cmt.setComment_parent(bnum);
		cmt.setComment_board(bnum);
		cmt.setContent(cont1);
		cmt.setDate(time1);
	
		int result = cService.write(cmt);
		
	
		
		String uri = "view.do?boardnum=" + bnum;
		
		return uri;
	
	}

}
