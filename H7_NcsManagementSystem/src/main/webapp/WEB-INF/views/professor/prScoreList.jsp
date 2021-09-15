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
	<div class="dropdown">
		<button onclick="myFunction()" class="dropbtn">정렬기준</button>
		<div id="myDropdown" class="dropdown-content">
			<a
				href="/controller/professor/prScoreList?cid=${cid}&ncs_num=${ncs_num}&type=${type}&sort=1">이름순</a>
			<a
				href="/controller/professor/prScoreList?cid=${cid}&ncs_num=${ncs_num}&type=${type}&sort=2">성적
				높은순</a> <a
				href="/controller/professor/prScoreList?cid=${cid}&ncs_num=${ncs_num}&type=${type }&sort=3">성적
				낮은순</a>
		</div>
	</div>
	<table style="width: 500px">
		<tr>
			<th>학생이름</th>
			<th>점수(총점)</th>
		</tr>
		<c:forEach items="${prScoreList}" var="prScoreList">
			<tr>
				<c:choose>
				<c:when test="${prScoreList.sum ne '미실시'}">
					<td><a href="/controller/professor/prScoreDetail?sname=${prScoreList.name}&cid=${cid}&ncs_num=${ncs_num}&type=${type}">${prScoreList.name }</a></td>
				</c:when>
				<c:otherwise>
					<td><a href="/controller/include/noScoreDetail">${prScoreList.name }</a></td>
				</c:otherwise>	
				</c:choose>		
				<td>${prScoreList.sum }</td>
			</tr>
		</c:forEach>
	</table>
</section>

<script>
	/* When the user clicks on the button, 
	 toggle between hiding and showing the dropdown content */
	function myFunction() {
		document.getElementById("myDropdown").classList.toggle("show");
	}

	// Close the dropdown if the user clicks outside of it
	window.onclick = function(event) {
		if (!event.target.matches('.dropbtn')) {
			var dropdowns = document.getElementsByClassName("dropdown-content");
			var i;
			for (i = 0; i < dropdowns.length; i++) {
				var openDropdown = dropdowns[i];
				if (openDropdown.classList.contains('show')) {
					openDropdown.classList.remove('show');
				}
			}
		}
	}
</script>

<%@ include file="../include/footer.jsp"%>
