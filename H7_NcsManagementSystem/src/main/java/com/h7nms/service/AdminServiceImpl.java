package com.h7nms.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.h7nms.dao.AdminDAO;
import com.h7nms.dto.AdminDormantMemberDTO;
import com.h7nms.dto.AdminNcsTestDTO;
import com.h7nms.dto.AdminNewMemberDTO;

@Service 
public class AdminServiceImpl implements AdminService {
	@Inject
	private SqlSession sqlSession;
	
	//신규 멤버
	@Override
	public List<AdminNewMemberDTO> newMember_select(AdminNewMemberDTO anm) throws Exception {
		AdminDAO dao = sqlSession.getMapper(AdminDAO.class);
		List<AdminNewMemberDTO> dtos = dao.newMember_select(anm);
		return dtos;
	}
	@Override
	public List<AdminNewMemberDTO> newMember_search(String search) throws Exception {
		AdminDAO dao = sqlSession.getMapper(AdminDAO.class);
		List<AdminNewMemberDTO> dtos = dao.newMember_search(search);
		return dtos;
	}
	//휴면 멤버
	@Override
	public List<AdminDormantMemberDTO> dormant_member(AdminDormantMemberDTO adm) throws Exception {
		AdminDAO dao = sqlSession.getMapper(AdminDAO.class);
		List<AdminDormantMemberDTO> dtos=dao.dormant_member(adm);
		return dtos;
	}

	@Override
	public void newMember_update(String userid) throws Exception {
		AdminDAO dao = sqlSession.getMapper(AdminDAO.class);
		dao.newMember_update(userid);
	}

	@Override
	public void newMember_delete(String userid) throws Exception {
		AdminDAO dao = sqlSession.getMapper(AdminDAO.class);
		dao.newMember_delete(userid);
	}

	@Override
	public void newMember_delete2(String userid) throws Exception {
		AdminDAO dao = sqlSession.getMapper(AdminDAO.class);
		dao.newMember_delete2(userid);
	}

	@Override
	public void dormant_update(String userid) throws Exception {
		AdminDAO dao = sqlSession.getMapper(AdminDAO.class);
		dao.dormant_update(userid);
		
	}

	@Override
	public void dormant_update2(String userid) throws Exception {
		AdminDAO dao = sqlSession.getMapper(AdminDAO.class);
		dao.dormant_update2(userid);
	}
	
	// 휴면 유저 검색
	@Override
	public List<AdminDormantMemberDTO> dormant_search(String search) throws Exception {
		AdminDAO dao = sqlSession.getMapper(AdminDAO.class);
		List<AdminDormantMemberDTO> dtos= dao.dormant_search(search);
		return dtos;
	}
	//진단 평가지 등록
	@Override
	public List<AdminNcsTestDTO> ncstest(AdminNcsTestDTO ant) throws Exception {
		AdminDAO dao = sqlSession.getMapper(AdminDAO.class);
		List<AdminNcsTestDTO> dtos = dao.ncstest(ant);
		return dtos;
	}

	@Override
	public List<AdminNcsTestDTO> ncsTest_search(String search) throws Exception {
		AdminDAO dao = sqlSession.getMapper(AdminDAO.class);
		List<AdminNcsTestDTO> dtos = dao.ncsTest_search(search);
		return dtos;
	}

	//2차 인증
	@Override
	public String getSavedAdminEmail() throws Exception{
		AdminDAO dao = sqlSession.getMapper(AdminDAO.class);
		return dao.getSavedAdminEmail();
	}
	@Override
	public int adminemailCheck(String email) throws Exception {
		AdminDAO dao = sqlSession.getMapper(AdminDAO.class);
		return dao.adminemailCheck(email);
	}
}
