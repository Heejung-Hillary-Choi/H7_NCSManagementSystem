package com.h7nms.dto;

public class AdminNewMemberDTO {
	private String rownum;
	private String userid;
	private String name;
	private String phone;
	private String email;
	private String wanted;
	
	
	public AdminNewMemberDTO() {}
	public AdminNewMemberDTO(String rownum, String userid, String name, String phone, String email, String wanted) {
		super();
		this.rownum = rownum;
		this.userid = userid;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.wanted = wanted;
	}


	public String getRownum() {
		return rownum;
	}


	public void setRownum(String rownum) {
		this.rownum = rownum;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getWanted() {
		return wanted;
	}


	public void setWanted(String wanted) {
		this.wanted = wanted;
	}
	
	
	
	

}
