<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../include/header.jsp"%>
<head>
<link href="${pageContext.request.contextPath}/resources/css/table.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet" type="text/css">
</head>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
	var result = '${msg}';

	if (result == 'success') {
		swal({
			title : "OK!",
			text : "처리가 완료되었습니다..",
			icon : "info"
		});
	}
	$(document).ready(
	         function() {
	            $('#searchBtn').on(
	                  "click",
	                  function(event) {
	                     var count = $('select option:selected').val(); //<-- 셀렉티드 하위의 옵션에서 선택된 값을 가져오는데
	                     if (count == "n") //<-- 첫번쨰 벨류에다가 0을 넣었으니까 0으로 검사해서 0이면 안선택한거
	                     {
	                        swal({
	                           title : "Oops!",
	                           text : "검색할 항목을 선택해주세요..",
	                           icon : "warning"
	                        });
	                     } else {
	                        self.location = "list"
	                              + '${pageMaker.makePage(1)}'
	                              + '&searchType='
	                              + $("select option:selected").val()
	                              + "&keyword="
	                              + $('#keywordInput').val();

	                     }
	                     event.preventDefault();
	                  })

	            $('.writeBtn').on("click", function(event) {
	               location.href = "/controller/boardNotice/write";
	            });
	            $('#newBtn').on("click", function(event) {
	               self.location = "write";
	            });

	         });
</script>
<section>
	<h2>✔공지사항 게시판✔</h2>
	<div style="width: 50%; float: right;">
		<select name="searchType"
			style="margin: auto; display: inline-block; width: 15%; position: relative;">
			<option value="n"
				<c:out value="${pageMaker.searchType==null?'selected':'' }"/>>----</option>
			<!-- <option value="n" selected>----</option>
    		<option value="n">----</option> -->
			<option value="t"
				<c:out value="${pageMaker.searchType eq 't'?'selected':'' }"/>>제목</option>
			<option value="c"
				<c:out value="${pageMaker.searchType eq 'c'?'selected':'' }"/>>내용</option>
			<option value="w"
				<c:out value="${pageMaker.searchType eq 'w'?'selected':'' }"/>>글쓴이</option>
			<option value="tc"
				<c:out value="${pageMaker.searchType eq 'tc'?'selected':'' }"/>>제목,내용</option>

		</select> <input type="text" name="keyword" id="keywordInput"
			style="margin: auto; display: inline-block; height: 30px;"
			value="${pageMaker.keyword}">
		<button id="searchBtn"
			style="margin: auto; display: inline-block; margin-left: 5px;">검색</button>
	</div>

	<table class='customers' style="margin-top: 80px; width: 80%;">
		<tr>
			<th style="width: 70px;">글번호</th>
			<th>글제목</th>
			<th style="width: 200px">작성자</th>
			<th style="width: 200px">작성 날짜</th>
			<th style="width: 70px">조회수</th>
		</tr>
		<c:forEach items="${blist}" var="dto">
			<tr>
				<td style="width: 10px">${dto.bid }</td>
				<td><a
					href="/controller/boardNotice/read${pageMaker.makeSearch()}&bid=${dto.bid}">${dto.btitle }</a></td>
				<td style="width: 100px">${dto.bwriter }</td>
				<td style="width: 200px"><fmt:formatDate pattern="yyyy-MM-dd"
						value="${dto.bdate }" /></td>
				<td style="width: 40px">${dto.bhit }</td>
			</tr>
		</c:forEach>
	</table>

	<div class="pagination">
		<c:if test="${pageMaker.page !=1}">
			<a href='list${pageMaker.makeSearch(1)}'>&laquo;</a>
		</c:if>
		<c:if test="${pageMaker.prev }">
			<a href='list${pageMaker.makeSearch(pageMaker.startPage-1)}'>&lt;</a>
		</c:if>

		<c:forEach begin="${pageMaker.startPage }" end="${ pageMaker.endPage}"
			var="idx">
			<a href='list${pageMaker.makeSearch(idx)}'
				<c:out value="${pageMaker.page==idx?' class=active ':'' }"/>>
				${idx}</a>
		</c:forEach>

		<%--<a href='#'>1</a>
    	 <a href='list${pageMaker.makeSearch(2)}'>2</a>
    	<a href='#' class="active">3</a> --%>
		<c:if test="${pageMaker.next }">
			<a href='list${pageMaker.makeSearch(pageMaker.endPage+1)}'>&gt;</a>
		</c:if>

		<c:if test="${pageMaker.page != pageMaker.totalEndPage}">
			<a href='list${pageMaker.makeSearch(pageMaker.totalEndPage)}'>&raquo;</a>
		</c:if>
	</div>

	<c:if
		test="${userauth.contains('ADMIN')||userauth.contains('INSTITUTION')}">
		<button class='writeBtn'
			style="position: absolute; right: 10%; margin: 10px;">글쓰기</button>
	</c:if>

</section>
<%@include file="../include/footer.jsp"%>
