package com.h7nms.dto;

public class PrStincoListDTO {
	private String stid;
	private String sname;
	private String attend_rate;
	
	public PrStincoListDTO() {}
	public PrStincoListDTO(String stid, String sname, String attend_rate) {
		super();
		this.stid = stid;
		this.sname = sname;
		this.attend_rate = attend_rate;
	}
	public String getStid() {
		return stid;
	}
	public void setStid(String stid) {
		this.stid = stid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getAttend_rate() {
		return attend_rate;
	}
	public void setAttend_rate(String attend_rate) {
		this.attend_rate = attend_rate;
	}
}
