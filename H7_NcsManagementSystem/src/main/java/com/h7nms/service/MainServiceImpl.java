package com.h7nms.service;

import java.util.List;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.h7nms.dao.MainDAO;
import com.h7nms.dto.MainDTO;

@Service
public class MainServiceImpl implements MainService {
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<MainDTO> mainshowBoardNoti(MainDTO dto) throws Exception {
		MainDAO dao = sqlSession.getMapper(MainDAO.class);
		return dao.mainshowBoardNoti(dto);
	}

	@Override
	public List<MainDTO> mainshowBoardQna(MainDTO dto) throws Exception {
		MainDAO dao = sqlSession.getMapper(MainDAO.class);
		return dao.mainshowBoardQna(dto);
	}

}
