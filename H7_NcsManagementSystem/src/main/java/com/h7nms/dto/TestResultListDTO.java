package com.h7nms.dto;

public class TestResultListDTO {
	private int tid;
	private String cname;
	private String ncs_name;
	private String ncs_num;
	private int type;
	public TestResultListDTO() {}
	public TestResultListDTO(int tid, String cname, String ncs_name, String ncs_num, int type) {
		super();
		this.tid = tid;
		this.cname = cname;
		this.ncs_name = ncs_name;
		this.ncs_num = ncs_num;
		this.type = type;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getNcs_name() {
		return ncs_name;
	}
	public void setNcs_name(String ncs_name) {
		this.ncs_name = ncs_name;
	}
	public String getNcs_num() {
		return ncs_num;
	}
	public void setNcs_num(String ncs_num) {
		this.ncs_num = ncs_num;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
