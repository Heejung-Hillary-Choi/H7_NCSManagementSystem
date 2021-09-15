package com.h7nms.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class BoardDTO {

	private int bid;
	private String bname; //userid
	private String bwriter; //username
	private String btitle;
	private String bcontent;
	private Date bdate;
	private int bhit;
	private int bgroup;
	private int bstep;
	private int bindent;
	private String fileName;
	private MultipartFile uploadFile;
	
	public BoardDTO() {}

	public BoardDTO(int bid, String bname, String btitle, String bcontent, Date bdate, int bhit, int bgroup, int bstep,
			int bindent, String fileName, MultipartFile uploadFile) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.btitle = btitle;
		this.bcontent = bcontent;
		this.bdate = bdate;
		this.bhit = bhit;
		this.bgroup = bgroup;
		this.bstep = bstep;
		this.bindent = bindent;
		this.fileName = fileName;
		this.uploadFile = uploadFile;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public int getBhit() {
		return bhit;
	}

	public void setBhit(int bhit) {
		this.bhit = bhit;
	}

	public int getBgroup() {
		return bgroup;
	}

	public void setBgroup(int bgroup) {
		this.bgroup = bgroup;
	}

	public int getBstep() {
		return bstep;
	}

	public void setBstep(int bstep) {
		this.bstep = bstep;
	}

	public int getBindent() {
		return bindent;
	}

	public void setBindent(int bindent) {
		this.bindent = bindent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	@Override
	public String toString() {
		return "BoardDTO [bid=" + bid + ", bname=" + bname + ", btitle=" + btitle + ", bcontent=" + bcontent + ", bdate="
				+ bdate + ", bhit=" + bhit + ", bgroup=" + bgroup + ", bstep=" + bstep + ", bindent=" + bindent
				+ ", fileName=" + fileName + ", uploadFile=" + uploadFile + "]";
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}

	}