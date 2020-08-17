package project.model;

import java.net.InetAddress;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class UserLogModel {

	private int num;
	private String Nick;
	private String Cont;
	private String Date;
	private String ipAddress;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getNick() {
		return Nick;
	}
	public void setNick(String nick) {
		Nick = nick;
	}
	public String getCont() {
		return Cont;
	}
	public void setCont(String cont) {
		Cont = cont;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getIp() {
		return ipAddress;
	}
	public void setIp(String ip) {
		this.ipAddress = ip;
	}
	
	
	
}
