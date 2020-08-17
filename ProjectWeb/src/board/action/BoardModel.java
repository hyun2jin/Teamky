package board.action;

import java.sql.*;

public class BoardModel {
	
	private int		board_num;
	private String  board_id;
	private String 	board_title;
	private String  board_content;
	private String	board_date;
	private int		board_read;
	private boolean board_lock;
	
	
	public BoardModel()
	{
		
	}
	
	public BoardModel(int num, String id, String title, String content, String date, int read, boolean lock)
	{
		this.board_num = num;
		this.board_id = id;
		this.board_title = title;
		this.board_content = content;
		this.board_date = date;
		this.board_read = read;
		this.board_lock = lock;
	}
	
	
	
	
	
	public boolean isBoard_lock() {
		return board_lock;
	}

	public void setBoard_lock(boolean board_lock) {
		this.board_lock = board_lock;
	}

	public String getBoard_id() {
		return board_id;
	}




	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}




	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public int getBoard_read() {
		return board_read;
	}
	public void setBoard_read(int board_read) {
		this.board_read = board_read;
	}
	
		
}
