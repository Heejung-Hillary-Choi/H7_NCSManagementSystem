package com.h7nms.dto;

public class DefaultCourseDTO {
	private int cid;
	private String cname;
	private String inid;
	private String prid;
	private String date_full;
	private String room_num;
	public DefaultCourseDTO() {}
	public DefaultCourseDTO(int cid, String cname, String inid, String prid, String date_full, String room_num) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.inid = inid;
		this.prid = prid;
		this.date_full = date_full;
		this.room_num = room_num;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getInid() {
		return inid;
	}
	public void setInid(String inid) {
		this.inid = inid;
	}
	public String getPrid() {
		return prid;
	}
	public void setPrid(String prid) {
		this.prid = prid;
	}
	public String getDate_full() {
		return date_full;
	}
	public void setDate_full(String date_full) {
		this.date_full = date_full;
	}
	public String getRoom_num() {
		return room_num;
	}
	public void setRoom_num(String room_num) {
		this.room_num = room_num;
	}
	@Override
	public String toString() {
		return "InNewCourseDTO [cid=" + cid + ", cname=" + cname + ", inid=" + inid + ", prid=" + prid + ", date_full="
				+ date_full + ", room_num=" + room_num + "]";
	}
	
}
