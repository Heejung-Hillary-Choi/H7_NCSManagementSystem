package com.h7nms.dto;

public class TestResultSNADTO {
	private String ques_area;
	private int count;
	private int sum;
	private double avg;
	public TestResultSNADTO() {}
	public TestResultSNADTO(String ques_area, int count, int sum, double avg) {
		super();
		this.ques_area = ques_area;
		this.count = count;
		this.sum = sum;
		this.avg = avg;
	}
	@Override
	public String toString() {
		return "HTestResultListDto [ques_area=" + ques_area + ", count=" + count + ", sum=" + sum + ", avg=" + avg
				+ "]";
	}
	public String getQues_area() {
		return ques_area;
	}
	public void setQues_area(String ques_area) {
		this.ques_area = ques_area;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
}
