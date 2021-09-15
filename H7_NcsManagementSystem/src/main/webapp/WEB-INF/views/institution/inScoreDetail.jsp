<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%> 
<style>
div .pdfBtn{
	text-align: right; 
	padding-right: 10%; 
	padding-top: 25px;
}
</style>

<%
	String cid = request.getParameter("cid");
%>
<div class="pdfBtn">
	<button id="savePdf">pdf저장</button>
	<button value="인쇄하기" id="print" onclick="pageprint()">인쇄</button>
</div>

<section id="sec">
<c:forEach items="${tldtos}" var="tldtos">
	<h2>자가진단평가 (${type}평가)</h2>	
	<h3 hidden="">${tldtos.cname}_${tldtos.ncs_name}_${type}_${tldtos.sname}</h3>
		<table>
			<tr>
				<th colspan="6">(${type}) 학습자 자가 진단 평가</th>
			</tr>
			<tr>
				<th>교육기관</th>
				<td>${tldtos.name}</td>
				<th>교육기간</th>
				<td colspan="3">${tldtos.date_session}</td>
			</tr>
			<tr>
				<th>평가일시</th>
				<td><fmt:formatDate value="${tldtos.date_test}" pattern="yyyy-MM-dd"/></td>
				<th>과정명</th>
				<td>${tldtos.cname}</td>
				<th>학생명</th>
				<td>${tldtos.sname}</td>
			</tr>
			<tr>
				<th>교과목</th>
				<td colspan="3">${tldtos.ncs_name}</td>
				<th>평가자</th>
				<td>${tldtos.pname}</td>
			</tr>
			<tr>
				<th>능력단위명</th>
				<td colspan="5">${tldtos.ncs_name}</td>
			</tr>
			<tr>
				<th>능력단위코드</th>
				<td colspan="5">${tldtos.ncs_num}</td>
			</tr>
		</table>
	</c:forEach>	
		<br>
		<table>
			<tr>
				<th>진단영역</th>
				<th>진단문항</th>
				<th>입력답안</th>
			</tr>
			<c:forEach items="${qrdtos}" var="qrdtos">
				<tr>
					<td>${qrdtos.ques_area}</td>
					<td align="left">${qrdtos.ques_num} ${qrdtos.question}</td>
					<td width="60px">${qrdtos.answer}</td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<table>
			<tr>
				<th>진단영역</th>
				<th>문항수</th>
				<th>점수</th>
				<th>점수/문항수</th>
			</tr>
			<c:forEach items="${trldtos}" var="trldtos">
				<tr>
					<td>${trldtos.ques_area }</td>
					<td>${trldtos.count }</td>
					<td>${trldtos.sum }</td>
					<td>${trldtos.avg }</td>
				</tr>
			</c:forEach>
				<tr><td colspan="4"><p align="left"><br>👉🏻자신의 점수를 문항 수로 나눈 값이 '3점' 이하에 해당하는 영역은 업무를 성공적으로 수행하는데 요구하는 능력이 부족한 것으로 교육훈련이나 개인학습을 통한 개발이 필요함. 
		<br>👉🏻평가항목(수행준거) 앞에 "나는 ~~ 할 수 있다"만 붙여서 학습자 스스로 자가진단 평가서로 활용함.<br><br></p>
				</td></tr>
		</table>
		<c:forEach items="${tldtos}" var="tldtos">
		<h2>${tldtos.name}</h2>		
		</c:forEach>
</section>
<%@ include file="../include/footer.jsp"%>
