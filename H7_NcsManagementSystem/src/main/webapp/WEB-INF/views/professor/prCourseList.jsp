<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>

<section>
	<h2>강의 목록 조회</h2>
	<table>
		<tr>
			<th>강의명</th>
			<th>교육기관</th>
			<th>수강기간</th>
			<th>강의실</th>
			<th colspan='2'>수강생 관리</th>
		</tr>
		<c:forEach items="${prCourseList}" var="prCourseList">
			<tr>
				<td>${prCourseList.cname}</td>
				<td>${prCourseList.name}</td>
				<td>${prCourseList.date_full}</td>
				<td>${prCourseList.room_num}</td>
				<td><button
						onclick="location.href='/controller/professor/prStincoList?cid=${prCourseList.cid}'">
						출석부</button></td>
						<td><%-- <button
						onclick="location.href='/controller/professor/prSungJuk.do?cid=${prCourseList.cid }&cname=${prcdtos.cname}'"> --%>
						<button
						onclick="location.href='/controller/professor/prScore?cid=${prCourseList.cid}'">
						성적</button></td>
			</tr>
		</c:forEach>
	</table>
</section>
<%@ include file="../include/footer.jsp"%>
