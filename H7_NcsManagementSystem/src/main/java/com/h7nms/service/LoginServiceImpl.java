package com.h7nms.service;

import java.io.PrintWriter;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.h7nms.dao.LoginDAO;
import com.h7nms.dto.LoginDTO;

@Service
public class LoginServiceImpl implements LoginService {
	@Inject
	private SqlSession sqlSession;

	// 회원가입
	@Override
	public void createMember(LoginDTO login) throws Exception {
		System.out.println(sqlSession);
		System.out.println("login:" + login);
		LoginDAO dao = sqlSession.getMapper(LoginDAO.class);
		dao.createMember(login);
		System.out.println("login:" + login);
	}

	public void createAuth(LoginDTO login) throws Exception {
		System.out.println(sqlSession);
		System.out.println("login:" + login);
		LoginDAO dao = sqlSession.getMapper(LoginDAO.class);
		dao.createAuth(login);
		System.out.println("login:" + login);
	}

	// 아이디 찾기
	@Override
	public String findId(String name, String email) throws Exception {
		LoginDAO dao = sqlSession.getMapper(LoginDAO.class);
		String userid = dao.findId(name, email);
		return userid;
	}

	// 비밀번호 찾기
	@Override
	public String findPw(String userid, String name, String email) throws Exception {
		LoginDAO dao = sqlSession.getMapper(LoginDAO.class);
		String password = dao.findPw(userid, name, email);

		return password;

	}

	// 중복 아이디 체크
	public int useridCheck(String userid) throws Exception {

		LoginDAO dao = sqlSession.getMapper(LoginDAO.class);

		return dao.useridCheck(userid);
	}

	// 중복 이메일 체크
	public int emailCheck(String email) throws Exception {

		LoginDAO dao = sqlSession.getMapper(LoginDAO.class);

		return dao.emailCheck(email);
	}

	public int nameEmailFind(String email, String name) throws Exception {

		LoginDAO dao = sqlSession.getMapper(LoginDAO.class);

		return dao.nameEmailFind(email, name);
	}

	public int idNameEmailFind(String userid, String email, String name) throws Exception {

		LoginDAO dao = sqlSession.getMapper(LoginDAO.class);

		return dao.idNameEmailFind(userid, email, name);
	}

	// 비밀번호 수정
	@Override
	public void passwordUpdate(String userid, String password) throws Exception {
		LoginDAO dao = sqlSession.getMapper(LoginDAO.class);
		dao.passwordUpdate(userid, password);
	}

	// 기존 비밀번호 확인
	public int prePasswordCheck(String password, String userid) throws Exception {

		LoginDAO dao = sqlSession.getMapper(LoginDAO.class);

		return dao.prePasswordCheck(password, userid);
	}

	// 회원정보 수정
	@Override
	public void memberUpdate(LoginDTO login) throws Exception {
		LoginDAO dao = sqlSession.getMapper(LoginDAO.class);
		dao.memberUpdate(login);
	}

	// 회원정보 조회
	@Override
	public List<LoginDTO> memberDetail(String userid) throws Exception {
		LoginDAO dao = sqlSession.getMapper(LoginDAO.class);
		return dao.memberDetail(userid);
	}

}
