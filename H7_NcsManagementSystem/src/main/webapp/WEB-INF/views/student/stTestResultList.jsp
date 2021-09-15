<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css">
<section>
	<h2>지난 진단평가 결과 조회</h2>
	<table>
		<tr>
			<th>강의명</th>
			<th>교과목명(능력단위코드)</th>
			<th>결과확인</th>
		</tr>
		<c:forEach items="${mtrdtos}" var="mtrdtos">
			<tr>
				<td>${mtrdtos.cname}</td>
				<td>${mtrdtos.ncs_name}(${mtrdtos.ncs_num} )</td>
				<td><c:choose>
						<c:when test="${mtrdtos.type eq 1}">
							<button
								onclick="location.href='stTestResult?tid=${mtrdtos.tid}'">사전</button>
						</c:when>
						<c:otherwise>
							<button
								onclick="location.href='stTestResult?tid=${mtrdtos.tid}'">사후</button>
						</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
<%@ include file="../include/footer.jsp"%>