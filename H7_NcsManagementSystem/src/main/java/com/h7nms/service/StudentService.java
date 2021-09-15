package com.h7nms.service;

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

public interface StudentService {
	//나의 강의목록
	public ArrayList<CourseListDTO> stcourseList(String stid) throws Exception;
	public ArrayList<StNcsListDTO> stncsDistinctList(@Param("cid") int cid) throws Exception;

	//진단평가 수행내역이 이미 있나요?
	public String stgetIsTestResult1(String ncs_num, @Param("cid") int cid, String stid) throws Exception ;
	public String stgetIsTestResult2(String ncs_num, @Param("cid") int cid, String stid) throws Exception ;
	public String stisAlreadyIn(String stid, int tid) throws Exception;

	//testSelect SelectBox
	public ArrayList<TestSelectDTO> sttestSelectCname(String stid) throws Exception ;	
	public ArrayList<TestSelectDTO> sttestSelectNcsname(String stid) throws Exception ;	

	//testPaper = testList
	public TestInfoDTO sttestInfo(String stid, int tid ) throws Exception;
	public ArrayList<TestQuesListDTO> sttestQues(int tid) throws Exception;
	public ArrayList<Test_detailDTO> stTest_detail(String ncs_num) throws Exception ;	
		
	// testResult
	public ArrayList<TestQResultDTO> stshowQuesAnswerResultList(@Param("stid") String stid,@Param("tid") int tid ) throws Exception;
	public ArrayList<TestResultSNADTO> sttestResultSumAvgList(@Param("stid") String stid,@Param("tid") int tid) throws Exception;
		
	// testResultList
	public ArrayList<TestResultListDTO> stmyTestResultList(String stid) throws Exception;

	//get id or name
	public String stgetNCSnum(String ncs_name) throws Exception;
	public String stgetNCSname(String ncs_num) throws Exception;
	public String stgetCname(@Param("cid") int cid) throws Exception;
	public int stgetCid(@Param("cname") String cname) throws Exception;
	public int stgetTid(@Param("cname") String cname,@Param("ncs_name") String ncs_name, @Param("type") int type) throws Exception;
	
	//insert
	public int stcreateTestResult(ArrayList<Test_resultDTO> trdtos) throws Exception;
}

/*
	// 학생이 자기가 듣는 강의 목록을 출력하는 메소드
	public ArrayList<CourseListDTO> courseList(String stid) throws Exception;
	//cname을 가지고 해당 강의의 전체 평가일정표를 보여주는 메소드
	public ArrayList<StNcsListDTO> ncsDistinctList(int cid) throws Exception;
	//한 강의에서, 각각의 능력단위에 따라, 사전-사후 평가 내역이 존재하는지 찾는다.
	public String getIsTestResult1(String ncs_num, String cname, String stid) throws Exception ;
	public String getIsTestResult2(String ncs_num, String cname, String stid) throws Exception ;
	// 능력단위코드에 따라 해당하는 진단문항 및 test_detail 테이블의 값을 반환하는 메소드
	public ArrayList<Test_detailDTO> Test_detail(String ncs_num) throws Exception ;	
	// 강의명 가져오는 메소드
	public ArrayList<TestSelectDTO> testSelectCname(String stid) throws Exception ;	
	// 능력단위 가져오는 메소드
	public ArrayList<TestSelectDTO> testSelectNcsname(String stid) throws Exception ;	
	// 사용자가 선택한 값에 따라 시험지의 '정보'테이블(상단)을 채워서 보여주는 메소드 1
	public TestInfoDTO testInfo(String stid, int cid, String ncs_name, int type) throws Exception;		
	// 사용자가 선택한 값에 따라 시험지의 '문항'테이블(하단)을 채워서 보여주는 메소드 2
	public ArrayList<TestQuesListDTO> testQues(int cid, String ncs_name, int type) throws Exception;
	// 사용자가 선택한 값에 따라 tid 찾는 메소드
	public int stgetTid(int cid, String ncs_name, int type) throws Exception;
	// tid, stid가 일치하는 값이 test_result 테이블에 있는지 찾는 메소드
	public String isTestResult(String stid, int tid) throws Exception;
	// 사용자가 콤보박스에서 선택한 cname에 따라 cid 찾는 메소드
	public int stgetCid(String cname) throws Exception;
	// 사용자가 입력한 점수값을 DB의 test_result테이블에 입력하는 메소드
	public void createTestResult(ArrayList<Test_resultDTO> trdtos) throws Exception;
	// test_result를 한번더 보여주는 메소드
	public ArrayList<TestQResultDTO> showQuesAnswerResultList(int tid, String stid) throws Exception;
	// DB의 test_result테이블에서 해당학생, 해당시험으로 저장된 점수값들의 '총점과 평균'을 계산하여 불러오는 메소드
	public ArrayList<TestResultSNADTO> testResultSumAvgList(int tid,String stid) throws Exception;
	// testResultList에서 로그인한 stid로 수행한 결과가 있는 시험정보 보여주는 메소드
	public ArrayList<TestResultListDTO> myTestResultList(String stid) throws Exception;
	public String stgetNCSnum(String ncs_name) throws Exception;
 */
 