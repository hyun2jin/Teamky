package guest.action;

public class GuestModel {
	
	private int 	guestNo;
	private String	guestNick;
	private String	guestPwd;
	private	String	guestCont;
	private	String	guestImage;
	private String	gueestDate;
	
	
	boolean IsMatchPwd(String pwd) {
		
		if(guestPwd.equals(pwd))
			return true;
			
		return false;
	}

	
	public String getGuestImage() {
		return guestImage;
	}


	public void setGuestImage(String guestImage) {
		this.guestImage = guestImage;
	}
	

	public int getGuestNo() {
		return guestNo;
	}


	public void setGuestNo(int guestNo) {
		this.guestNo = guestNo;
	}


	public String getGuestNick() {
		return guestNick;
	}


	public void setGuestNick(String guestNick) {
		this.guestNick = guestNick;
	}


	public String getGuestPwd() {
		return guestPwd;
	}


	public void setGuestPwd(String guestPwd) {
		this.guestPwd = guestPwd;
	}


	public String getGuestCont() {
		return guestCont;
	}


	public void setGuestCont(String guestCont) {
		this.guestCont = guestCont;
	}


	
	public String getGueestDate() {
		return gueestDate;
	}


	public void setGueestDate(String gueestDate) {
		this.gueestDate = gueestDate;
	}
	
	

}
