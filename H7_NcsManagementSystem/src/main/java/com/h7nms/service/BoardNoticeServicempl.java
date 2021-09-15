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
import com.h7nms.dto.BoardDTO;
import com.h7nms.dto.PageMaker;

@Service
public class BoardNoticeServicempl implements BoardNoticeService {
	@Inject
	private SqlSession sqlSession;

	@Override
	public void createNotice(BoardDTO board) throws Exception {
		BoardNoticeDAO dao=sqlSession.getMapper(BoardNoticeDAO.class);
		dao.createNotice(board); }

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public BoardDTO readNotice(int bid) throws Exception {
		BoardNoticeDAO dao=sqlSession.getMapper(BoardNoticeDAO.class);
		dao.boardNoticeHit(bid);
		return dao.readNotice(bid);	}
	@Override
	public void updateNotice(BoardDTO board) throws Exception {
		// TODO Auto-generated method stub
		BoardNoticeDAO dao = sqlSession.getMapper(BoardNoticeDAO.class);
		dao.updateNotice(board);
	}
	@Override
	public void deleteNotice(int bid) throws Exception {
		BoardNoticeDAO dao=sqlSession.getMapper(BoardNoticeDAO.class);
		dao.deleteNotice(bid); }

	@Override
	public void boardNoticeHit(int bid) throws Exception {
		BoardNoticeDAO dao=sqlSession.getMapper(BoardNoticeDAO.class);
		dao.boardNoticeHit(bid);	}
	
	@Override
	public List<BoardDTO> listSearchNotice(PageMaker pm) throws Exception {
		BoardNoticeDAO dao=sqlSession.getMapper(BoardNoticeDAO.class);
		return dao.listSearchNotice(pm);
	}

	@Override
	public int listSearchCountNotice(PageMaker pm) throws Exception {
		BoardNoticeDAO dao=sqlSession.getMapper(BoardNoticeDAO.class);
		return dao.listSearchCountNotice(pm);
	}
	@Override
	public int getNoticebid() throws Exception {
		BoardNoticeDAO dao=sqlSession.getMapper(BoardNoticeDAO.class);
		return dao.getNoticebid();
	}
	@Override
	public String getNoticeWriterNamebyBID(int bid) throws Exception {
		BoardNoticeDAO dao=sqlSession.getMapper(BoardNoticeDAO.class);
		return dao.getNoticeWriterNamebyBID(bid);
	}
	@Override
	public String getNoticeWriterNamebyUSERID(String userid) throws Exception {
		BoardNoticeDAO dao=sqlSession.getMapper(BoardNoticeDAO.class);
		return dao.getNoticeWriterNamebyUSERID(userid);
	}
	
	@Override
	public String getfilename(int bid) throws Exception {
		BoardNoticeDAO dao=sqlSession.getMapper(BoardNoticeDAO.class);
		return dao.getfilename(bid);
	}

	@Override
	public void delete_notice_file(int bid) throws Exception {
		BoardNoticeDAO dao=sqlSession.getMapper(BoardNoticeDAO.class);
		dao.delete_notice_file(bid);
		
	}

	@Override
	public void update_notice_file(String filename, int bid) throws Exception {
		BoardNoticeDAO dao=sqlSession.getMapper(BoardNoticeDAO.class);
		dao.update_notice_file(filename, bid);
		
	}
}
