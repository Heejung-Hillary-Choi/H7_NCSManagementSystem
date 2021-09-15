package com.h7nms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.h7nms.dto.DefaultCourseDTO;
import com.h7nms.dto.InStudentListDTO;
import com.h7nms.dto.PrScoreListDTO;
import com.h7nms.dto.TestInfoDTO;
import com.h7nms.dto.TestQResultDTO;
import com.h7nms.dto.TestResultSNADTO;
import com.h7nms.dto.TestSelectDTO;

public interface InstitutionDAO {
	public void increateCourse(DefaultCourseDTO incd) throws Exception;
	public String ingetInName(String inid) throws Exception;
	public String ingetPrName(String prid) throws Exception;
	public int ingetNewCid() throws Exception;
	public List<DefaultCourseDTO> ingetOurCourses(String userid) throws Exception;
	public List<InStudentListDTO> ingetAllStudents() throws Exception;
	public DefaultCourseDTO ingetThisCourse(int cid) throws Exception;
	public InStudentListDTO ingetThisStudents(String stid) throws Exception;
	public void ininsertStinco(@Param("cid") int cid, @Param("stid") String stid) throws Exception;
	public String inisAlreadyIn(@Param("cid") int cid, @Param("stid") String stid) throws Exception;
	
	public List<TestSelectDTO> intestSelect(String userid) throws Exception;
	public List<TestSelectDTO> intestSelect2(String userid) throws Exception;
	public String inScoreList_ncs_name(String ncs_num) throws Exception;
	public List<PrScoreListDTO> inScoreList (@Param("cid") Integer cid, @Param("tid") Integer tid ) throws Exception;
	public List<TestInfoDTO> intestInfo(@Param("stid") String stid, @Param("cid") int cid,@Param("ncs_num") String ncs_num,@Param("type") int type) throws Exception;
	public List<TestQResultDTO> inshowQuesAnswerResultList (@Param("tid")int tid,@Param("stid") String stid) throws Exception;
	public List<TestResultSNADTO> intestResultSumAvgList (@Param("tid")int tid,@Param("stid") String stid) throws Exception;
	public String ingetNCSname(String ncs_num) throws Exception;
	public String ingetNCSnum(@Param("ncs_name") String ncs_name) throws Exception;
	public String ingetCname(Integer cid) throws Exception;
	public Integer ingetCid(String cname) throws Exception;
	public String ingetStid(String name) throws Exception;
	public Integer ingetTid(@Param("cid") int cid, @Param("ncs_num") String ncs_num, @Param("type") int type) throws Exception;
}
