package project.model;


public class Member {
	
	
    private int memberNo;
    private String mem_id;
    private String mem_nick;
    private String mem_pwd;
    private String email;
    private String phone;
 	private int	mem_st;
    private int mem_att;
    private boolean isLogin;
    
    public Member(String id, String nick, String pwd, String email, String phone, int st, int att) {
    	
    	this.mem_id = id;
    	this.mem_nick = nick;
    	this.mem_pwd = pwd;
    	this.email = email;
    	this.phone = phone;    	
    	this.mem_st = st;
    	this.mem_att = att;
    	isLogin = false;
    }
    
    public Member(Integer seq, String id, String nick, String pwd, String email, String phone, int st, int att) {
    	
    	this.memberNo = seq;
    	this.mem_id = id;
    	this.mem_nick = nick;
    	this.mem_pwd = pwd;
    	this.email = email;
    	this.phone = phone;
    	this.mem_st = st;
    	this.mem_att = att;
    	isLogin = false;
    }
    
    
    public boolean isLogin() {
		return isLogin;
	}

	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	boolean isAdmin() {
    	return mem_att == 2 ? true : false;
    }
    
    
    boolean isComparePwd(String pwd) {
    	
    	if(pwd == null || mem_pwd == null)
    		return false;
    	if(pwd.isEmpty() || mem_pwd.isEmpty())
    		return false;
    	
    	return mem_pwd.equals(pwd) ? true : false;
    }
    
    
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_nick() {
		return mem_nick;
	}
	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}
	public String getMem_pwd() {
		return mem_pwd;
	}
	public void setMem_pwd(String mem_pwd) {
		this.mem_pwd = mem_pwd;
	}
	public int getMem_st() {
		return mem_st;
	}
	public void setMem_st(int mem_st) {
		this.mem_st = mem_st;
	}
	public int getMem_att() {
		return mem_att;
	}
	public void setMem_att(int mem_att) {
		this.mem_att = mem_att;
	}
    
   public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}    
	

}
