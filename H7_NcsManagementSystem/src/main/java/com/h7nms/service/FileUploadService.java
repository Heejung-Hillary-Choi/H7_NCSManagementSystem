package com.h7nms.service;

import org.apache.ibatis.annotations.Param;

public interface FileUploadService {
	public void addFile(@Param("cid")int cid,@Param("cname")String cname, @Param("filename")String filename)throws Exception;
	public void addFile_qna(@Param("bid")int bid,@Param("bname")String bname, @Param("filename")String filename)throws Exception;
	public void addFile_notice(@Param("bid")int bid,@Param("bname")String bname, @Param("filename")String filename)throws Exception;

	public int isFileAlreadyIn_notice(@Param("bid") int bid) throws Exception;
	public int isFileAlreadyIn_qna(@Param("bid") int bid) throws Exception;
}
