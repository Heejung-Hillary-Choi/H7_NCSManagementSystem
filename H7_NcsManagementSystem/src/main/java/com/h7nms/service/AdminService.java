package com.h7nms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.h7nms.dto.AdminDormantMemberDTO;
import com.h7nms.dto.AdminNcsTestDTO;
import com.h7nms.dto.AdminNewMemberDTO;

public interface AdminService {
	//신규
	public List<AdminNewMemberDTO> newMember_select(AdminNewMemberDTO  anm )throws Exception;
	public void newMember_update(@Param("userid") String userid)throws Exception;
	public void newMember_delete(@Param("userid") String userid)throws Exception; //member 삭제
	public void newMember_delete2(@Param("userid") String userid)throws Exception; //member_auth 삭제
	public List<AdminNewMemberDTO> newMember_search(@Param("search") String search)throws Exception;// 신규 회원 검색
	//휴면
	public List<AdminDormantMemberDTO> dormant_member(AdminDormantMemberDTO  adm )throws Exception;
	public void dormant_update(@Param("userid") String userid) throws Exception; //비활성화
	public void dormant_update2(@Param("userid") String userid) throws Exception; //활성화
	public List<AdminDormantMemberDTO> dormant_search(@Param("search") String search) throws Exception;

	//진단 평가지 등록
	public List<AdminNcsTestDTO> ncstest(AdminNcsTestDTO ant) throws Exception;
	public List<AdminNcsTestDTO> ncsTest_search(@Param("search") String search) throws Exception;//진단평가지 검색
	
	//2차 인증
	public String getSavedAdminEmail() throws Exception;
	public int adminemailCheck(String email) throws Exception;
}
