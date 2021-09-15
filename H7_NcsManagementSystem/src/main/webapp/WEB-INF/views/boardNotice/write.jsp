<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<%@include file="../include/header.jsp"%>
<div class="main">
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
		$(document).ready(function() {
			var fObject = $(".form");

			$(".btnCancel").on("click", function() {

				fObject.attr("method", "get");
				fObject.attr("action", "/controller/boardNotice/list");
				fObject.submit();

			})
			$(".btn").on("click", function() {
				var btitle = $("#btitle").val();
				var bcontent = $("textarea#bcontent2").val();
				console.log(btitle,bcontent);
				if (btitle != "" && bcontent != "") {
					fObject.attr("method", "post");
					fObject.attr("action", "/controller/boardNotice/write");
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
	<!-- /.box-header -->
	<!-- action에 경로가 없으면 현재 페이지 주소로 이동한다.-->
	<form class="form" method="post" action="/controller/boardNotice/write"
		enctype="multipart/form-data">
		<h2>✔공지사항 게시판✔</h2>
		<table id="content">
			<tr>
				<th>작성자</th>
				<td>${bwriter}<input id="bname" type="hidden" name="bname"
					value="${userid}"></td>

			</tr>
			<tr>
				<th>제목</th>
				<td><input id="btitle" type="text" name="btitle"
					maxlength="100"></td>
			</tr>
			<tr>
				<th colspan=2>내용</th>
			</tr>
			<tr>
				<td colspan=2 id="bcontent"
					style="height: 300px; vertical-align: top; text-align: left;"><textarea
						id="bcontent2" maxlength="1000" rows=10 name="bcontent"></textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3"><input type="file" name="file"></td>
			</tr>
		</table>

		<button type="submit" class="btn" style="margin-top: 20px;">새글작성</button>
		<button type="submit" class="btnCancel" style="margin-top: 20px;">취소</button>
	</form>
</div>
<%@include file="../include/footer.jsp"%>