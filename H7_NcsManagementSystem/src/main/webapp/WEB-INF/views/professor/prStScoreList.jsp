<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<section>
	<h2>${cname}</h2>
	<h2>${sname} 학생 성적조회</h2>
	<table>
		<tr>
			<th>능력단위명</th>
			<th>진단평가</th>
			<th>점수(총점)</th>
		</tr>
		<c:forEach items="${stScoreList}" var="stScoreList">
			<tr>
				<c:choose>
					<c:when test="${stScoreList.type eq '1'}">
						<td rowspan="2">${stScoreList.ncs_name }</td>
						<td>사전</td>
						<td>${stScoreList.sum }</td>
					</c:when>
					<c:when test="${stScoreList.type eq '2'}">
					<td>사후</td>
						<td>${stScoreList.sum }</td>
					</c:when>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
</section>
<%@ include file="../include/footer.jsp"%>
