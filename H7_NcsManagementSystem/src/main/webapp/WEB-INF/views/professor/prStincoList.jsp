<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<%@ page session="false"%>
<style>
a { 
float: none !important;
}
</style>
<section id="sec">
	<h2>${cname}</h2>
	<h2>강의 출석부 조회</h2>
	
	<table style="width:500px">
		<tr>
			<th>순번</th>
			<th>학생 이름</th>
			<th>출석율</th>
		</tr>
		<c:forEach items="${prStincoList}" var="prsicldtos" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td><a href="/controller/professor/prStScoreList?
						cid=${cid}&stid=${prsicldtos.stid}">${prsicldtos.sname}</a></td>
				<td>${prsicldtos.attend_rate}</td>
			</tr>
		</c:forEach>
	</table>
</section>
<%@ include file="../include/footer.jsp"%>
