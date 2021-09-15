<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/loginHeader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--아이디 찾기 폼-->
<div class="form">
	<form role="form" action="/controller/login/findId" method="POST"
		onsubmit="return checked()">
		<div class="input-box">
			<input type="text" name="name" id="name" placeholder="ex)홍길동"
				required> <label for="name">이름</label>
			<div class="check_font" id="name_check"></div>
		</div>
		<div class="input-box">
			<input type="text" name="email" id="email"
				placeholder="ex)qls5170@naver.com" required><label
				for="email">이메일</label>
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
		</div>
		<p class="info">회원가입시 입력한 정보를 입력 해주세요 :)</p>
		<input type="submit" value="아이디 찾기" id="submitBtn" class="mini-button">
		<button type="button" onclick="location.href='../login/loginForm'"
			class="mini-button">취소</button>
	</form>
</div>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script>
	// 이름 정규식
	var nameJ = /^[가-힣]{2,6}$/;
	// 이름에 특수문자 들어가지 않도록 설정
	$("#name").keyup(function() {
		var name = $('#name').val();
		nameResult = nameJ.test(name);
		console.log(nameResult);
		if (nameResult) {
			console.log(nameResult);
			$("#name_check").text('');
		} else {
			$('#name_check').text("이름을 확인해주세요");
			$('#name_check').css('color', 'red');
			$("#submitBtn").attr("disabled", true);
		}
	});
	// 이메일 검사 정규식
	var mailJ = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	//이메일 중복확인
	$("#email")
			.keyup(
					function() {
						var email = $('#email').val();
						$
								.ajax({
									url : '${pageContext.request.contextPath}/login/emailCheck?email='
											+ email,
									type : 'get',
									success : function(data) {
										console
												.log("이메일 중복확인 ajax 1 = 중복o / 0 = 중복x : "
														+ data);
										if (data == 1) {
											// 1 : 이메일이 중복되는 문구
											$("#email_check").text("");
											$("#submitBtn").attr("disabled",
													false);
											$("#injungBtn").attr("disabled",
													false);
										} else {

											if (mailJ.test(email)) {
												// 0 : 이메일 길이 / 문자열 검사
												$("#email_check").text(
														"가입하지 않은 이메일입니다.");
												$('#email_check').css('color',
														'red');
												$("#submitBtn").attr(
														"disabled", true);
												$("#injungBtn").attr(
														"disabled", true);
											} else if (email == "") {

												$('#email_check').text(
														'이메일을 입력해주세요 :)');
												$('#email_check').css('color',
														'red');
												$("#submitBtn").attr(
														"disabled", true);
												$("#injungBtn").attr(
														"disabled", true);

											} else {
												$('#email_check')
														.text(
																"이메일의 형식에 맞지 않습니다. ^_____^");
												$('#email_check').css('color',
														'red');
												$("#submitBtn").attr(
														"disabled", true);
												$("#injungBtn").attr(
														"disabled", true);
											}

										}
									},
									error : function() {
										console.log("실패");
									}
								});
					});
	/* 인증번호 이메일 전송 */
	$("#injungBtn")
			.click(
					function() {
						var email = $("#email").val(); // 입력한 이메일
						var checkBox = $(".mail_check_input"); // 인증번호 입력란
						var injungBtn = $("#injungBtn"); //인증번호 버튼
						var name = $('#name').val();
						console.log("name2" + name);
						$
								.ajax({
									type : "GET",
									contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
									url : "${pageContext.request.contextPath}/login/nameEmailFind?name="
											+ name + "&email=" + email,

									success : function(data1) {
										if (data1 == 1) {
											$
													.ajax({
														type : "GET",
														contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
														url : "${pageContext.request.contextPath}/login/mailCheck?email="
																+ email,

														success : function(
																data2) {
															/* console.log("data : " + data); */
															checkBox.attr(
																	"disabled",
																	false);
															checkBox
																	.attr("id",
																			"mail_check_input_box_true");
															injungBtn.attr(
																	"disabled",
																	true);
															$('#email_check')
																	.text('');
															code = data2;
															swal(
																	"전송 성공!",
																	"인증번호가 전송되었습니다.",
																	"success")
														}
													});
										} else {
											console.log("data1 :" + data1);
											$('#email_check').text(
													'이름과 이메일이 일치하지 않습니다.');
											$('#email_check').css('color',
													'red');
										}
									}
								});

					});
	//이메일 주소
	var code = ""; //이메일전송 인증번호 저장위한 코드
	/* 인증번호 비교 */
	$(".mail_check_input").keyup(function() {

		var inputCode = $(".mail_check_input").val(); // 입력코드    
		var checkResult = $("#injung_check"); // 비교 결과     
		if (inputCode == code) { // 일치할 경우
			checkResult.html("인증번호가 일치합니다.");
			checkResult.attr("class", "coㅋrrect");
			$("#submitBtn").attr("disabled", false);
		} else { // 일치하지 않을 경우
			checkResult.html("인증번호를 다시 확인해주세요.");
			checkResult.attr("class", "incorrect");
			$("#submitBtn").attr("disabled", true);
		}
	});
	function checked() {
		var email = $('#email').val();
		var name = $('#name').val();
		if (nameJ.test(name)) {
			$("#name_check").text('');
			return true;
		} else {
			$('#name_check').text("이름을 확인해주세요");
			$('#name_check').css('color', 'red');
			$("#submitBtn").attr("disabled", true);
			return false;
		}
		return false;
	}
</script>
<%@ include file="../include/footer.jsp"%>
