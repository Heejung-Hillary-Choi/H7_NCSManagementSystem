<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@ include file="../include/header.jsp"%>

<section>
	<h2>DB에 등록이 완료되었습니다.</h2>
	<h2>입력된 정보를 확인하세요.</h2>
	<table>
		<tr>
			<th colspan="2">강의명(고유학수번호)</th>
		</tr>
		<tr>
			<td colspan="2">${newCourse.cname}(${newCourse.cid})</td>
		</tr><tr>
			<th>교육기관(현재 로그인)</th>
			<th>강사명(강사ID)</th>
		</tr><tr>
			<td>${institution}(${newCourse.inid})</td>
			<td>${professor}(${newCourse.prid})</td>
		</tr><tr>
			<th>전체 교육기간</th>
			<th>강의실</th>
		</tr><tr>
			<td>${newCourse.date_full}</td>
			<td>${newCourse.room_num}</td>
		</tr>
		<tr>
			<th colspan="2">csv 파일명</th>
		</tr>
		<tr>
			<td colspan="2"><a href="/controller/institution/displayFile?fileName=${savedName}">${savedName}</a></td>
		</tr>
	</table>
</section>
<%@ include file="../include/footer.jsp"%>
