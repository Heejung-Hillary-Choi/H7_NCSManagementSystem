package com.h7nms.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.h7nms.service.BoardQnaService;
import com.h7nms.service.FileUploadService;


@Controller
@RequestMapping("/boardQna/*")
public class BoardQnaController {
	@Inject
	  private BoardQnaService bqs;
	@Inject
	private FileUploadService fs;
	@Resource(name="uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
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
		logger.info("board Qna list");
		
List<BoardDTO> blist = bqs.listSearchQna(pm);
		
		pm.setTotalCount(bqs.listSearchCountQna(pm));
		for(int i=0;i<blist.size();i++) {
			int bid = blist.get(i).getBid();
			String bwriter = bqs.getQnaWriterNamebyBID(bid);
			blist.get(i).setBwriter(bwriter);
		}
		model.addAttribute("blist",blist);
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void writeGet(Model model) throws Exception {
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
		logger.info("board Qna write GET");	
		
		model.addAttribute("bwriter", bqs.getQnaWriterNamebyUSERID(userid));
		
	}
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePost(BoardDTO board,RedirectAttributes rttr,MultipartFile file, Model model) throws Exception {
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
		logger.info("board Qna write POST");
		bqs.createQna(board);
		int bid=bqs.getQnabid();
		String bid2 = Integer.toString(bid);
		String bname = board.getBname();
		String filename2=bid2+"_"+file.getOriginalFilename();
		fs.addFile_qna(bid, bname, filename2); //파일 업로드
		File target= new File(uploadPath,filename2);
		FileCopyUtils.copy(file.getBytes(), target);
		rttr.addAttribute("filename2",filename2);
		rttr.addFlashAttribute("msg","success");
		return "redirect:/boardQna/list";		
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bid") int bid,PageMaker pm,Model model) throws Exception {
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
		logger.info("board Qna read");
		
		System.out.println(pm);
		model.addAttribute("read",bqs.readQna(bid));
		model.addAttribute("filename2",bqs.getfilename(bid));
		model.addAttribute("bwriter", bqs.getQnaWriterNamebyBID(bid));
		
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bid") int bid,PageMaker pm,Model model,RedirectAttributes rttr) throws Exception {
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
		logger.info("board Qna delete");

		bqs.deleteQna(bid);
		bqs.delete_qna_file(bid);
		
		rttr.addAttribute("page", pm.getPage());
		rttr.addAttribute("perPageNum", pm.getPerPageNum());
		rttr.addAttribute("searchType", pm.getSearchType());
		rttr.addAttribute("keyword", pm.getKeyword());

		rttr.addFlashAttribute("msg","success");
		return "redirect:/boardQna/list";	
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGet(@RequestParam("bid") int bid,PageMaker pm,Model model) throws Exception {
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
		logger.info("board Qna update get");

		System.out.println(pm);
		model.addAttribute("modify",bqs.readQna(bid));
		model.addAttribute("bwriter", bqs.getQnaWriterNamebyUSERID(userid));
		
	}


	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPost(BoardDTO board, String btitle, PageMaker pm,MultipartFile file, Model model,RedirectAttributes rttr) throws Exception {
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
		logger.info("board Qna update POST");

		bqs.updateQna(board);
		System.out.println(pm);
		
		int bid=bqs.getQnabid();
		String bid2 = Integer.toString(bid);
		String filename=bid2+"_"+file.getOriginalFilename();
		bqs.update_qna_file(filename, bid);
		File target= new File(uploadPath,filename);
		FileCopyUtils.copy(file.getBytes(), target);
		
		rttr.addAttribute("page", pm.getPage());
		rttr.addAttribute("perPageNum", pm.getPerPageNum());
		rttr.addAttribute("searchType", pm.getSearchType());
		rttr.addAttribute("keyword", pm.getKeyword());
		
		rttr.addFlashAttribute("msg","success");
		return "redirect:/boardQna/list";	
		}
	
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String replyGet(BoardDTO board, @RequestParam("originbid") int originbid, Model model) throws Exception {
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
		logger.info("board Qna reply GET");

		model.addAttribute("originbid",originbid);
		model.addAttribute("bwriter", bqs.getQnaWriterNamebyUSERID(userid));
		
				
		return "boardQna/reply";
	}
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String replyPost(BoardDTO board, @RequestParam("originbid") int originbid, @RequestParam("bname") String bname, @RequestParam("btitle") String btitle, @RequestParam("bcontent") String bcontent, RedirectAttributes rttr, PageMaker pm, Model model) throws Exception {
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
		logger.info("board Qna reply POST");

		model.addAttribute("originbid",originbid);
		int bgroup = bqs.getQnaOriginalBgroup(originbid);
		int bstep = bqs.getQnaOriginalBstep(originbid);
		int bindent = bqs.getQnaOriginalBindent(originbid);
		
		bqs.replyShapeQna(bgroup,bstep);
		bqs.replyCreateQna(bname, "re)"+btitle, bcontent, bgroup, bstep, bindent);

		rttr.addAttribute("page", pm.getPage());
		rttr.addAttribute("perPageNum", pm.getPerPageNum());
		rttr.addAttribute("searchType", pm.getSearchType());
		rttr.addAttribute("keyword", pm.getKeyword());

		rttr.addFlashAttribute("msg","success");
			
		return "redirect:/boardQna/list";
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