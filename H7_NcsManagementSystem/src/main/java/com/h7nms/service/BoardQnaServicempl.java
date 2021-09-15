package com.h7nms.service;


import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.h7nms.dao.BoardNoticeDAO;
import com.h7nms.dao.BoardQnaDAO;
import com.h7nms.dto.BoardDTO;
import com.h7nms.dto.PageMaker;
@Service
public class BoardQnaServicempl implements BoardQnaService {
	@Inject
	private SqlSession sqlSession;

	@Override
	public void createQna(BoardDTO board) throws Exception {
		BoardQnaDAO dao=sqlSession.getMapper(BoardQnaDAO.class);
		dao.createQna(board);	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardDTO readQna(int bid) throws Exception {
		BoardQnaDAO dao=sqlSession.getMapper(BoardQnaDAO.class);
		dao.boardQnaHit(bid);
		return dao.readQna(bid);	}
	@Override
	public void updateQna(BoardDTO board) throws Exception {
		// TODO Auto-generated method stub
		BoardQnaDAO dao = sqlSession.getMapper(BoardQnaDAO.class);
		dao.updateQna(board);	}
	@Override
	public void deleteQna(int bid) throws Exception {
		BoardQnaDAO dao=sqlSession.getMapper(BoardQnaDAO.class);
		dao.deleteQna(bid);	}
	
	@Override
	public void boardQnaHit(int bid) throws Exception {
		BoardQnaDAO dao=sqlSession.getMapper(BoardQnaDAO.class);
		dao.boardQnaHit(bid);	}
	
	@Override
	public List<BoardDTO> listSearchQna(PageMaker pm) throws Exception {
		BoardQnaDAO dao=sqlSession.getMapper(BoardQnaDAO.class);
		return dao.listSearchQna(pm);	}
	@Override
	public int listSearchCountQna(PageMaker pm) throws Exception {
		BoardQnaDAO dao=sqlSession.getMapper(BoardQnaDAO.class);
		return dao.listSearchCountQna(pm);	}

	@Override
	public void replyShapeQna(int bgroup,int bstep) throws Exception{
		BoardQnaDAO dao = sqlSession.getMapper(BoardQnaDAO.class);
		dao.replyShapeQna(bgroup,bstep);	}
	@Override
	public void replyCreateQna(String bname, String btitle, String bcontent, int bgroup, int bstep, int bindent) throws Exception {
		BoardQnaDAO dao = sqlSession.getMapper(BoardQnaDAO.class);
		dao.replyCreateQna(bname, btitle, bcontent, bgroup, bstep, bindent);	}

	@Override
	public int getQnaOriginalBgroup(int bid)throws Exception{
		BoardQnaDAO dao=sqlSession.getMapper(BoardQnaDAO.class);
		return dao.getQnaOriginalBgroup(bid);	}

	@Override
	public int getQnaOriginalBstep(int bid)throws Exception{
		BoardQnaDAO dao=sqlSession.getMapper(BoardQnaDAO.class);
		return dao.getQnaOriginalBstep(bid);	}

	@Override
	public int getQnaOriginalBindent(int bid)throws Exception{
		BoardQnaDAO dao=sqlSession.getMapper(BoardQnaDAO.class);
		return dao.getQnaOriginalBindent(bid);	}
	@Override
	public String getQnaWriterNamebyBID(int bid) throws Exception {
		BoardQnaDAO dao=sqlSession.getMapper(BoardQnaDAO.class);
		return dao.getQnaWriterNamebyBID(bid);
	}
	@Override
	public String getQnaWriterNamebyUSERID(String userid) throws Exception {
		BoardQnaDAO dao=sqlSession.getMapper(BoardQnaDAO.class);
		return dao.getQnaWriterNamebyUSERID(userid);
	}
	@Override
	public int getQnabid() throws Exception {
		BoardQnaDAO dao = sqlSession.getMapper(BoardQnaDAO.class);
		return dao.getQnabid();
	}

	@Override
	public String getfilename(int bid) throws Exception {
		BoardQnaDAO dao = sqlSession.getMapper(BoardQnaDAO.class);
		return dao.getfilename(bid);
	}

	@Override
	public void delete_qna_file(int bid) throws Exception {
		BoardQnaDAO dao = sqlSession.getMapper(BoardQnaDAO.class);
		dao.delete_qna_file(bid);
		
	}

	@Override
	public void update_qna_file(String filename, int bid) throws Exception {
		BoardQnaDAO dao = sqlSession.getMapper(BoardQnaDAO.class);
		dao.update_qna_file(filename, bid);
		
	}
}
