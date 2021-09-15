package com.h7nms.dto;

public class CourseListDTO {
	private Integer cid;
	private String cname;
	private String name;
	private String date_full;
	private String room_num;
	
	public CourseListDTO() {}
	public CourseListDTO(Integer cid, String cname, String name, String date_full, String room_num) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.name = name;
		this.date_full = date_full;
		this.room_num = room_num;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}
