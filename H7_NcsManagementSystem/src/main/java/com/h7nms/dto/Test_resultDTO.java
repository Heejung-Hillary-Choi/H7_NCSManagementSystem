package com.h7nms.dto;

public class Test_resultDTO {
	private int tid, answer;
	private String ques_num, stid;
	
	public Test_resultDTO() {}
	public Test_resultDTO(int tid, int answer, String ques_num, String stid) {
		super();
		this.tid = tid;
		this.answer = answer;
		this.ques_num = ques_num;
		this.stid = stid;
	}
	
	@Override
	public String toString() {
		return "HTest_resultDto [tid=" + tid + ", answer=" + answer + ", ques_num=" + ques_num + ", stid=" + stid + "]";
	}
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public String getQues_num() {
		return ques_num;
	}
	public void setQues_num(String ques_num) {
		this.ques_num = ques_num;
	}
	public String getStid() {
		return stid;
	}
	public void setStid(String stid) {
		this.stid = stid;
	}
	
}
