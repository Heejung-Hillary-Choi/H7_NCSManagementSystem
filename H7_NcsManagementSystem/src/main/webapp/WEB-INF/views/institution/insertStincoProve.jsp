<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>

<section>
	<h2>수강생 등록</h2>
	<h3>등록 전 모든 정보가 정확한지 다시 한번 확인해주세요.</h3>
	<h3>하단의 등록 버튼을 누르면 DB에 최종 등록됩니다.</h3>
		
	<form action="${pageContext.request.contextPath}/institution/insertStincoResult" method="POST">	
		<input type="hidden" name="cid" value="${thisCourse.cid}">	
		<div id="courseInfoDiv">	
		<h2>선택 강의 정보</h2>
		<table>
			<tr><th colspan="2">강의명(고유학수번호)</th></tr>
			<tr><td colspan="2">${thisCourse.cname}(${thisCourse.cid})</td></tr>
				<tr>
					<th>교육기관(현재 로그인)</th>
					<th>강사명(강사ID)</th>
				</tr>
				<tr>
					<td>${institution}(${thisCourse.inid})</td>
					<td>${professor}(${thisCourse.prid})</td>
				</tr>
				<tr>
					<th>전체 교육기간</th>
					<th>강의실</th>
				</tr>
				<tr>
					<td>${thisCourse.date_full}</td>
					<td>${thisCourse.room_num}</td>
				</tr>
			</table>
		</div>

		<h2>선택 학생 정보</h2>
		<div id="selectStudents" style="overflow: auto; height: 300px;">	
			<table>
				<tr><th colspan="5">※이미 해당 강의에 등록된 수강생은 제외되었습니다.</th></tr>
				<tr>
					<th>순 번</th>
					<th>학생ID</th>
					<th>이 름</th>
					<th>연락처</th>
					<th>기본주소</th>
				</tr>
				<c:if test="${empty thisStudents}">
				<tr>
				<td colspan="5">모든 학생이 이미 등록되어 있습니다.</td>
				</tr>
				</c:if>
				<c:forEach items="${thisStudents}" var="thisStudents" varStatus="status">
					<input type="hidden" name="stid" value="${thisStudents.stid}">	
				<tr>
					<td>${status.count}</td>
					<td>${thisStudents.stid}</td>
					<td>${thisStudents.name}</td>
					<td>${thisStudents.phone}</td>
					<td>${thisStudents.address}</td>
				</tr>			
				</c:forEach>
			</table>
		</div>
<c:if test="${!empty thisStudents}"><input type="submit" class="button" value="등록"></c:if>
	</form>
<c:if test="${empty thisStudents}"><button onclick="history.go(-1)">다시 선택</button></c:if>
</section>
<%@ include file="../include/footer.jsp"%>
