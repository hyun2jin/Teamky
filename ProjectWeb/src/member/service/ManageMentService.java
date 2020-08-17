package member.service;

import java.util.ArrayList;

import project.dao.ManageMentDao;
import project.model.Member;

public class ManageMentService {

	
	ManageMentDao Mdao = new ManageMentDao();
	
	
	public ArrayList<Member> getList() {
		
		return Mdao.getUserList();	
	}
	
	public int UpdateMember(String nick, int att) {
		
		return Mdao.UpdateMember(nick, att);
	}
	
}
