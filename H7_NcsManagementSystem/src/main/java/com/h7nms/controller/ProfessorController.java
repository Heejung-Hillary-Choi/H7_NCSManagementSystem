package com.h7nms.controller;

import java.util.Collection;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.h7nms.dto.PrStincoListDTO;
import com.h7nms.dto.PrScoreDTO;
import com.h7nms.dto.PrScoreListDTO;
import com.h7nms.dto.StScoreDTO;
import com.h7nms.service.ProfessorService;

@Controller
@RequestMapping("/professor/*")
public class ProfessorController {
	@Inject
	private ProfessorService ps;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//강사가 강의하는 강의목록
	@RequestMapping(value="/prCourseList", method = RequestMethod.GET)
	public void prCourseList(Model model)  throws Exception{
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
		logger.info("prCourseList");
		
		model.addAttribute("prCourseList",ps.prcourseList(userid));
	}
	//선택한 강의의 출석부 조회
	@RequestMapping(value="/prStincoList", method = RequestMethod.GET)
	public void prStincoList(@RequestParam("cid") int cid, Model model)  throws Exception{
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
		logger.info("prStincoList");

		model.addAttribute("cid",cid);
		model.addAttribute("cname",ps.prgetCname(cid));
		model.addAttribute("prStincoList",ps.prStincoList(cid));
	}
	//특정 강의의 특정 학생 전체 성적 조회
	@RequestMapping(value="/prStScoreList", method = RequestMethod.GET)
	public void prStScoreList(@RequestParam("cid") int cid, @RequestParam("stid") String stid, Model model)  throws Exception{
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
		logger.info("prStScoreList");
		
		model.addAttribute("stid",stid);
		model.addAttribute("sname",ps.prgetSname(stid));
		model.addAttribute("cid",cid);
		model.addAttribute("cname",ps.prgetCname(cid));
		model.addAttribute("stScoreList",ps.prStScoreList(cid, stid));
	}
	//특정 강의의 평가일정표에 따른 전체 평가목록
	@RequestMapping(value="/prScore", method = RequestMethod.GET)
	public void prScore(@RequestParam("cid")int cid, PrScoreDTO hpld, Model model)  throws Exception{
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
		logger.info("prScore");
		
		model.addAttribute("prScore",ps.prScore(hpld));
	}
	//특정 강의, 특정 능력단위, 전체 학생 시험점수 목록
	@RequestMapping(value="/prScoreList", method = RequestMethod.GET)
	public void prScoreList(@RequestParam("cid") int cid,@RequestParam("ncs_num") String ncs_num,@RequestParam("type") int type,@RequestParam("sort") int sort, PrScoreListDTO hpld, Model model)  throws Exception{
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
		logger.info("prScoreList");
		
		System.out.println("cid :"+cid+" ncs_num :"+ncs_num+" type :"+type+" sort :"+sort);
		
		model.addAttribute("cid",cid);
		model.addAttribute("ncs_num",ncs_num);
		model.addAttribute("type",type);
		
		model.addAttribute("ncs_name",ps.prgetNCSname(ncs_num));
		model.addAttribute("cname",ps.prgetCname(cid));
		model.addAttribute(ps.prgetTid(cid, ncs_num, type));
		
		if(sort==1) {
			int tid =ps.prgetTid(cid, ncs_num, type);
			model.addAttribute("prScoreList",ps.prScoreList(cid, tid));
			System.out.println("ps.prScoreList"+ps.prScoreList(cid, tid));
		}else if(sort==2) {
			int tid =ps.prgetTid(cid, ncs_num, type);
			model.addAttribute("prScoreList",ps.prScoreList2(cid, tid));
			System.out.println("ps.prScoreList2"+ps.prScoreList2(cid, tid));
		}else if(sort==3) {
			int tid =ps.prgetTid(cid, ncs_num, type);
			model.addAttribute("prScoreList",ps.prScoreList3(cid, tid));
			System.out.println("ps.prScoreList3"+ps.prScoreList3(cid, tid));
		}
	}

	//시험지 결과 조회를 위해 강의명-능력단위명-사전/사후 검색
	@RequestMapping(value="/prtestSelect", method = RequestMethod.GET)
	public void prtestSelect(Model model) throws Exception {
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
		
		model.addAttribute("cnameList",ps.prtestSelect(userid));
		model.addAttribute("ncsList",ps.prtestSelect2(userid));
	}
	//특정 학생의 시험지 결과
	@RequestMapping(value="/prScoreDetail", method = RequestMethod.GET)
	public String prScoreDetail(@RequestParam("sname") String sname, @RequestParam("cid") int cid, @RequestParam("ncs_num") 
	String ncs_num, @RequestParam("type") int type, Model model) throws Exception {
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
		logger.info("prScoreDetail");

		String stid=ps.prgetStid(sname);
		int tid=ps.prgetTid(cid, ncs_num, type);
		model.addAttribute("stid",stid);
		model.addAttribute("tid",tid);
		
		if(type==1) {
			model.addAttribute("type","사전");
		}else if(type==2) {
			model.addAttribute("type","사후");
		}
		
		model.addAttribute("tldtos",ps.prtestInfo(tid, stid));
		model.addAttribute("qrdtos",ps.prshowQuesAnswerResultList(tid, stid));
		model.addAttribute("trldtos",ps.prtestResultSumAvgList(tid, stid));
		
		return "/professor/prScoreDetail";			
	}
	
	//RedirectAttributes : 리다이렉트로 넘어가는 페이지에 파라미터값을 넘길수 있음 여기에 힌트를 얻음
	@RequestMapping(value="/prScoreList2", method = RequestMethod.GET)
	public String prtestSelect2(@RequestParam("cname") String cname,@RequestParam("ncs_name") String ncs_name,Model model,@RequestParam("type") int type,
			RedirectAttributes redirectAttributes) throws Exception {
		int cid=ps.prgetCid(cname);
		String ncs_num=ps.prgetNCSnum(ncs_name);
		int sort =1;
		 redirectAttributes.addAttribute("cid", cid);
		 redirectAttributes.addAttribute("ncs_num", ncs_num);
		 redirectAttributes.addAttribute("type", type);
		 redirectAttributes.addAttribute("sort", sort);
		return "redirect:/professor/prScoreList";
	}
}
