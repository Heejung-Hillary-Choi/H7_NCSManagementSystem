package com.h7nms.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.h7nms.dao.ProfessorDAO;
import com.h7nms.dto.CourseListDTO;
import com.h7nms.dto.TestInfoDTO;
import com.h7nms.dto.TestQResultDTO;
import com.h7nms.dto.TestResultSNADTO;
import com.h7nms.dto.TestSelectDTO;
import com.h7nms.dto.PrStincoListDTO;
import com.h7nms.dto.PrScoreDTO;
import com.h7nms.dto.PrScoreListDTO;
import com.h7nms.dto.StScoreDTO;

@Service
public class ProfessorServiceImpl implements ProfessorService{
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<CourseListDTO> prcourseList(String userid) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		List<CourseListDTO> dtos=dao.prcourseList(userid);
		return dtos;
	}
	@Override
	public List<PrStincoListDTO> prStincoList(int cid) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		List<PrStincoListDTO> dtos=dao.prStincoList(cid);
		return dtos;
	}
	@Override
	public List<StScoreDTO> prStScoreList(int cid, String stid) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		List<StScoreDTO> dto = dao.prStScoreList(cid, stid);
		return dto;
	}

	@Override
	public List<PrScoreDTO> prScore(PrScoreDTO prScore) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		List<PrScoreDTO> dtos=dao.prScore(prScore);
		return dtos;
	}
	@Override
	public List<PrScoreListDTO> prScoreList(Integer cid, Integer tid) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		List<PrScoreListDTO> dto = dao.prScoreList(cid, tid);
		return dto;
	}
	@Override
	public List<PrScoreListDTO> prScoreList2(int cid, int tid) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		List<PrScoreListDTO> dto = dao.prScoreList2(cid, tid);
		return dto;
	}
	@Override
	public List<PrScoreListDTO> prScoreList3(int cid, int tid) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		List<PrScoreListDTO> dto = dao.prScoreList3(cid, tid);
		return dto;
	}
	
	@Override
	public List<TestInfoDTO> prtestInfo(int tid, String stid) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		List<TestInfoDTO> dtos = dao.prtestInfo(tid, stid);
		return dtos;
	}
	@Override
	public List<TestQResultDTO> prshowQuesAnswerResultList(int tid, String stid) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		List<TestQResultDTO> dtos = dao.prshowQuesAnswerResultList(tid, stid);
		return dtos;
	}
	@Override
	public List<TestResultSNADTO> prtestResultSumAvgList(int tid, String stid) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		 List<TestResultSNADTO> dtos=dao.prtestResultSumAvgList(tid, stid);
		return dtos;
	}
	
	@Override
	public List<TestSelectDTO> prtestSelect(String userid) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		 List<TestSelectDTO> dtos = dao.prtestSelect(userid);
		return dtos;
	}
	@Override
	public List<TestSelectDTO> prtestSelect2(String userid) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		 List<TestSelectDTO> dtos = dao.prtestSelect2(userid);
		return dtos;
	}

	@Override
	public String prgetNCSnum(String ncs_name) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		return dao.prgetNCSnum(ncs_name);
	}
	@Override
	public String prgetNCSname(String ncs_num) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		String ncs_name =dao.prgetNCSname(ncs_num);
		return ncs_name;		
	}
	@Override
	public String prgetStid(String name) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		return dao.prgetStid(name);
	}
	@Override
	public String prgetSname(String stid) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		return dao.prgetSname(stid);
	}
	@Override
	public Integer prgetCid(String cname) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		return dao.prgetCid(cname);
	}
	@Override
	public String prgetCname(int cid) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		return dao.prgetCname(cid);
	}
	@Override
	public Integer prgetTid(int cid, String ncs_num, int type) throws Exception {
		ProfessorDAO dao=sqlSession.getMapper(ProfessorDAO.class);
		String ncs_name=dao.prgetNCSname(ncs_num);
		return dao.prgetTid(cid, ncs_name, type);	
	}

}
