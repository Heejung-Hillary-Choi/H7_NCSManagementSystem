package com.h7nms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.h7nms.dto.DefaultCourseDTO;
import com.h7nms.dto.InStudentListDTO;
import com.h7nms.dto.TestQResultDTO;
import com.h7nms.service.FileUploadService;
import com.h7nms.service.InstitutionService;

@Controller
@RequestMapping("/institution/*")
public class InstitutionController {
	@Resource(name="uploadPath")
	private String uploadPath;
	@Inject
	private FileUploadService fs;
	@Inject
	private InstitutionService is;
	
	private static final Logger logger = LoggerFactory.getLogger(InstitutionController.class);

	//신규 강의 개설 - 폼
	@RequestMapping(value="/insertCourseForm", method = RequestMethod.GET)
	public void insertCourseForm(Model model)  throws Exception{
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
		logger.info("insertCourseForm");
	}
	//신규 강의 개설 - 중간확인
/*	@RequestMapping(value="/insertCourseProve", method = RequestMethod.GET)
	public String insertCourseProve(DefaultCourseDTO incd, Model model)  throws Exception{
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
		logger.info("insertCourseProve");
		
		String prname = null;
		prname = is.ingetPrName(incd.getPrid());
		//중요한 점. 강사id를 틀리게 썼을 경우 입력이 안되어야 정상이겠쥬? 강사id를 먼저 검색한 다음 값이 null이면 create를 하지 맙시다. 
		if(prname==null) {
			return "/institution/insertFail";
		}
		model.addAttribute("professor",prname);
		model.addAttribute("institution",is.ingetInName(userid));
		
		
		return "/institution/insertCourseProve";
	}*/
	//신규 강의 개설 - 등록
	@RequestMapping(value="/insertCourseResult", method = RequestMethod.POST)
	public String insertCourseResult(DefaultCourseDTO incd, MultipartFile file, Model model)  throws Exception{
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
		logger.info("insertCourseResultPOST");

		String prname = null;
		prname = is.ingetPrName(incd.getPrid());
		//중요한 점. 강사id를 틀리게 썼을 경우 입력이 안되어야! 강사id를 먼저 검색한 다음 값이 null이면 create를 하지 맙시다. 
		if(prname==null) {
			return "/institution/insertFail";
		}
		model.addAttribute("professor",prname);
		model.addAttribute("institution",is.ingetInName(userid));
		is.increateCourse(incd);
		
		int cid = is.ingetNewCid();
		String cid2 = Integer.toString(cid); //파일 업로드 수정한 부분!
		String cname = incd.getCname(); //파일 업로드
		incd.setCid(cid);
		System.out.println(incd);
		System.out.println("orfilename" + file.getOriginalFilename());
		
		//파일 업로드 수정한 부분!
		String filename2=cid2+"_"+file.getOriginalFilename(); //파일 업로드
		fs.addFile(cid, cname, filename2); //파일 업로드
		File target= new File(uploadPath,filename2);
		FileCopyUtils.copy(file.getBytes(), target);
		
		model.addAttribute("newCourse", incd);
		model.addAttribute("savedName",filename2); //파일 업로드
		return "/institution/insertCourseResult";
	}
	//파일 업로드 주석처리!
	/*
	 * private String uploadFile(String originalFilename, byte[] bytes) throws
	 * Exception { UUID uid=UUID.randomUUID(); //파일 업로드 String
	 * savedName=uid.toString()+"_"+originalFilename; File target= new
	 * File(uploadPath,savedName); FileCopyUtils.copy(bytes, target); return
	 * savedName; }
	 */
	//파일 업로드
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		System.out.println("displayFileE: " + fileName);
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
			MediaType mType = null;
			if (formatName.equals("JPG")) {
				mType = MediaType.IMAGE_JPEG;
			} else if (formatName.equals("GIF")) {
				mType = MediaType.IMAGE_JPEG;
			} else if (formatName.equals("PNG")) {
				mType = MediaType.IMAGE_JPEG;
			}
			HttpHeaders headers = new HttpHeaders();
			in = new FileInputStream(uploadPath + fileName);
			if (fileName.contains("s_")) {
				headers.setContentType(mType);
				System.out.println("displayFileE: 2" + fileName);
			} else {
				System.out.println("displayFileE: 3" + fileName);
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition",
						"attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	//수강생 등록 - 폼
	@RequestMapping(value="/insertStincoForm", method = RequestMethod.GET)
	public void insertStincoForm(Model model)  throws Exception{
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
		logger.info("insertStincoForm - 수강생 등록");
		
		model.addAttribute("ourCourses", is.ingetOurCourses(userid));
		model.addAttribute("allStudents", is.ingetAllStudents());
	}
	//수강생 등록 - 중간확인
	@RequestMapping(value="/insertStincoProve", method = RequestMethod.GET)
	public void insertStincoProve(@RequestParam("cid") int cid, @RequestParam("stid") ArrayList<String> stid, Model model)  throws Exception{
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
		logger.info("insertStincoProve - GET - 수강생 등록 정보를 확인한 뒤 DB에 입력하겠습니다. ");
		
		DefaultCourseDTO dto = new DefaultCourseDTO();
		dto = is.ingetThisCourse(cid);
		model.addAttribute("professor",is.ingetPrName(dto.getPrid()));
		model.addAttribute("institution",is.ingetInName(userid));
		model.addAttribute("thisCourse",dto);
		
		ArrayList<InStudentListDTO> thisStudents = new ArrayList<InStudentListDTO>();
		for(int i=0;i<stid.size();i++) {
			if(!is.inisAlreadyIn(cid, stid.get(i))) {
				thisStudents.add(is.ingetThisStudents(stid.get(i)));
			}
		}
		model.addAttribute("thisStudents",thisStudents);
	}
	//수강생 등록 - 등록
	@RequestMapping(value="/insertStincoResult", method = RequestMethod.POST)
	public void insertStincoResultPOST(@RequestParam("cid") int cid, @RequestParam("stid") ArrayList<String> stid, Model model)  throws Exception{
		System.out.println("model :"+model);
		logger.info("insertStincoResult - POST - 수강생 등록을 실시합니다.");
		
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
		
		for(int i=0;i<stid.size();i++) {
			is.ininsertStinco(cid,stid.get(i));
		}
	}
	
	//학생 시험지 조회 - 검색
	@RequestMapping(value="/inTestSelect", method = RequestMethod.GET)
	public void inTestSelect(Model model) throws Exception {
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
		
		model.addAttribute("cnameList",is.intestSelect(userid));
		model.addAttribute("ncsList",is.intestSelect2(userid));
	}
	//학생 시험지 조회 - 전체 학생
	@RequestMapping(value="/inScoreList", method = RequestMethod.GET)
	public void inScoreList(@RequestParam("cname") String cname, @RequestParam("ncs_name") String ncs_name, @RequestParam("type") int type, Model model) throws Exception {
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
		logger.info("inScoreList");
		
		model.addAttribute("cname",cname);
		model.addAttribute("ncs_name",ncs_name);
		model.addAttribute("type",type);
		
		String ncs_num = is.ingetNCSnum(ncs_name);
		int cid = is.ingetCid(cname);
		int tid = is.ingetTid(cid, ncs_num, type);
		model.addAttribute("cid",cid);
		model.addAttribute("tid",tid);
		model.addAttribute("ncs_num",ncs_num);
		model.addAttribute("inScoreList",is.inScoreList(cid, tid));
	}
	//학생 시험지 조회 - 특정 학생 
	@RequestMapping(value="/inScoreDetail", method = RequestMethod.GET)
	public void inScoreDetail(@RequestParam("sname") String sname ,@RequestParam("cid") int cid,@RequestParam("ncs_num") 
	String ncs_num,@RequestParam("type") int type, Model model) throws Exception {
		logger.info("inScoreDetail");
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
			
				String stid=is.ingetStid(sname);
				int tid=is.ingetTid(cid, ncs_num, type);

				System.out.print("stid"+stid);
				if(type==1) {
					model.addAttribute("type","사전");
				}else if(type==2) {
					model.addAttribute("type","사후");
				}
				
				model.addAttribute("tid",tid);
				model.addAttribute("stid",stid);
				
				List<TestQResultDTO> qrdtos = null;
				qrdtos = is.inshowQuesAnswerResultList(tid, stid);
//				if(qrdtos.isEmpty()) {
//					model.addAttribute("error","error");
//				}
				
				model.addAttribute("tldtos",is.intestInfo(stid, cid, ncs_num, type));
				model.addAttribute("qrdtos",qrdtos);
				model.addAttribute("trldtos",is.intestResultSumAvgList(tid, stid));			
	}

}
