package com.h7nms.dto;

import java.util.Date;

public class TestInfoDTO {
	private String name;
	private String date_session;
	private Date date_test;
	private String cname;
	private String sname;
	private String ncs_name;
	private String pname;
	private String ncs_num;
	
	public TestInfoDTO() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate_session() {
		return date_session;
	}

	public void setDate_session(String date_session) {
		this.date_session = date_session;
	}

	public Date getDate_test() {
		return date_test;
	}

	public void setDate_test(Date date_test) {
		
		this.date_test = date_test;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getNcs_name() {
		return ncs_name;
	}

	public void setNcs_name(String ncs_name) {
		this.ncs_name = ncs_name;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getNcs_num() {
		return ncs_num;
	}

	public void setNcs_num(String ncs_num) {
		this.ncs_num = ncs_num;
	}

	@Override
	public String toString() {
		
		return "TestInfoDTO [name=" + name + ", date_session=" + date_session + ", date_test=" + date_test + ", cname="
				+ cname + ", sname=" + sname + ", ncs_name=" + ncs_name + ", pname=" + pname + ", ncs_num=" + ncs_num
				+ "]";
	}

	public TestInfoDTO(String name, String date_session, Date date_test, String cname, String sname, String ncs_name,
			String pname, String ncs_num) {
		super();
		this.name = name;
		this.date_session = date_session;
		this.date_test = date_test;
		this.cname = cname;
		this.sname = sname;
		this.ncs_name = ncs_name;
		this.pname = pname;
		this.ncs_num = ncs_num;
	}	
}
