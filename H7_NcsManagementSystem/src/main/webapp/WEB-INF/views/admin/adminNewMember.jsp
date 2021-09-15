<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../include/loginHeader.jsp"%>
<div id="item">
	<h2>-신규 회원 관리-</h2>
	<%@ include file="../include/adminMenu.jsp"%>
	<div id="admintable">
		<table>
			<tr>
				<th>순번</th>
				<th>ID</th>
				<th>이름</th>
				<th>연락처</th>
				<th>이메일</th>
				<th>요청 권한</th>
				<th colspan="2">확인</th>
			</tr>
			<c:forEach items="${new_member}" var="new_member">
				<tr>
					<td>${new_member.rownum}</td>
					<td>${new_member.userid}</td>
					<td>${new_member.name}</td>
					<td>${new_member.phone}</td>
					<td>${new_member.email}</td>
					<td>${new_member.wanted}</td>
					<td><input type="button" id="update" name="update" value="승인"
						onclick="location.href='/controller/admin/newMember_updete?userid=${new_member.userid}'"></td>
					<td><input type="button" id="update" name="delete" value="거부"
						onclick="location.href='/controller/admin/newMember_delete?userid=${new_member.userid}'"></td>
				</tr>
			</c:forEach>
			<c:if test="${empty new_member}">
				<td colspan="7" style="text-align: center; color: gray;">현재 가입승인을 기다리는 신규 회원이 없습니다..</td>
			</c:if>
		</table>
	</div>
</div>

<%@ include file="../include/footer.jsp"%>