package com.h7nms.controller;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.h7nms.dto.AdminDormantMemberDTO;
import com.h7nms.dto.AdminNcsTestDTO;
import com.h7nms.dto.AdminNewMemberDTO;
import com.h7nms.dto.DefaultCourseDTO;
import com.h7nms.dto.InStudentListDTO;
import com.h7nms.service.AdminService;
import com.h7nms.service.InstitutionService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	@Inject
	private AdminService as;
	@Autowired
	private JavaMailSender mailSender;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@RequestMapping(value="/adminHome", method = RequestMethod.GET)
	public void adminHomeGet(Model model)  throws Exception{
	}
	
	@RequestMapping(value="/adminHome", method = RequestMethod.POST)
	public void adminHome(/*@RequestParam("is2ndAtuthrized") String is2ndAtuthrized, */Model model)  throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String userid = null;
		Collection<? extends GrantedAuthority> userauthority = null;
		String userauth = null;
		if(principal != null) {
			userid = auth.getName();
			userauthority = auth.getAuthorities();
			userauth = userauthority.toString();
		}
		model.addAttribute("userid", userid);
		model.addAttribute("userauth", userauth);
		logger.info("adminHome");
		
//		model.addAttribute("is2ndAtuthrized", is2ndAtuthrized);		
	}
	
	//2차 인증
	@RequestMapping(value="/admin2ndAuth", method = RequestMethod.GET)
	public void admin2ndAuth(Model model)  throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String userid = null;
		Collection<? extends GrantedAuthority> userauthority = null;
		String userauth = null;
		if(principal != null) {
			userid = auth.getName();
			userauthority = auth.getAuthorities();
			userauth = userauthority.toString();
		}
		model.addAttribute("userid", userid);
		model.addAttribute("userauth", userauth);
		logger.info("admin2ndAuth");
	}
	// 이메일 인증
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(@RequestParam("email") String email) throws Exception {

		/* 뷰(View)로부터 넘어온 데이터 확인 */
		logger.info("이메일 데이터 전송 확인");
		logger.info("인증번호 : " + email);
		/* 인증번호(난수) 생성 */
		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		logger.info("인증번호 " + checkNum);
		/* 이메일 보내기 */
		String setFrom = "qls5170@naver.com";
		String toMail = email;
		String title = "관리자 로그인 2차인증 이메일 입니다.";
		String content = "LUCKY SEVEN NCS평가관리시스템 관리자 로그인을 위한 2차인증을 시도하였습니다.(✿◕‿◕✿)" + "<br><br>" + "인증 번호는 " + checkNum + "입니다."
				+ "<br>" + "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";

		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		String num = Integer.toString(checkNum);
		return num;
	}
	// 이메일 중복 체크 컨트롤러
	@RequestMapping(value = "/emailCheck", method = RequestMethod.GET)
	@ResponseBody
	public int emailCheck(@RequestParam("email") String email) throws Exception {

		return as.adminemailCheck(email);
	}
	
	//신규 멤버
	@RequestMapping(value="/adminNewMember", method = RequestMethod.GET)
	public String adminNewMember(AdminNewMemberDTO anm,Model model)  throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String userid = null;
		Collection<? extends GrantedAuthority> userauthority = null;
		String userauth = null;
		if(principal != null) {
			userid = auth.getName();
			userauthority = auth.getAuthorities();
			userauth = userauthority.toString();
		}
		model.addAttribute("userid", userid);
		model.addAttribute("userauth", userauth);	
			
		model.addAttribute("new_member",as.newMember_select(anm));
		return "/admin/adminNewMember";
	}
	// 새로운 멤버 승인
	@RequestMapping(value="/newMember_updete", method = RequestMethod.GET)
	public String NewMemberUpdate(@RequestParam("userid")String userid,Model model)  throws Exception{
		as.newMember_update(userid);
		return "redirect:/admin/adminNewMember";
	}
	// 새로운 멤버 거부
	@RequestMapping(value="/newMember_delete", method = RequestMethod.GET)
	public String NewMemberDelete(@RequestParam("userid")String userid,Model model)  throws Exception{
		as.newMember_delete2(userid);
		as.newMember_delete(userid);
		return "redirect:/admin/adminNewMember";
	}
	// 새로운 멤버 검색
	@RequestMapping(value="/newMember_search", method = RequestMethod.GET)
	public String adminnewMembersearch(@RequestParam("search")String search,Model model)  throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String userid = null;
		Collection<? extends GrantedAuthority> userauthority = null;
		String userauth = null;
		if(principal != null) {
			userid = auth.getName();
			userauthority = auth.getAuthorities();
			userauth = userauthority.toString();
		}
		model.addAttribute("userid", userid);
		model.addAttribute("userauth", userauth);
		
		List<AdminNewMemberDTO> dtos = as.newMember_search(search);
		model.addAttribute("new_member",dtos);
		return"/admin/adminNewMember";
	}
	
	//휴면 멤버
	@RequestMapping(value="/adminDormantMember", method = RequestMethod.GET)
	public String adminDormantMember(AdminDormantMemberDTO adm,Model model)  throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String userid = null;
		Collection<? extends GrantedAuthority> userauthority = null;
		String userauth = null;
		if(principal != null) {
			userid = auth.getName();
			userauthority = auth.getAuthorities();
			userauth = userauthority.toString();
		}
		model.addAttribute("userid", userid);
		model.addAttribute("userauth", userauth);	
		
		
		model.addAttribute("dormant_member",as.dormant_member(adm));
		return "/admin/adminDormantMember";
	}
	//휴면 멤버 비활성화
	@RequestMapping(value="/dormant_update", method = RequestMethod.GET)
	public String DormantMemberUpdate(@RequestParam("userid")String userid, Model model)  throws Exception{
		as.dormant_update(userid);
		return "redirect:/admin/adminDormantMember";
	}
	//휴면 멤버 활성화
	@RequestMapping(value="/dormant_update2", method = RequestMethod.GET)
	public String DormantMemberUpdate2(@RequestParam("userid")String userid,Model model)  throws Exception{
		as.dormant_update2(userid);
		return "redirect:/admin/adminDormantMember";
	}
	//휴면 멤버 검색
	@RequestMapping(value="/dormant_search", method = RequestMethod.GET)
	public String admindormantsearch(@RequestParam("search")String search,Model model)  throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String userid = null;
		Collection<? extends GrantedAuthority> userauthority = null;
		String userauth = null;
		if(principal != null) {
			userid = auth.getName();
			userauthority = auth.getAuthorities();
			userauth = userauthority.toString();
		}
		model.addAttribute("userid", userid);
		model.addAttribute("userauth", userauth);
		
		List<AdminDormantMemberDTO> dtos = as.dormant_search(search);
		model.addAttribute("dormant_member",dtos);
		return"/admin/adminDormantMember";
	}
	
	//진단 평가지 등록
	@RequestMapping(value="/adminNcsTest", method = RequestMethod.GET)
	public String adminNcsTest(AdminNcsTestDTO ant,Model model)  throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String userid = null;
		Collection<? extends GrantedAuthority> userauthority = null;
		String userauth = null;
		if(principal != null) {
			userid = auth.getName();
			userauthority = auth.getAuthorities();
			userauth = userauthority.toString();
		}
		model.addAttribute("userid", userid);
		model.addAttribute("userauth", userauth);	
		
		
		model.addAttribute("ncstest",as.ncstest(ant));
		return "/admin/adminNcsTest";
	}
	@RequestMapping(value="/ncstest_search", method = RequestMethod.GET)
	public String adminNcsTestsearch(@RequestParam("search")String search,Model model)  throws Exception{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String userid = null;
		Collection<? extends GrantedAuthority> userauthority = null;
		String userauth = null;
		if(principal != null) {
			userid = auth.getName();
			userauthority = auth.getAuthorities();
			userauth = userauthority.toString();
		}
		model.addAttribute("userid", userid);
		model.addAttribute("userauth", userauth);
		
		List<AdminNcsTestDTO> dtos = as.ncsTest_search(search);
		model.addAttribute("ncstest",dtos);
		return "/admin/adminNcsTest";
	}
	//테스트 페이지
	@RequestMapping(value="/adminTestPage", method = RequestMethod.GET)
	public String adminTestPage(Model model)  throws Exception{
		return "/admin/adminTestPage";
	}
}
