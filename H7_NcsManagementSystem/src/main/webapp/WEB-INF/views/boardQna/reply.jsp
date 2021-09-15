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
			fObject.attr("action", "/controller/boardQna/list");
			fObject.submit();

		})
		$(".btnReply").on("click", function() {
			var btitle = $("#btitle").val();
			var bcontent = $("textarea#bcontent2").val();
			console.log(btitle, bcontent);
			if (btitle != "" && bcontent != "") {
				fObject.attr("method", "post");
				fObject.attr("action", "/controller/boardQna/reply");
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

	<form class="form" method="post">
		<input type='hidden' name="originbid" value="${originbid}">
		<h2>✔Q&A게시판✔</h2>
		<table>
			<tr>
				<th>이름</th>
				<td>${bwriter}<input id="bname" type="hidden" name="bname" value="${userid }"></td>
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
		</table>
	</form>
	<!-- /.box-body -->
	<div style="margin-top: 20px; margin-left: 45%;">
		<button type="submit" class="btnReply">답글작성</button>

		<button type="submit" class="btnCancel">취소</button>
	</div>



</div>

<%@include file="../include/footer.jsp"%>


