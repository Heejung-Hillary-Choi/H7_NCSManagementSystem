package com.h7nms.dto;

public class InStudentListDTO {
	private String stid;
	private String name;
	private String phone;
	private String address;
	public InStudentListDTO() {}
	public InStudentListDTO(String stid, String name, String phone, String address) {
		super();
		this.stid = stid;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}
	public String getStid() {
		return stid;
	}
	public void setStid(String stid) {
		this.stid = stid;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "InStudentListDTO [stid=" + stid + ", name=" + name + ", phone=" + phone + ", address=" + address + "]";
	}
}