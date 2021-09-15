package com.h7nms.dto;

public class PrScoreDTO {
	private int cid;
	private String cname;
	private String ncs_name;
	private String ncs_num;
	private String date_session;
	
	public PrScoreDTO() {}
	public PrScoreDTO(int cid, String cname, String ncs_name, String ncs_num, String date_session) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.ncs_name = ncs_name;
		this.ncs_num = ncs_num;
		this.date_session = date_session;
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
	public String getDate_session() {
		return date_session;
	}
	public void setDate_session(String date_session) {
		this.date_session = date_session;
	}
}
