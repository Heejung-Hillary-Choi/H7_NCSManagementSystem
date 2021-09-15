<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<style>
a { 
float: none !important;
}
</style>
<%
	String cid = request.getParameter("cid");
%>

<section>
	<h2><c:out value="${cname}"></c:out></h2>
	<h2>
		<c:out value="${ncs_name}"></c:out>
		<c:if test="${type eq '1'}">사전평가 </c:if>
		<c:if test="${type eq '2'}">사후평가 </c:if>
		성적조회
	</h2>

	<table style="width: 500px">
		<tr>
			<th>학생이름</th>
			<th>점수(총점)</th>
		</tr>
		<c:forEach items="${inScoreList}" var="inScoreList">
			<tr>
				<c:choose>
				<c:when test="${inScoreList.sum ne '미실시'}">
					<td><a href="/controller/institution/inScoreDetail?sname=${inScoreList.name}&cid=${cid}&ncs_num=${ncs_num}&type=${type}">${inScoreList.name }</a></td>
				</c:when>
				<c:otherwise>
					<td><a href="/controller/include/noScoreDetail">${inScoreList.name }</a></td>
				</c:otherwise>	
				</c:choose>		
				<td>${inScoreList.sum }</td>
			</tr>
		</c:forEach>
	</table>
</section>
<%@ include file="../include/footer.jsp"%>
