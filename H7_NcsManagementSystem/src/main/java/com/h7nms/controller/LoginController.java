package com.h7nms.controller;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

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

import com.h7nms.dto.LoginDTO;
import com.h7nms.service.LoginService;

@Controller
@RequestMapping("/login/*")
public class LoginController {
	@Inject
	private LoginService ls;
	@Autowired
	private JavaMailSender mailSender;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/loginForm")
	public String loginForm() throws Exception {
		logger.info("get loginForm");
		return "/login/loginForm";
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
		String title = "H7NMS NCS교육관리시스템을 인증 이메일 입니다.";
		String content = "LUCKY SEVEN NCS교육관리시스템을 방문해주셔서 감사합니다.(✿◕‿◕✿)" + "<br><br>" + "인증 번호는 " + checkNum + "입니다."
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

	// 회원가입로그인 페이지에서 회원가입 버튼 눌렀을 때
	@RequestMapping(value = "/loginJoin")
	public String loginJoin() throws Exception {
		logger.info("get loginJoin");
		return "/login/loginJoin";
	}

	// id 중복 체크 컨트롤러
	@RequestMapping(value = "/idCheck", method = RequestMethod.GET)
	@ResponseBody
	public int idCheck(@RequestParam("userid") String userid) throws Exception {
		return ls.useridCheck(userid);
	}

	// 이메일 중복 체크 컨트롤러
	@RequestMapping(value = "/emailCheck", method = RequestMethod.GET)
	@ResponseBody
	public int emailCheck(@RequestParam("email") String email) throws Exception {

		return ls.emailCheck(email);
	}

	// 회원가입 POST_회원가입 페이지에서 완료 눌렀을 때
	@RequestMapping(value = "/loginJoinPro", method = RequestMethod.POST)
	public String loginJoin(LoginDTO login) throws Exception {
		logger.info("post loginJoin");
		ls.createMember(login);
		ls.createAuth(login);
		System.out.println("login: " + login);
		return "/login/loginJoinPro";
	}

	// 아이디 찾기
	@RequestMapping(value = "/loginIdFind")
	public String loginIdFind() throws Exception {
		return "/login/loginIdFind";
	}

	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	public String findId(@RequestParam("name") String name, @RequestParam("email") String email, Model md)
			throws Exception {
		md.addAttribute("userid", ls.findId(name, email));
		return "/login/findId";
	}

	@RequestMapping(value = "/nameEmailFind", method = RequestMethod.GET)
	@ResponseBody
	public int nameEmailFind(@RequestParam("name") String name, @RequestParam("email") String email) throws Exception {

		return ls.nameEmailFind(email, name);
	}

	// 비밀번호 찾기
	@RequestMapping(value = "/loginPwFind")
	public String loginPwFind() throws Exception {
		return "/login/loginPwFind";
	}

	@RequestMapping(value = "/findPw", method = RequestMethod.POST)
	public String findPw(@RequestParam("userid") String userid, @RequestParam("name") String name,
			@RequestParam("email") String email, Model md) throws Exception {
		System.out.println("userid :" + userid);
		System.out.println("name :" + name);
		System.out.println("email :" + email);
		md.addAttribute("password", ls.findPw(userid, name, email));
		System.out.println("ls.findPw" + ls.findPw(userid, name, email));
		return "/login/findPw";
	}

	@RequestMapping(value = "/idNameEmailFind", method = RequestMethod.GET)
	@ResponseBody
	public int idNameEmailFind(@RequestParam("userid") String userid, @RequestParam("name") String name,
			@RequestParam("email") String email) throws Exception {

		return ls.idNameEmailFind(userid, email, name);
	}


	//로그인
	@RequestMapping(value = "/login/loginSuccess", method = RequestMethod.GET)
	public String login(Model model) {
		logger.info("로그인에 성공하였습니다.");
		String returnValue = "";
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
		if(userauth.contains("ADMIN")) {
			returnValue = "redirect:/admin/admin2ndAuth";
		}else{	
		returnValue = "login/loginSuccess";
		}

		return returnValue;
	}
	// 로그아웃
	@RequestMapping(value = "/login/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model) {
		logger.info("로그아웃하였습니다.");
		return "login/logout";
	}

	// 로그인
	@RequestMapping(value = "/login/accessDenied", method = RequestMethod.GET)
	public String accessDenied(Locale locale, Model model) {
		logger.info("접근이 거부되었습니다.");
		return "login/accessDenied";
	}

	// 비밀번호 수정 페이지
	@RequestMapping(value = "/passwordUpdateView", method = RequestMethod.GET)
	public String memberUpdateView(Model model) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String userid = null;
		Collection<? extends GrantedAuthority> userauthority = null;
		String userauth = null;
		if (principal != null) {
			userid = auth.getName();
			userauthority = auth.getAuthorities();
			userauth = userauthority.toString();
		}
		model.addAttribute("userid", userid);
		model.addAttribute("userauth", userauth);
		return "/login/passwordUpdateView";
	}

	// 비밀번호 수정 버튼
	@RequestMapping(value = "/passwordUpdate", method = RequestMethod.GET)
	public String passwordUpdate(@RequestParam("userid") String userid, @RequestParam("password") String password)
			throws Exception {
		ls.passwordUpdate(userid, password);
		return "/login/passUpdateSuccess";
	}

	// 기존 비밀번호 확인
	@RequestMapping(value = "/passwordCheck", method = RequestMethod.GET)
	@ResponseBody
	public int passwordCheck(@RequestParam("password") String password, @RequestParam("userid") String userid)
			throws Exception {

		return ls.prePasswordCheck(password, userid);
	}

	// 회원정보 수정 페이지 이동
	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public String myPage(Model model, LoginDTO login) throws Exception {
		logger.info("post myPage 수정페이지");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String userid = null;
		Collection<? extends GrantedAuthority> userauthority = null;
		String userauth = null;
		if (principal != null) {
			userid = auth.getName();
			userauthority = auth.getAuthorities();
			userauth = userauthority.toString();
		}
		model.addAttribute("userid", userid);
		model.addAttribute("userauth", userauth);
		List<LoginDTO> memberDetailDtos = ls.memberDetail(userid);
		model.addAttribute("memberDetailDtos", memberDetailDtos);
		System.out.println("memberDetailDtos: " + memberDetailDtos);
		return "login/myPage";
	}

	// 회원정보 수정 버튼
	@RequestMapping(value = "/memberDetailResult", method = RequestMethod.GET)
	public String myPageBtn(Model model, LoginDTO login) throws Exception {
		logger.info("post myPage");
		ls.memberUpdate(login);
		System.out.println("login: " + login);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String userid = null;
		if (principal != null) {
			userid = auth.getName();
		}
		List<LoginDTO> memberDetailDtos = ls.memberDetail(userid);
		model.addAttribute("memberDetailDtos", memberDetailDtos);
		System.out.println("memberDetailDtos: " + memberDetailDtos);
		return "/login/memberDetailResult";
	}
}
