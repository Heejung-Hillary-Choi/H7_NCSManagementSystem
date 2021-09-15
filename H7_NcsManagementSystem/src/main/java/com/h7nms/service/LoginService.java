package com.h7nms.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.h7nms.dto.LoginDTO;

public interface LoginService {
//	public BoardDTO read(int bno) throws Exception;
	public void createMember(LoginDTO login) throws Exception;

	public void createAuth(LoginDTO vo) throws Exception;

	public String findId(@Param("name") String name, @Param("email") String email) throws Exception;

	public String findPw(@Param("userid") String userid, @Param("name") String name, @Param("email") String email)
			throws Exception;

	public int useridCheck(@Param("userid") String userid) throws Exception;

	public int prePasswordCheck(@Param("password") String password, @Param("userid") String userid) throws Exception;

	public int emailCheck(@Param("email") String email) throws Exception;

	public void passwordUpdate(@Param("userid") String userid, @Param("password") String password) throws Exception;

	public void memberUpdate(LoginDTO vo) throws Exception;

	public List<LoginDTO> memberDetail(@Param("userid") String userid) throws Exception;

	public int nameEmailFind(@Param("email") String email, @Param("name") String name) throws Exception;

	public int idNameEmailFind(@Param("userid") String userid, @Param("email") String email, @Param("name") String name)
			throws Exception;

}
