package com.h7nms.dto;

public class LoginDTO {
	private String authority;
	private String userid;
	private String userpassword;
	private String name;
	private String address_detail;
	private String address_extra;
	private String postcode;
	private String phone;
	private String email;
	private String address;

	public LoginDTO() {
	}

	public LoginDTO(String authority, String userid, String userpassword, String name, String address_detail,
			String address_extra, String postcode, String phone, String email, String address) {
		super();
		this.authority = authority;
		this.userid = userid;
		this.userpassword = userpassword;
		this.name = name;
		this.address_detail = address_detail;
		this.address_extra = address_extra;
		this.postcode = postcode;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	public String getAddress_extra() {
		return address_extra;
	}

	public void setAddress_extra(String address_extra) {
		this.address_extra = address_extra;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "LoginDTO [authority=" + authority + ", userid=" + userid + ", userpassword=" + userpassword + ", name="
				+ name + ", address_detail=" + address_detail + ", address_extra=" + address_extra + ", postcode="
				+ postcode + ", phone=" + phone + ", email=" + email + ", address=" + address + "]";
	}

	
}
