package com.h7nms.dto;

public class Test_detailDTO {
	private String ncs_num, ques_area, ques_num, question;
	
	public Test_detailDTO() {}
	public Test_detailDTO(String ncs_num, String ques_area, String ques_num, String question) {
		super();
		this.ncs_num = ncs_num;
		this.ques_area = ques_area;
		this.ques_num = ques_num;
		this.question = question;
	}
	
	@Override
	public String toString() {
		return "HTest_detailDto [ncs_num=" + ncs_num + ", ques_area=" + ques_area + ", ques_num=" + ques_num
				+ ", question=" + question + "]";
	}
	
	public String getNcs_num() {
		return ncs_num;
	}
	public void setNcs_num(String ncs_num) {
		this.ncs_num = ncs_num;
	}
	public String getQues_area() {
		return ques_area;
	}
	public void setQues_area(String ques_area) {
		this.ques_area = ques_area;
	}
	public String getQues_num() {
		return ques_num;
	}
	public void setQues_num(String ques_num) {
		this.ques_num = ques_num;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
}
