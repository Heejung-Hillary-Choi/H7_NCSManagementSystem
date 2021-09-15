<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../include/loginHeader.jsp"%>
<h2>-진단평가지 등록-</h2>
<div id="searcha">
	<form action="ncstest_search" method="get">
		<input type="text" id="search_text" name="search"> <input
			type="submit" id="search_btn" value="검색">
	</form>
</div>
<div id="item">
	<%@ include file="../include/adminMenu.jsp"%>
	<div id="admintable" class="scroll">
		<table>
			<tr>
				<th>강의명</th>
				<th>강사 아이디</th>
				<th>파일명</th>
			</tr>
			<c:forEach items="${ncstest}" var="ncstest">
				<tr style="height: 40px;">
					<td>${ncstest.cname}</td>
					<td>${ncstest.prid}</td>
					<td class="leftalign"><a style="margin: 0px;" href="/controller/institution/displayFile?fileName=${ncstest.filename}">
							${ncstest.filename}</a></td>
				</tr>
			</c:forEach>
			<c:if test="${empty ncstest}">
				<td colspan="3" style="text-align: center; color: gray;">현재 등록된
					파일이 없습니다..</td>
			</c:if>
		</table>
	</div>
</div>
<%@ include file="../include/footer.jsp"%>