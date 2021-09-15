package com.h7nms.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.h7nms.dto.CourseListDTO;
import com.h7nms.dto.Test_resultDTO;
import com.h7nms.dto.StNcsListDTO;
import com.h7nms.dto.TestInfoDTO;
import com.h7nms.dto.TestQResultDTO;
import com.h7nms.dto.TestQuesListDTO;
import com.h7nms.dto.TestResultListDTO;
import com.h7nms.dto.TestResultSNADTO;
import com.h7nms.dto.TestSelectDTO;
import com.h7nms.dto.Test_detailDTO;

public interface StudentDAO {
	//나의 강의목록
	public ArrayList<CourseListDTO> stcourseList(String stid) throws Exception;
	public ArrayList<StNcsListDTO> stncsDistinctList(@Param("cid") int cid) throws Exception;

	//진단평가 수행내역이 이미 있나요?
	public String stgetIsTestResult1(@Param("ncs_num") String ncs_num,@Param("cid") int cid, @Param("userid") String stid) throws Exception ;
	public String stgetIsTestResult2(@Param("ncs_num") String ncs_num,@Param("cid") int cid, @Param("userid") String stid) throws Exception ;
	public String stisAlreadyIn(@Param("userid") String stid, @Param("tid") int tid) throws Exception;

	//testSelect SelectBox
	public ArrayList<TestSelectDTO> sttestSelectCname(String stid) throws Exception ;	
	public ArrayList<TestSelectDTO> sttestSelectNcsname(String stid) throws Exception ;	

	//testPaper = testList
	public TestInfoDTO sttestInfo(@Param("stid") String stid,@Param("tid") int tid) throws Exception;
	public ArrayList<TestQuesListDTO> sttestQues(@Param("tid") int tid) throws Exception;
	public ArrayList<Test_detailDTO> stTest_detail(@Param("ncs_num") String ncs_num) throws Exception ;	
		
	// testResult
	public ArrayList<TestQResultDTO> stshowQuesAnswerResultList(@Param("stid") String stid, @Param("tid") int tid ) throws Exception;
	public ArrayList<TestResultSNADTO> sttestResultSumAvgList(@Param("stid") String stid,@Param("tid") int tid) throws Exception;
		
	// testResultList
	public ArrayList<TestResultListDTO> stmyTestResultList(String stid) throws Exception;

	//get id or name
	public String stgetNCSnum(String ncs_name) throws Exception;
	public String stgetNCSname(String ncs_num) throws Exception;
	public String stgetCname(@Param("cid") int cid) throws Exception;
	public int stgetCid(@Param("cname") String cname) throws Exception;
	public int stgetTid(@Param("cname") String cname, @Param("ncs_name") String ncs_name, @Param("type") int type) throws Exception;
	
	//insert
	public int stcreateTestResult(ArrayList<Test_resultDTO> trdtos) throws Exception;
}