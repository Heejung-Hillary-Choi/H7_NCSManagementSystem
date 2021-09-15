package com.h7nms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.h7nms.dto.BoardDTO;
import com.h7nms.dto.PageMaker;

public interface BoardNoticeService {
	  public void createNotice(BoardDTO board)  throws Exception;

	  public BoardDTO readNotice(int bid) throws Exception;
	  public void updateNotice(BoardDTO board) throws Exception;
	  public void deleteNotice(int bid) throws Exception;

	  public void boardNoticeHit(int bid) throws Exception;
	  
	  public List<BoardDTO> listSearchNotice(PageMaker pm)throws Exception;
	  public int listSearchCountNotice(PageMaker pm)throws Exception;
	  
	  public int getNoticebid() throws Exception;
	  public String getNoticeWriterNamebyBID(@Param("bid") int bid) throws Exception;
	  public String getNoticeWriterNamebyUSERID(@Param("userid") String userid) throws Exception;
	  public String getfilename(@Param("bid") int bid) throws Exception;
	  public void delete_notice_file(int bid) throws Exception;
	  public void update_notice_file(@Param("filename")String filename ,@Param("bid")int bid) throws Exception;

}
