package com.h7nms.service;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.h7nms.dto.CourseListDTO;
import com.h7nms.dto.Test_detailDTO;
import com.h7nms.dto.Test_resultDTO;
import com.h7nms.dto.StNcsListDTO;
import com.h7nms.dto.TestInfoDTO;
import com.h7nms.dto.TestQResultDTO;
import com.h7nms.dto.TestQuesListDTO;
import com.h7nms.dto.TestResultListDTO;
import com.h7nms.dto.TestResultSNADTO;
import com.h7nms.dto.TestSelectDTO;
import com.h7nms.dao.StudentDAO;

@Service
public class StudentServiceImpl implements StudentService{
	@Inject
	private SqlSession sqlSession;

	@Override
	public ArrayList<CourseListDTO> stcourseList(String stid) throws Exception {
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.stcourseList(stid); }
	@Override
	public ArrayList<StNcsListDTO> stncsDistinctList(int cid) throws Exception {
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.stncsDistinctList(cid); }
	@Override
	public String stgetIsTestResult1(String ncs_num, int cid, String stid) throws Exception {
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.stgetIsTestResult1(ncs_num, cid, stid); }
	@Override
	public String stgetIsTestResult2(String ncs_num, int cid, String stid) throws Exception {
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.stgetIsTestResult2(ncs_num, cid, stid); }
	@Override
	public ArrayList<Test_detailDTO> stTest_detail(String ncs_num) throws Exception {
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.stTest_detail(ncs_num);	}
	@Override
	public ArrayList<TestSelectDTO> sttestSelectCname(String stid) throws Exception {
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.sttestSelectCname(stid); }
	@Override
	public ArrayList<TestSelectDTO> sttestSelectNcsname(String stid) throws Exception {
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.sttestSelectNcsname(stid);	}
	@Override
	public ArrayList<TestQResultDTO> stshowQuesAnswerResultList(String stid, int tid) throws Exception {
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.stshowQuesAnswerResultList(stid,tid);	}
	@Override
	public ArrayList<TestResultSNADTO> sttestResultSumAvgList(String stid, int tid) throws Exception {
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.sttestResultSumAvgList(stid, tid);	}
	@Override
	public ArrayList<TestResultListDTO> stmyTestResultList(String stid) throws Exception {
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.stmyTestResultList(stid);	}
	@Override
	public TestInfoDTO sttestInfo(String stid, int tid) throws Exception {
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.sttestInfo(stid, tid);	}
	@Override
	public ArrayList<TestQuesListDTO> sttestQues(int tid) throws Exception {
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.sttestQues(tid);	}
	@Override
	public String stgetNCSname(String ncs_num) throws Exception {
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.stgetNCSname(ncs_num);	}
	@Override
	public String stgetNCSnum(String ncs_name) throws Exception{
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.stgetNCSnum(ncs_name);	}
	@Override
	public String stgetCname(int cid) throws Exception{
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.stgetCname(cid);	}
	@Override
	public int stgetCid(String cname) throws Exception{
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.stgetCid(cname);	}
	@Override
	public int stgetTid(@Param("cname") String cname, @Param("ncs_name") String ncs_name, @Param("type") int type) throws Exception{
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.stgetTid(cname, ncs_name, type);	}
	@Override
	public int stcreateTestResult(ArrayList<Test_resultDTO> trdtos) throws Exception {
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.stcreateTestResult(trdtos);
	}
	@Override
	public String stisAlreadyIn(String stid, int tid) throws Exception{
		StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
		return dao.stisAlreadyIn(stid, tid);
	}
}

//@Override
//public String stisTestResult(String stid, int tid) throws Exception {
//	StudentDAO dao = sqlSession.getMapper(StudentDAO.class);		
//	return dao.isTestResult(stid, tid);	}
