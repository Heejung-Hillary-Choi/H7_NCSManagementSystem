package com.h7nms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.FilenameUtils;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.h7nms.dto.BoardDTO;
import com.h7nms.dto.PageMaker;
import com.h7nms.service.BoardNoticeService;
import com.h7nms.service.FileUploadService;

@Controller
@RequestMapping("/boardNotice/*")
public class BoardNoticeController {
	@Inject
	  private BoardNoticeService bns;
	@Inject
	private FileUploadService fs;
	@Resource(name="uploadPath")
	private String uploadPath;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	//list (전체 목록)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void blist(PageMaker pm,Model model) throws Exception {
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
		logger.info("board Notice list");
		
		List<BoardDTO> blist = bns.listSearchNotice(pm);
		
		pm.setTotalCount(bns.listSearchCountNotice(pm));
		for(int i=0;i<blist.size();i++) {
			int bid = blist.get(i).getBid();
			String bwriter = bns.getNoticeWriterNamebyBID(bid);
			blist.get(i).setBwriter(bwriter);
		}
		model.addAttribute("blist",blist);
		
		//model.addAttribute("pageMaker",pm);
		//return "sboard/list";
	}
	
	//write 시작 (새 글쓰기)
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeGet(Model model) throws Exception {
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
		logger.info("write Get");
		
		model.addAttribute("bwriter", bns.getNoticeWriterNamebyUSERID(userid));
		
		if(userauth.contains("ADMIN")||userauth.contains("INSTITUTION")) {
			return "/boardNotice/write"; 
		}else {
			return "/include/noBoardAuth";
		}		
	}
	
	//write 완료
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePost(BoardDTO board,RedirectAttributes rttr, MultipartFile file,Model model) throws Exception {
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
		logger.info("write Post");
		
		bns.createNotice(board);
		int bid=bns.getNoticebid();
		String bid2 = Integer.toString(bid);
		String bname = board.getBname();
		String filename2=bid2+"_"+file.getOriginalFilename();
		fs.addFile_notice(bid, bname, filename2); //파일 업로드
		File target= new File(uploadPath,filename2);
		FileCopyUtils.copy(file.getBytes(), target);
		rttr.addAttribute("filename2",filename2);
		//rttr.addAttribute("msg","success");
		rttr.addFlashAttribute("msg","success");
		return "redirect:/boardNotice/list";		
	}
	
	//read (상세 글 보기
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bid") int bid, PageMaker pm,Model model) throws Exception {
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
		logger.info("read");

		model.addAttribute("read",bns.readNotice(bid));
		model.addAttribute("filename2",bns.getfilename(bid));
		model.addAttribute("bwriter", bns.getNoticeWriterNamebyBID(bid));		
	}

	//글삭제
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String deleteNotice(@RequestParam("bid") int bid,PageMaker pm,Model model,RedirectAttributes rttr) throws Exception {
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
		logger.info("deleteNotice");

		bns.deleteNotice(bid);
		bns.delete_notice_file(bid);
		rttr.addAttribute("page", pm.getPage());
		rttr.addAttribute("perPageNum", pm.getPerPageNum());
		rttr.addAttribute("searchType", pm.getSearchType());
		rttr.addAttribute("keyword", pm.getKeyword());

		rttr.addFlashAttribute("msg","success");
		return "redirect:/boardNotice/list";	
	}
		
	//글수정 시작
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String updateNoticeGet(@RequestParam("bid") int bid,PageMaker pm,Model model) throws Exception {
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
		logger.info("updateNotice Start");

		model.addAttribute("modify",bns.readNotice(bid));
		model.addAttribute("bwriter", bns.getNoticeWriterNamebyBID(bid));

		return "/boardNotice/modify"; 

	}

	//글수정 완료
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String updateNoticePost(BoardDTO board, @RequestParam("bid") int bid, PageMaker pm, Model model, MultipartFile file, RedirectAttributes rttr) throws Exception {
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
		logger.info("updateNotice Post");

		bns.updateNotice(board);
		
		String bid2 = Integer.toString(bid);
		String bname = board.getBname();
		String filename=bid2+"_"+file.getOriginalFilename();

		int isFileAlreadyIn = fs.isFileAlreadyIn_notice(bid);
		if(isFileAlreadyIn == 0 ) {
			fs.addFile_notice(bid, bname, filename);			
		}else {
			bns.update_notice_file(filename, bid);			
		}
		
		File target= new File(uploadPath,filename);
		FileCopyUtils.copy(file.getBytes(), target);
				
		rttr.addAttribute("page", pm.getPage());
		rttr.addAttribute("perPageNum", pm.getPerPageNum());
		rttr.addAttribute("searchType", pm.getSearchType());
		rttr.addAttribute("keyword", pm.getKeyword());

		rttr.addFlashAttribute("msg","success");
		return "redirect:/boardNotice/list";	
	}
	
	//파일 업로드
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
			System.out.println("fileName :"+fileName);
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
	
}