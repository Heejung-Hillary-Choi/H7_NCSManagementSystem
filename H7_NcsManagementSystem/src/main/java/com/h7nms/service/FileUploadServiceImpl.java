package com.h7nms.service;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.h7nms.dao.FileUploadDAO;

@Service 
public class FileUploadServiceImpl implements FileUploadService {
	@Inject
	private SqlSession sqlSession;

	@Override
	public void addFile(int cid, String cname, String filename) throws Exception {
		FileUploadDAO dao = sqlSession.getMapper(FileUploadDAO.class);
		dao.addFile(cid, cname, filename);	}
	@Override
	public void addFile_qna(int bid, String bname, String filename) throws Exception {
		FileUploadDAO dao = sqlSession.getMapper(FileUploadDAO.class);
		dao.addFile_qna(bid, bname, filename);	}

	@Override
	public void addFile_notice(int bid, String bname, String filename) throws Exception {
		FileUploadDAO dao = sqlSession.getMapper(FileUploadDAO.class);
		dao.addFile_notice(bid, bname, filename);	}

	@Override
	public int isFileAlreadyIn_notice(int bid) throws Exception {
		FileUploadDAO dao = sqlSession.getMapper(FileUploadDAO.class);
		return dao.isFileAlreadyIn_notice(bid);	}
	@Override
	public int isFileAlreadyIn_qna(int bid) throws Exception {
		FileUploadDAO dao = sqlSession.getMapper(FileUploadDAO.class);
		return dao.isFileAlreadyIn_qna(bid);	}

}
