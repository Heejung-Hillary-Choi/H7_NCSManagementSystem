package com.h7nms.dto;

import java.util.Arrays;

public class StNcsListDTO {
	private String ncs_num;
	private String ncs_name;
	private String date_session;
	private int[] isTestResult;
	
	
	public StNcsListDTO() {}
	
	public StNcsListDTO(String ncs_num, String ncs_name, String date_session) {
		super();
		this.ncs_num = ncs_num;
		this.ncs_name = ncs_name;
		this.date_session = date_session;
		this.isTestResult = new int[2];
	}


	public String getNcs_num() {
		return ncs_num;
	}


	public void setNcs_num(String ncs_num) {
		this.ncs_num = ncs_num;
	}


	public String getNcs_name() {
		return ncs_name;
	}


	public void setNcs_name(String ncs_name) {
		this.ncs_name = ncs_name;
	}


	public String getDate_session() {
		return date_session;
	}


	public void setDate_session(String date_session) {
		this.date_session = date_session;
	}


	public int[] getIsTestResult() {
		return isTestResult;
	}


	public void setIsTestResult(int[] isTestResult) {
		this.isTestResult = isTestResult;
	}


	


	@Override
	public String toString() {
		return "HNcsLsitDto [ncs_num=" + ncs_num + ", ncs_name=" + ncs_name + ", date_session=" + date_session
				+ ", isTestResult=" + Arrays.toString(isTestResult) + "]";
	}

	

	

	
	
	
}
