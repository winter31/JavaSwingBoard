package vo;

import java.io.Serializable;

public class Board implements Serializable, Comparable<Board> {
	private String uId;
	private String text;
	private String title;
	private String date;
	private int num;
	/**
	 * @param uId
	 * @param text
	 * @param title
	 * @param date
	 * @param num
	 */
	public Board(String uId, String text, String title, String date, int num) {
		super();
		this.uId = uId;
		this.text = text;
		this.title = title;
		this.date = date;
		this.num = num;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return  "게시글 번호 : " + num +", ID : " + uId + ", 타이틀 : " + title + ", 날짜 : " + date;
	}
	
	public String toString1() {
		return ", 게시글 번호 : " + num+" ID : " + uId + ", 내용 : " + text + ", 타이틀 : " + title + ", 날짜 : " + date ;
	}
	@Override
	public int compareTo(Board d) {
		return this.date.compareTo(d.date);
	}
}
