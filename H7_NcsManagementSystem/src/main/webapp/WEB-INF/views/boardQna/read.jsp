<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@include file="../include/header.jsp"%>
<script>
	$(document).ready(function() {
		var fObject = $(".form");

		$(".btnReply").on("click", function() {
			fObject.attr("method", "get");
			fObject.attr("action", "/controller/boardQna/reply");
			fObject.submit();
		})
		$(".btnList").on("click", function() {

			fObject.attr("method", "get");
			fObject.attr("action", "/controller/boardQna/list");
			fObject.submit();

		})
		$(".btnRemove").on("click", function() {
			fObject.attr("action", "/controller/boardQna/remove");
			fObject.submit();
		})
		$(".btnModify").on("click", function() {
			fObject.attr("method", "get");
			fObject.attr("action", "/controller/boardQna/modify");
			fObject.submit();
		})
	});
</script>

<div class="main">

	<form class="form" method="post">
		<input type='hidden' name='bid' value="${read.bid}"> <input
			type='hidden' name='originbid' value="${read.bid}"> <input
			type='hidden' name='page' value="${pageMaker.page}"> <input
			type='hidden' name='perPageNum' value="${pageMaker.perPageNum}">
		<input type='hidden' name='searchType' value="${pageMaker.searchType}">
		<input type='hidden' name='keyword' value="${pageMaker.keyword}">
	</form>
	<h2>✔Q&A 게시판✔</h2>
	<table>
		<tr>
			<th width="15%">작성자</th>
			<td width="35%">${bwriter}</td>
			<th width="15%">글번호</th>
			<td width="35%">${read.bid}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${read.bhit}</td>
			<th>작성일</th>
			<td>${read.bdate}</td>
		</tr>
		<tr>
			<th>글제목</th>
			<td colspan="3">${read.btitle}</td>
		</tr>
		<tr>
			<th colspan="4">글내용</th>
		</tr>
		<tr>
			<td colspan="4" id="bcontent"
				style="height: 300px; vertical-align: top; text-align: left;">${read.bcontent}</td>
		</tr>
		<c:if test="${filename2 ne null}">
			<tr>
				<th>첨부파일</th>
				<td colspan="3"><a
					href="/controller/boardNotice/displayFile?fileName=${filename2}">${filename2}</a></td>
			</tr>
		</c:if>
	</table>

	<!-- /.box-body -->
	<div class="box-footer" style="text-align: center; margin: 10px;">
		<button type="submit" class="btnReply">답글</button>
		<c:if test="${userid eq read.bname}">
			<button type="submit" class="btnModify">수정</button>
			<button type="submit" class="btnRemove">삭제</button>
		</c:if>
		<c:if test="${userauth.contains('ADMIN')}">
			<button type="submit" class="btnRemove">삭제</button>
	</c:if>
		<button type="submit" class="btnList">목록</button>
	</div>
</div>

<%@include file="../include/footer.jsp"%>


