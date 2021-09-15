package com.h7nms.dto;

public class TestQuesListDTO {
	private String ques_area, ques_num, question;
	public TestQuesListDTO() {}
	public TestQuesListDTO(String ques_area, String ques_num, String question) {
		super();
		this.ques_area = ques_area;
		this.ques_num = ques_num;
		this.question = question;
	}
	@Override
	public String toString() {
		return "HTestQuesListDto [ques_area=" + ques_area + ", ques_num=" + ques_num + ", question=" + question + "]";
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
