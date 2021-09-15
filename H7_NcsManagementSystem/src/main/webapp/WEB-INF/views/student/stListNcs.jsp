<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<head>
<link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css">
</head>

<section>
	<h2>${cname}</h2>
	<h2>평가일정표</h2>
	<table>
		<tr>
			<th>능력단위명<br>(능력단위코드)</th>
			<th>교육기간</th>
			<th colspan="2">진단평가</th>
		</tr>
		<c:forEach items="${nldtos}" var="nldtos">
			<tr>
				<td rowspan="2">${nldtos.ncs_name}<br>(${nldtos.ncs_num})
				</td>
				<td rowspan="2">${nldtos.date_session}</td>
				<th>사전</th>
				<td>
					<c:choose>
						<c:when test="${nldtos.isTestResult[0] ne -1}">
							<button onclick="location.href='stTestResult?tid=${nldtos.isTestResult[0]}'">결과조회</button>
						</c:when>
						<c:otherwise>
							<button onclick="location.href='stTestPaper?cname=${cname}&ncs_name=${nldtos.ncs_name}&type=1'">평가수행</button>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th>사후</th>
				<td><c:choose>
					<c:when test="${nldtos.isTestResult[1] ne -1}">
							<button onclick="location.href='stTestResult?tid=${nldtos.isTestResult[1]}'">결과조회</button>
					</c:when>
					<c:otherwise>
							<button onclick="location.href='stTestPaper?cname=${cname}&ncs_name=${nldtos.ncs_name}&type=2'">평가수행</button>
					</c:otherwise></c:choose>
						</td>
			</tr>
		</c:forEach>
	</table>
<%@ include file="../include/footer.jsp"%>
