package com.h7nms.service;

import java.util.List;

import com.h7nms.dto.MainDTO;

public interface MainService {
	public List<MainDTO> mainshowBoardNoti(MainDTO dto) throws Exception;

	public List<MainDTO> mainshowBoardQna(MainDTO dto) throws Exception;

}
