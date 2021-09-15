<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@ include file="../include/header.jsp"%>
<section>
	<h2>${cname}</h2>
	<h2>성적조회</h2>
	<table>
		<tr>
			<th>능력단위명</th>
			<th colspan="2">진단평가 결과조회</th>
		</tr>
		<c:forEach items="${prScore}" var="prScoredtos">
			<tr>
				<td>${prScoredtos.ncs_name}</td>				
				<td>
						<button
							onclick="location.href='/controller/professor/prScoreList?cid=${prScoredtos.cid}&ncs_num=${prScoredtos.ncs_num}&type=1&sort=1'"> 
							사전</button>
				</td>
				<td>
						<button
							onclick="location.href='/controller/professor/prScoreList?cid=${prScoredtos.cid}&ncs_num=${prScoredtos.ncs_num}&type=2&sort=1'"> 
							사후</button>
					</td>
			</tr>
		</c:forEach>
	</table>
<%@ include file="../include/footer.jsp"%>
