package com.h7nms.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

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

import com.h7nms.dto.CourseListDTO;
import com.h7nms.dto.StNcsListDTO;
import com.h7nms.dto.TestInfoDTO;
import com.h7nms.dto.TestQResultDTO;
import com.h7nms.dto.TestQuesListDTO;
import com.h7nms.dto.TestResultListDTO;
import com.h7nms.dto.TestResultSNADTO;
import com.h7nms.dto.TestSelectDTO;
import com.h7nms.dto.Test_resultDTO;
import com.h7nms.service.StudentService;

@Controller

@RequestMapping("/student/*")
public class StudentController {
	@Inject
	private StudentService ss;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//한 학생의 나의 강의목록 전체
	@RequestMapping(value = "/stListCourse", method = RequestMethod.GET)
	public void courseList(Model model) throws Exception {
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
		logger.info("");
		
		ArrayList<CourseListDTO> cldtos = ss.stcourseList(userid);
		model.addAttribute("cldtos", cldtos);
	}	
	//한 학생의 한 강의의 능력단위 전체 - 평가일정표
	@RequestMapping(value = "/stListNcs", method = RequestMethod.GET)
	public void ncsList(@RequestParam("cid") int cid, Model model) throws Exception {
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
		logger.info("ncsList");
				
		model.addAttribute("cid", cid);
		model.addAttribute("cname", ss.stgetCname(cid));
		
		ArrayList<StNcsListDTO> nldtos = ss.stncsDistinctList(cid);
		
		for(int i=0; i<nldtos.size(); i++) {
			String ncs_num=nldtos.get(i).getNcs_num();
			int[] getIsTestResult = new int[2];
			System.out.println(ncs_num);
			
			String distTidStr= null;
			String distTidStr2= null;
			int distTid =-1;
			int distTid2 = -1;
			distTidStr = ss.stgetIsTestResult1(ncs_num, cid, userid);
			distTidStr2 = ss.stgetIsTestResult2(ncs_num, cid, userid);
			System.out.println(distTidStr);
			System.out.println(distTidStr2);
			if(distTidStr!=null) {
				distTid = Integer.parseInt(distTidStr); 
			}else {
				distTid=-1;
			}
			if(distTidStr2!=null) {
				distTid2 = Integer.parseInt(distTidStr2);
			}else {
				distTid2=-1;
			}
			System.out.println(distTid);
			System.out.println(distTid2);
			getIsTestResult[0] = distTid;
			getIsTestResult[1] = distTid2;
			
			System.out.println(getIsTestResult[0]);
			System.out.println(getIsTestResult[1]);
			
			nldtos.get(i).setIsTestResult(getIsTestResult);
			System.out.println(nldtos);
		}
		
		model.addAttribute("nldtos",nldtos);
		System.out.println(nldtos);		
	}
	
	//직접 강의명-능력단위명-타입 선택하는 창
	@RequestMapping(value = "/stTestSelect", method = RequestMethod.GET)
	public void testSelect(Model model) throws Exception {
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
		logger.info("");
		
    	//cname 가져오기
		ArrayList<TestSelectDTO> cnameList = ss.sttestSelectCname(userid);
		model.addAttribute("cnameList", cnameList);		
		System.out.println(cnameList);
    	//ncs_name 가져오기
		ArrayList<TestSelectDTO> ncsList = ss.sttestSelectNcsname(userid);
		model.addAttribute("ncsList", ncsList);
	}	
	//선택한 진단평가를 수행하는 페이지
	@RequestMapping(value = "/stTestPaper", method = RequestMethod.GET)
	public void testPaper(@RequestParam("cname") String cname, @RequestParam("ncs_name") String ncs_name, @RequestParam("type") int type, Model model) throws Exception {
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
		logger.info("");
		
		model.addAttribute("cname",cname);
		model.addAttribute("ncs_name",ncs_name);
		
		if(type==1) {
			model.addAttribute("type","사전");			
		}else if(type==2) {
			model.addAttribute("type","사후");						
		}
		
		String ncs_num = ss.stgetNCSnum(ncs_name);
		int cid = ss.stgetCid(cname);
		int tid = ss.stgetTid(cname, ncs_name, type);
		model.addAttribute("cid",cid);
		model.addAttribute("tid",tid);
		model.addAttribute("ncs_num",ncs_num);
		
		String resultAlreadyIn = null;
		resultAlreadyIn = ss.stisAlreadyIn(userid,tid);
		if(resultAlreadyIn!=null) {
			model.addAttribute("resultAlreadyIn", resultAlreadyIn);
		} else {
				
		TestInfoDTO tldtos = ss.sttestInfo(userid,tid);
		model.addAttribute("tldtos", tldtos);
		System.out.println("테스트"+tldtos);
			
		ArrayList<TestQuesListDTO> tqldtos = ss.sttestQues(tid);
		model.addAttribute("tqldtos", tqldtos);
		System.out.println("테스트"+tqldtos);	
		}
		
	}
	//수행한 기록이 있는 모든 진단평가지 목록 출력
	@RequestMapping(value = "/stTestResultList", method = RequestMethod.GET)
	public void stTestResultList(Model model) throws Exception {
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
		logger.info("");
		
    	ArrayList<TestResultListDTO> mtrdtos=ss.stmyTestResultList(userid);
    	model.addAttribute("mtrdtos", mtrdtos);
    	}
	
	//testResult를 db에 입력하는 단계
	@RequestMapping(value = "/stTestResultInsert", method = RequestMethod.POST)
	public String testInsertResultPOST(HttpServletRequest request, Model model) throws Exception {
//		Enumeration<String> e = request.getParameterNames();
//		while(e.hasMoreElements()) {
//			String name = e.nextElement();
//			String data[] = request.getParameterValues(name);
//			System.out.println(data);
//		}
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
		logger.info("");
		
		String stid = request.getParameter("stid");
		int tid=Integer.parseInt(request.getParameter("tid")); //고정
    	int count = Integer.parseInt(request.getParameter("ques_count"));
		
		ArrayList<Test_resultDTO> trdtos= new ArrayList<Test_resultDTO>();
    	for(int i=count;i>0;i--) { 
	    	String ques_num=request.getParameter("qn"+i);	    		    	
	    	int answer=Integer.parseInt(request.getParameter("answer"+i));
			Test_resultDTO trdto = new Test_resultDTO(tid, answer, ques_num, stid);
			trdtos.add(trdto);
    	}
    	
    	int resultInsertCount = ss.stcreateTestResult(trdtos);
    	if(resultInsertCount==count) {
    		System.out.println("정상 입력 확인 완료!");
    	}
		return "redirect:/student/stTestResult?tid="+tid;
	}
	//진단평가 수행결과를 보여주는 페이지 
	@RequestMapping(value = "/stTestResult", method = RequestMethod.GET)
	public void testResultGET(@RequestParam("tid") int tid, Model model) throws Exception {
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
		logger.info("");
		
		TestInfoDTO tldtos = ss.sttestInfo(userid,tid);
		model.addAttribute("tldtos", tldtos);
		
		System.out.println("con_tid"+tid);
		System.out.println("con_ss.showQuesAnswerResultList :"+ss.stshowQuesAnswerResultList(userid,tid));
		//진단문항 테이블 다시한번 보여주기 -> tid를 가지고 해당 문항들을 찾자. 
		ArrayList<TestQResultDTO> qrdtos=ss.stshowQuesAnswerResultList(userid,tid);		
		System.out.println("con_qrdtos :"+qrdtos);
		model.addAttribute("qrdtos", qrdtos);
		
		//총점과 평균
		ArrayList<TestResultSNADTO> trldtos=ss.sttestResultSumAvgList(userid,tid);
		System.out.println("con_trldtos :"+trldtos);
		model.addAttribute("trldtos", trldtos);  
		
	}
}
