package com.h7nms.dto;

public class TestQResultDTO {
	private String ques_area;
	private String ques_num;
	private String question;
	private int answer;
	public TestQResultDTO() {}
	public TestQResultDTO(String ques_area, String ques_num, String question, int answer) {
		super();
		this.ques_area = ques_area;
		this.ques_num = ques_num;
		this.question = question;
		this.answer = answer;
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
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	
	
}
