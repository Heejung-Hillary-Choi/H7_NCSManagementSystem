package com.h7nms.dto;

public class AdminDormantMemberDTO {
	private String rownum;
	private String userid;
	private String password;
	private String name;
	private String phone;
	private String email;
	private String enabled;
	
	public AdminDormantMemberDTO() {}
	public AdminDormantMemberDTO(String rownum, String userid, String password, String name, String phone, String email,
			String enabled) {
		super();
		this.rownum = rownum;
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.enabled = enabled;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}


	
	
}
