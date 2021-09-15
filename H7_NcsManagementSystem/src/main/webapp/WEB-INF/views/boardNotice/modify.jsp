<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@include file="../include/header.jsp"%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script>
	$(document).ready(function() {
		var fObject = $(".form");

		$(".btnCancel").on("click", function() {

			fObject.attr("method", "get");
			fObject.attr("action", "/controller/boardNotice/list");
			fObject.submit();

		})
		$(".btnSave").on("click", function() {
			var btitle = $("#btitle").val();
			var bcontent = $("#bcontent2").val();
			console.log(btitle, bcontent);
			if (btitle != "" && bcontent != "") {
				fObject.attr("method", "post");
				fObject.attr("action", "/controller/boardNotice/modify");
				fObject.submit();
			} else {
				swal({
					title : "경고!",
					text : "내용을 입력해주세요..",
					icon : "error"
				});
				return false;
			}
		})

	});
</script>

<div class="main">

	<form class="form" method="post" enctype="multipart/form-data">

		<input type='hidden' name='bid' value="${modify.bid}"> 
		<input type='hidden' name='bname' value="${modify.bname}"> 
		<input type='hidden' name='page' value="${pageMaker.page}"> 
		<input type='hidden' name='perPageNum' value="${pageMaker.perPageNum}">
		<input type='hidden' name='searchType' value="${pageMaker.searchType}">
		<input type='hidden' name='keyword' value="${pageMaker.keyword}">

		<h2>✔공지사항 게시판✔</h2>
		<table>
			<tr>
				<th width="15%">작성자</th>
				<td width="35%">${bwriter }</td>
				<th width="15%">글번호</th>
				<td width="35%">${modify.bid }</td>
			</tr>
			<tr>
				<th>글 제목</th>
				<td><input type="text" id="btitle" name="btitle" maxlength="100"
					value="${modify.btitle }" /></td>
			</tr>
			<tr>
				<th colspan="4">글 내용</th>
			</tr>
			<tr>
				<td colspan="4"><textarea id="bcontent2" maxlength="1000"
						style="text-align: left;" name="bcontent">
			${modify.bcontent }
		</textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3"><input type="file" name="file"></td>
			</tr>
		</table>
		<!-- /.box-body -->
		<div class="box-footer" style="margin-top: 20px;">
			<button type="submit" class="btnSave">수정</button>
			<button type="submit" class="btnCancel">취소</button>
		</div>
	</form>


</div>

<%@include file="../include/footer.jsp"%>


