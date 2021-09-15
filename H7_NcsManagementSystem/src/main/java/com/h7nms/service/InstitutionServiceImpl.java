package com.h7nms.service;

import java.util.List;
import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.h7nms.dao.InstitutionDAO;
import com.h7nms.dto.DefaultCourseDTO;
import com.h7nms.dto.InStudentListDTO;
import com.h7nms.dto.PrScoreListDTO;
import com.h7nms.dto.TestInfoDTO;
import com.h7nms.dto.TestQResultDTO;
import com.h7nms.dto.TestResultSNADTO;
import com.h7nms.dto.TestSelectDTO;
@Service 
public class InstitutionServiceImpl implements InstitutionService {
	@Inject
	private SqlSession sqlSession;

	public void increateCourse(DefaultCourseDTO incd) throws Exception {
		System.out.println(sqlSession);
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		dao.increateCourse(incd);
	}	
	public String ingetInName(String inid) throws Exception {
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		String inname = dao.ingetInName(inid);		
		return inname;}
	public String ingetPrName(String prid) throws Exception {
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		String prname = dao.ingetPrName(prid);		
		return prname;}
	public int ingetNewCid() throws Exception {
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		int cid = dao.ingetNewCid();		
		return cid;}
	public List<DefaultCourseDTO> ingetOurCourses(String userid) throws Exception {
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		List<DefaultCourseDTO> dcdtos = dao.ingetOurCourses(userid);		
		return dcdtos;
	}
	public List<InStudentListDTO> ingetAllStudents() throws Exception {
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		List<InStudentListDTO> isldtos = dao.ingetAllStudents();		
		return isldtos;
	}
	public DefaultCourseDTO ingetThisCourse(int cid) throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		DefaultCourseDTO dto = dao.ingetThisCourse(cid);		
		return dto;	
	}
	public InStudentListDTO ingetThisStudents(String stid) throws Exception {
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		InStudentListDTO dto = dao.ingetThisStudents(stid);		
		return dto;
	}
	public void ininsertStinco(int cid, String stid) throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		dao.ininsertStinco(cid,stid);	
	}
	public boolean inisAlreadyIn(int cid, String stid) throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		String result = dao.inisAlreadyIn(cid,stid);
		if(result!=null) {
			return true; //이미 있다.
		}else {
			return false; //널이다==기존 값 없다.			
		}
	}
	
	public List<TestSelectDTO> intestSelect(String userid) throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		List<TestSelectDTO> dtos = dao.intestSelect(userid);
		return dtos;
	}
	public List<TestSelectDTO> intestSelect2(String userid) throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		List<TestSelectDTO> dtos = dao.intestSelect2(userid);
		return dtos;
	}
	public String inScoreList_ncs_name(String ncs_num) throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		dao.inScoreList_ncs_name(ncs_num);
		return dao.inScoreList_ncs_name(ncs_num);
	}
	public List<PrScoreListDTO> inScoreList (@Param("cid") Integer cid, @Param("tid") Integer tid ) throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		List<PrScoreListDTO> dto = dao.inScoreList(cid, tid);
		return dto;
	}
	public List<TestInfoDTO> intestInfo(@Param("stid") String stid, @Param("cid") int cid,@Param("ncs_num") String ncs_num,@Param("type") int type) throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		List<TestInfoDTO> dtos = dao.intestInfo(stid, cid, ncs_num, type);
		return dtos;
	}
	public List<TestQResultDTO> inshowQuesAnswerResultList (@Param("tid")int tid,@Param("stid") String stid) throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		List<TestQResultDTO> dtos = dao.inshowQuesAnswerResultList(tid, stid);
		return dtos;
	}
	public List<TestResultSNADTO> intestResultSumAvgList (@Param("tid")int tid,@Param("stid") String stid) throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		List<TestResultSNADTO> dtos=dao.intestResultSumAvgList(tid, stid);
		return dtos;
	}
	public String ingetNCSname(String ncs_num )throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		String ncs_name =dao.ingetNCSname(ncs_num);
		System.out.println("ncs_name "+ncs_name);
		return ncs_name;	
	}
	public String ingetNCSnum(@Param("ncs_name") String ncs_name) throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		return dao.ingetNCSnum(ncs_name);
	}
	public String ingetCname(Integer cid) throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		dao.ingetCname(cid);
		return dao.ingetCname(cid);
	}
	public Integer ingetCid(String cname) throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		return dao.ingetCid(cname);
	}
	public String ingetStid(String name)throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		return dao.ingetStid(name);
	}
	public Integer ingetTid(@Param("cid") int cid, @Param("ncs_num") String ncs_num, @Param("type") int type) throws Exception{
		InstitutionDAO dao=sqlSession.getMapper(InstitutionDAO.class);
		String ncs_name=dao.ingetNCSname(ncs_num);
		return dao.ingetTid(cid, ncs_name, type);	
	}
}
