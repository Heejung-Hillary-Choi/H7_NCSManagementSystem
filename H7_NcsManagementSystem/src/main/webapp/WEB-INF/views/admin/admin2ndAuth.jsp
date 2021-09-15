<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<%@ include file="../include/loginHeader.jsp"%>
<div>
	<script>
		swal({
			title: "R U admin?",
			text: "관리자 페이지 진입을 위해서는 2차 인증이 필요합니다..",
			icon: "warning"});
	</script>
</div>
<br>
<h1>인증번호를 받을 관리자 이메일을 입력하세요.</h1>
<!-- 2차 인증을 위한 이메일 인증번호 발송 페이지.
확인 후 일치하면 boolean is2ndAuthorized = true;
admin페이지에 들어갈 때마다 is2ndAuthorized인지 확인할 것.-->
<div class="form">
<form action="adminHome" method="post">
<div class="input-box">
	<input type="text" name="email" id="email" style="margin-bottom: 5px;" required>
	<label for="email">Email</label>
	<div class="check_font" id="email_check"></div>
</div>

<div class="input-box">
	<div class="mail_check_input_box">
		<input class="mail_check_input" id="mail_check_input_box_false"
			disabled="disabled" required>
	</div>
	<div class="mail_check_button">
		<input type="button" value="인증번호 전송" id="injungBtn" disabled>
		<div class="check_font" id="injung_check"></div>
	</div>
	<div class="isAuthorized">
		<input type="hidden" readonly="readonly" name="is2ndAtuthrized" id="is2ndAtuthrized" value="">	
	</div>
</div>

<input type="submit" class="mini-button" id="join-submit" value="관리자 홈" disabled="disabled">
<button type="button" class="mini-button" onclick="location.href='${pageContext.request.contextPath}/logout'">취소</button>
</form>
</div>

<script>
var mailJ =/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

//이메일 중복확인
$("#email").keyup(function() {
	var email = $('#email').val();
	$.ajax({
		url : '${pageContext.request.contextPath}/admin/emailCheck?email='+ email,
		type : 'get',
		success : function(data) {
			console.log("1 = admin이 맞음 / 0 = 등록된 메일주소와 다름 : " + data);
			if (mailJ.test(email)) {
				if (data != 0) {
					$("#email_check").text(
						"어머나, 우리 웹사이트 관리자가 맞나봐요! :-D");
					$("#email_check").css("color","blue");
					$("#injungBtn").attr("disabled",false);
					$("#injungBtn").css("background-color","#798699");
				} else {
						$("#email_check").text(
							"엥, 사용자님은 우리 관리자가 아닌 것같은데요? －O－");
						$('#email_check').css('color','red');
						$("#injungBtn").attr("disabled", true);
						$("#injungBtn").css("background-color","#ccc");				

				}
			} else if (email == "") {
				$('#email_check').text(
					'이메일을 입력해주세요 :)');
				$('#email_check').css('color','gray');
				$("#join-submit").attr("disabled", true);
				$("#injungBtn").attr("disabled", true);
				$("#injungBtn").css("background-color","#ccc");				

			} else {
				$('#email_check').text(
					"이메일의 형식에 맞지 않습니다. (﻿︶﻿^︶)");
				$('#email_check').css('color','gray');
				$("#join-submit").attr("disabled", true);
				$("#injungBtn").attr("disabled", true);
				$("#injungBtn").css("background-color","#ccc");				

			}
		},
		error : function() {
			console.log("실패");
		}
	});
});

//이메일 주소
var code = ""; //이메일전송 인증번호 저장위한 코드

/* 인증번호 이메일 전송 */
$("#injungBtn").click(function() {

	var email = $("#email").val(); // 입력한 이메일
	var checkBox = $(".mail_check_input"); // 인증번호 입력란
	var injungBtn = $("#injungBtn"); //인증번호 버튼
	$.ajax({

		type : "GET",
		url : "mailCheck?email=" + email,
		success : function(data) {
			/* console.log("data : " + data); */
			checkBox.attr("disabled", false);
			checkBox.attr("id", "mail_check_input_box_true");
			injungBtn.attr("disabled", true);
			injungBtn.css("background-color","#ccc");	
			code = data;
			swal({
				title: "Email Sent!",
				text: "인증번호가 전송되었습니다..",
				icon: "info"
				});
		}
	});
});

/* 인증번호 비교 */
$(".mail_check_input").keyup(function() {

	var inputCode = $(".mail_check_input").val(); // 입력코드    
	var checkResult = $("#injung_check"); // 비교 결과     
	if (inputCode == code) { // 일치할 경우
		checkResult.html("인증번호가 일치합니다.");
		checkResult.attr("class", "correct");
		$("#join-submit").attr(
				"disabled", false);
		$("#is2ndAtuthrized").attr("value", code);
	} else { // 일치하지 않을 경우
		checkResult.html("인증번호를 다시 확인해주세요.");
		checkResult.attr("class", "incorrect");
		$("#join-submit").attr("disabled", true);
	}
});
</script>
<%@ include file="../include/footer.jsp"%>