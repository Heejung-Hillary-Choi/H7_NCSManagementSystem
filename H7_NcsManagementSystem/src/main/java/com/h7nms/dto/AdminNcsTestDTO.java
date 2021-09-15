package com.h7nms.dto;

public class AdminNcsTestDTO {
	private String cname;
	private String prid;
	private String filename;
	
	
	@Override
	public String toString() {
		return "AdminNcsTestDTO [cname=" + cname + ", prid=" + prid + ", filename=" + filename + "]";
	}
	public AdminNcsTestDTO() {}
	public AdminNcsTestDTO(String cname, String prid, String filename) {
		super();
		this.cname = cname;
		this.prid = prid;
		this.filename = filename;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getPrid() {
		return prid;
	}
	public void setPrid(String prid) {
		this.prid = prid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
