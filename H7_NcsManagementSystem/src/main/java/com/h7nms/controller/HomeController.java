package com.h7nms.controller;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.h7nms.dto.MainDTO;
import com.h7nms.service.MainService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Inject
	private MainService service;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(MainDTO dto, Model model) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String userid = null;
		Collection<? extends GrantedAuthority> userauthority = null;
		String userauth = null;
		if(principal != null) {
			userid = auth.getName();
			userauthority = auth.getAuthorities();
			userauth = userauthority.toString();
//			System.out.println(userauthority);
//			System.out.println(userauthority.toString());
//			System.out.println(userauth.contains("ADMIN"));
		}
		model.addAttribute("userid", userid);
		model.addAttribute("userauth", userauth);
		logger.info("default returning mainPage");

		List<MainDTO> boardNotiDtos = service.mainshowBoardNoti(dto);
		List<MainDTO> boardQnaDtos = service.mainshowBoardQna(dto);
		model.addAttribute("boardNotiDtos", boardNotiDtos);
		model.addAttribute("boardQnaDtos", boardQnaDtos);
//		System.out.println("boardNotiDtos: " + boardNotiDtos);
//		System.out.println("boardQnaDtos: " + boardQnaDtos);
		return "mainPage";
	}

	@RequestMapping(value = "/mainPage", method = RequestMethod.GET)
	public String main(MainDTO dto, Model model) throws Exception {
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
		
		logger.info("mainPage");

		List<MainDTO> boardNotiDtos = service.mainshowBoardNoti(dto);
		List<MainDTO> boardQnaDtos = service.mainshowBoardQna(dto);
		model.addAttribute("boardNotiDtos", boardNotiDtos);
		model.addAttribute("boardQnaDtos", boardQnaDtos);
		return "mainPage";
	}
	
	@RequestMapping(value = "/whatisNCS", method = RequestMethod.GET)
	public void whatisNCS(Model model) throws Exception {
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

		logger.info("whatisNCS");
	}
	
	@RequestMapping(value = "/include/noScoreDetail", method = RequestMethod.GET)
	public void noScoreDetail(Model model) throws Exception {
		logger.info("noScoreDetail");
		model.addAttribute("error","error");		
	}
}
