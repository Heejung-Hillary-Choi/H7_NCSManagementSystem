package com.h7nms.dto;

public class StScoreDTO {
private String ncs_name;
private int type;
private int sum;
public StScoreDTO() {}
public StScoreDTO(String ncs_name, int type, int sum) {
	super();
	this.ncs_name = ncs_name;
	this.type = type;
	this.sum = sum;
}
@Override
public String toString() {
	return "StScoreDto [ncs_name=" + ncs_name + ", type=" + type + ", sum=" + sum + "]";
}
public String getNcs_name() {
	return ncs_name;
}
public void setNcs_name(String ncs_name) {
	this.ncs_name = ncs_name;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public int getSum() {
	return sum;
}
public void setSum(int sum) {
	this.sum = sum;
}

}
