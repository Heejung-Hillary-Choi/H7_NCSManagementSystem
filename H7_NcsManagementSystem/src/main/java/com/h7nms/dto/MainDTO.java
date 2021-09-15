package com.h7nms.dto;

public class MainDTO {
	private String btitle;
	private String bname;
	private String bdate;
	private int bhit;
	public MainDTO() {}
	public MainDTO(String btitle, String bname, String bdate, int bhit) {
		super();
		if(bdate.length()>10) {
			bdate = bdate.substring(0, 10);
		}
		this.btitle = btitle;
		this.bname = bname;
		this.bdate = bdate;
		this.bhit = bhit;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBdate() {
		return bdate;
	}
	public void setBdate(String bdate) {
		if(bdate.length()>10) {
			bdate = bdate.substring(0, 10);
		}
		this.bdate = bdate;
	}
	public int getBhit() {
		return bhit;
	}
	public void setBhit(int bhit) {
		this.bhit = bhit;
	}
	@Override
	public String toString() {
		return "MainDTO [btitle=" + btitle + ", bname=" + bname + ", bdate=" + bdate + ", bhit=" + bhit + "]";
	}
	
}
