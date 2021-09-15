<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@ include file="../include/header.jsp"%>
<section>
	<h2>신규 강의 개설</h2>
	<h3>등록 전 모든 정보가 정확한지 다시 한번 확인해주세요.</h3>
	<h3>하단의 등록 버튼을 누르면 DB에 최종 등록됩니다.</h3>
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
<%@ include file="../include/footer.jsp"%>
