package com.h7nms.dto;

public class PrScoreListDTO {
	private String name;
	private String sum;
	
	public PrScoreListDTO() {}
	public PrScoreListDTO(String name, String sum) {
		super();
		this.name = name;
		this.sum = sum;
	}

	@Override
	public String toString() {
		return " PrScoreListDTO [name=" + name + ", sum=" + sum + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
}
