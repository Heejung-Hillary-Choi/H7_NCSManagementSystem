<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" type="text/css"
	href="/controller/resources/css/admin.css">
<%@ include file="../include/loginHeader.jsp"%>
<h2>-휴면 회원 관리-</h2>
<div id="searcha">
	<form action="dormant_search" method="get">
		<input type="text" id="search_text" name="search"> <input
			type="submit" id="search_btn" value="검색">
	</form>
</div>
<div id="item">
	<%@ include file="../include/adminMenu.jsp"%>
	<div id="admintable" class="scroll">
		<table>
			<tr>
				<th style="min-width: 55px;">순번</th>
				<th style="min-width: 55px;">상태</th>
				<th>ID</th>
				<th style="max-width: 120px;">비밀번호</th>
				<th style="min-width: 70px;">이름</th>
				<th>연락처</th>
				<th>이메일</th>
				<th>변경</th>
			</tr>
			<c:forEach items="${dormant_member}" var="dormant_member"
				varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td><c:choose>
							<c:when test="${dormant_member.enabled eq '1'}">
								<input type="button" value="비활성화"
									onclick="location.href='/controller/admin/dormant_update?userid=${dormant_member.userid}'">
							</c:when>
							<c:when test="${dormant_member.enabled eq '0'}">
								<input type="button" value="활성화"
									onclick="location.href='/controller/admin/dormant_update2?userid=${dormant_member.userid}'">
							</c:when>
						</c:choose></td>
					<td>${dormant_member.userid}</td>
					<td>${dormant_member.password}</td>
					<td>${dormant_member.name}</td>
					<td>${dormant_member.phone}</td>
					<td class="leftalign">${dormant_member.email}</td>
					<td>${dormant_member.enabled}</td>
				</tr>
			</c:forEach>
	<c:if test="${empty dormant_member}">
				<td colspan="3" style="text-align: center; color: gray;">현재 등록된 회원이 없습니다..</td>
			</c:if>
		</table>
	</div>
</div>

<%@ include file="../include/footer.jsp"%>