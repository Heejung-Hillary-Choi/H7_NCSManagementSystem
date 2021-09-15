package com.h7nms.dao;

import java.util.List;

import com.h7nms.dto.MainDTO;

public interface MainDAO {
	public List<MainDTO> mainshowBoardNoti(MainDTO vo) throws Exception;
	public List<MainDTO> mainshowBoardQna(MainDTO vo) throws Exception;
}
