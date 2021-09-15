package com.h7nms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.h7nms.dto.BoardDTO;
import com.h7nms.dto.PageMaker;

public interface BoardQnaService {
	  public void createQna(BoardDTO board)  throws Exception;
	  
	  public BoardDTO readQna(int bid) throws Exception;
	  public void updateQna(BoardDTO board) throws Exception;
	  public void deleteQna(int bid) throws Exception;
	  
	  public void boardQnaHit(int bid) throws Exception;
	  
	  public List<BoardDTO> listSearchQna(PageMaker pm)throws Exception;
	  public int listSearchCountQna(PageMaker pm)throws Exception;

	  public void replyShapeQna(@Param("bgroup") int bgroup,@Param("bstep")int bstep) throws Exception;
	  public void replyCreateQna(@Param("bname") String bname,@Param("btitle") String btitle,@Param("bcontent") String bcontent,@Param("bgroup") int bgroup,@Param("bstep") int bstep, @Param("bindent") int bindent)throws Exception;

	  public int getQnaOriginalBgroup(@Param("bid")int bid)throws Exception;
	  public int getQnaOriginalBstep(@Param("bid")int bid)throws Exception;
	  public int getQnaOriginalBindent(@Param("bid")int bid)throws Exception;
	  public String getQnaWriterNamebyBID(@Param("bid") int bid) throws Exception;
	  public String getQnaWriterNamebyUSERID(@Param("userid") String userid) throws Exception;
  
	  public int getQnabid() throws Exception;
	  public String getfilename(@Param("bid") int bid) throws Exception;
	  public void delete_qna_file(int bid) throws Exception;
	  public void update_qna_file(@Param("filename")String filename ,@Param("bid")int bid) throws Exception;

}
