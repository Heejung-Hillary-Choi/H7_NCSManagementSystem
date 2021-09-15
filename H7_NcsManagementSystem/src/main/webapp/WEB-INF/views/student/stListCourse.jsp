<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<head>
<link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css">
</head>


<section>
	<h2>강의 목록 조회</h2>
	<table>
		<tr>
			<th>강의명</th>
			<th>강사이름</th>
			<th>수강기간</th>
			<th>강의실</th>
		</tr>
		<c:forEach items="${cldtos}" var="cldtos">
			<tr>
				<td><a href="stListNcs?cid=${cldtos.cid}" id="courseList">${cldtos.cname}</a></td>
				<td>${cldtos.name}</td>
				<td>${cldtos.date_full}</td>
				<td>${cldtos.room_num}</td>
			</tr>
		</c:forEach>
	</table>
<%@ include file="../include/footer.jsp"%>
