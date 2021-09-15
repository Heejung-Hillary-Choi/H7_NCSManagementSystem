<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/loginHeader.jsp"%>
<!--회원가입 폼-->
<div class="form">
	<form role="form" method="POST" action="/controller/login/loginJoinPro"
		onsubmit="return checked()">
		<div class="input-box">
			<label for="userid">아이디</label> <input type="text" name="userid"
				id="userid" maxlength="50" required>
			<div class="check_font" id="id_check"></div>
		</div>
		<div class="input-box">
			<input type="password" name="userpassword" maxlength="50"
				id="userpassword" required> <label for="password">비밀번호</label>
			<div class="check_font" id="password_check"></div>
		</div>
		<div class="input-box">
			<input type="password" name="passwordCheck" id="userpassword2"
				maxlength="50" required> <label for="passwordCheck">비밀번호
				확인</label>
			<div class="check_font" id="passwordcheck_check"></div>
		</div>
		<div class="input-box">
			<input type="text" name="name" id="name" placeholder="ex)홍길동"
				required> <label for="name">이름</label>
			<div class="check_font" id="name_check"></div>
		</div>
		<div class="input-box">
			<input type="text" name="phone" id="phone"
				placeholder="ex)01012345678" onfocus="this.placeholder=''"
				onblur="this.placeholder='ex)01012345678'" required> <label
				for="name">전화번호</label>
			<div class="check_font" id="phone_check"></div>
		</div>
		<div class="input-box">
			<input type="text" name="email" id="email"
				placeholder="ex)honggildong@naver.com" onfocus="this.placeholder=''"
				onblur="this.placeholder='ex)honggildong@naver.com'" required>
			<label for="email">본인확인이메일</label>
			<div class="check_font" id="email_check"></div>
		</div>
		<div class="input-box">
			<div class="mail_check_input_box">
				<input class="mail_check_input" id="mail_check_input_box_false"
					disabled="disabled" required>
			</div>
			<div class="mail_check_button">
				<input type="button" value="인증번호 전송" id="injungBtn">
				<div class="check_font" id="injung_check"></div>
			</div>
		</div>
		<div class="input-box">
			<label for="postcode">주소</label> <input type="text" id="postcode"
				name="postcode" placeholder="우편번호" class="halfbox" readonly required>
			<input type="button" onclick="DaumPostcode()" value="우편번호 찾기"
				id="smallBtn"><br> <input type="text" id="address"
				name="address" placeholder="주소" readonly> <input type="text"
				id="address_extra" name="address_extra" placeholder="참고항목" readonly>
			<input type="text" id="address_detail" name="address_detail"
				placeholder="상세주소" required>
			<div class="check_font" id="address_check"></div>
		</div>
		<div class="input-radio">
			<input type="radio" name="authority" value="ROLE_STUDENT" checked>학생
			<input type="radio" name="authority" value="ROLE_PROFESSOR">강사<input
				type="radio" name="authority" value="ROLE_INSTITUTION">교육기관
			<div class="check_font" id="authority_check"></div>
		</div>
		<input type="submit" value="회원가입" id="join-submit" class="mini-button">
		<button type="button" onclick="location.href='../login/loginForm'"
			class="mini-button">취소</button>
	</form>
</div>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
	//주소찾기 API
	function DaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var addr = ''; // 주소 변수
				var extraAddr = ''; // 참고항목 변수

				//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
					addr = data.roadAddress;
				} else { // 사용자가 지번 주소를 선택했을 경우(J)
					addr = data.jibunAddress;
				}

				// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
				if (data.userSelectedType === 'R') {
					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if (extraAddr !== '') {
						extraAddr = ' (' + extraAddr + ')';
					}
					// 조합된 참고항목을 해당 필드에 넣는다.
					document.getElementById("address_extra").value = extraAddr;

				} else {
					document.getElementById("address_extra").value = '';
				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('postcode').value = data.zonecode;
				document.getElementById("address").value = addr;
				// 커서를 상세주소 필드로 이동한다.
				document.getElementById("address_detail").focus();
			}
		}).open();
	}
	/// 아이디 유효성 검사(1 = 중복 / 0 != 중복)
	//모든 공백 체크 정규식
	var empJ = /\s/g;
	//아이디 정규식
	var idJ = /^[a-z0-9]{2,12}$/;
	// 비밀번호 정규식
	var pwJ = /^[A-Za-z0-9]{4,12}$/;
	// 이름 정규식
	var nameJ = /^[가-힣]{2,6}$/;
	// 이메일 검사 정규식

	var mailJ = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

	// 전화 번호 정규식
	var phoneJ = /^([0-9]{2,3})([0-9]{3,4})([0-9]{4})$/;

	//아이디 중복체크 
	$("#userid")
			.keyup(
					function() {
						var user_id = $('#userid').val();
						$
								.ajax({
									url : '${pageContext.request.contextPath}/login/idCheck?userid='
											+ user_id,
									type : 'get',
									success : function(data) {
										console.log("1 = 중복o / 0 = 중복x : "
												+ data);

										if (data == 1) {
											// 1 : 아이디가 중복되는 문구
											$("#id_check").text(
													"이미 사용중인 아이디입니다 :p");
											$("#id_check").css("color", "red");
											$("#join-submit").attr("disabled",
													true);
										} else {

											if (idJ.test(user_id)) {
												// 0 : 아이디 길이 / 문자열 검사
												$("#id_check").text(
														"아주 멋진 아이디네요 !");
												$('#id_check').css('color',
														'blue');
												$("#join-submit").attr(
														"disabled", false);

											} else if (user_id == "") {

												$('#id_check').text(
														'아이디를 입력해주세요 :)');
												$('#id_check').css('color',
														'red');
												$("#join-submit").attr(
														"disabled", true);

											} else {

												$('#id_check')
														.text(
																"아이디는 소문자와 숫자 2~12자리만 가능합니다 :)");
												$('#id_check').css('color',
														'red');
												$("#join-submit").attr(
														"disabled", true);
											}

										}
									},
									error : function() {
										console.log("실패");
									}
								});
					});

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
			$("#join-submit").attr("disabled", true);
		}
	});

	// 휴대전화
	$('#phone').keyup(function() {
		if (phoneJ.test($(this).val())) {
			console.log(nameJ.test($(this).val()));
			$("#phone_check").text('');
		} else {
			$('#phone_check').text('휴대폰번호를 확인해주세요 :) 숫자만 입력 가능합니다.');
			$('#phone_check').css('color', 'red');
			$("#join-submit").attr("disabled", true);
		}
	});
	// 주소
	$('#detailAddress').keyup(function() {
		if ($(this).val() !== "") {
			console.log($(this).val());
			$('#address_check').text('');
		} else {
			$('#address_check').text('상세주소를 확인해주세요 :)');
			$('#address_check').css('color', 'red');
			$("#join-submit").attr("disabled", true);

		}
	});
	// 비밀번호 
	$('#userpassword').keyup(function() {
		if (pwJ.test($(this).val())) {
			console.log(pwJ.test($(this).val()));
			$("#password_check").text('');
		} else {
			$('#password_check').text('비밀번호를 확인해주세요. 영어와 숫자 4~12자리만 가능합니다 :)');
			$('#password_check').css('color', 'red');
			$("#join-submit").attr("disabled", true);
		}
		var userpassword = $('#userpassword').val();
		var userpassword2 = $('#userpassword2').val();
		if (userpassword2 == userpassword) {
			$('#passwordcheck_check').text('');
		} else {
			$('#passwordcheck_check').text('비밀번호가 일치하지 않습니다 :)');
			$('#passwordcheck_check').css('color', 'red');
			$("#join-submit").attr("disabled", true);

		}
	});
	// 비밀번호 확인
	$('#userpassword2').keyup(function() {
		var userpassword = $('#userpassword').val();
		console.log('#userpassword2');
		console.log(userpassword);
		if ($(this).val() == userpassword) {
			console.log($(this).val());
			$('#passwordcheck_check').text('');
		} else {
			$('#passwordcheck_check').text('비밀번호가 일치하지 않습니다 :)');
			$('#passwordcheck_check').css('color', 'red');
			$("#join-submit").attr("disabled", true);

		}
	});
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
										console.log("1 = 중복o / 0 = 중복x : "
												+ data);
										if (data == 1) {
											// 1 : 이메일이 중복되는 문구
											$("#email_check").text(
													"이미 사용중인 이메일입니다.－O－");
											$("#email_check").css("color",
													"red");
											$("#join-submit").attr("disabled",
													true);
											$("#injungBtn").attr("disabled",
													true);
										} else {

											if (mailJ.test(email)) {
												// 0 : 이메일 길이 / 문자열 검사
												$("#email_check").text(
														"사용가능한 이메일입니다. :-D");
												$('#email_check').css('color',
														'blue');
												$("#injungBtn").attr(
														"disabled", false);
											} else if (email == "") {

												$('#email_check').text(
														'이메일을 입력해주세요 :)');
												$('#email_check').css('color',
														'red');
												$("#join-submit").attr(
														"disabled", true);
												$("#injungBtn").attr(
														"disabled", true);

											} else {
												$('#email_check')
														.text(
																"이메일의 형식에 맞지 않습니다. ^_____^");
												$('#email_check').css('color',
														'red');
												$("#join-submit").attr(
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
	//이메일 주소
	var code = ""; //이메일전송 인증번호 저장위한 코드

	/* 인증번호 이메일 전송 */
	$("#injungBtn").click(function() {

		var email = $("#email").val(); // 입력한 이메일
		var checkBox = $(".mail_check_input"); // 인증번호 입력란
		var injungBtn = $("#injungBtn"); //인증번호 버튼

		if (email == "") {
			swal("보내기 실패!", "메일 주소를 입력하세요.", "warning");
		} else {
			$.ajax({

				type : "GET",
				url : "mailCheck?email=" + email,
				success : function(data) {
					/* console.log("data : " + data); */
					checkBox.attr("disabled", false);
					checkBox.attr("id", "mail_check_input_box_true");
					injungBtn.attr("disabled", true);
					code = data;
					swal("전송 성공!", "인증번호가 전송되었습니다.", "success")
				}
			});
		}
	});

	/* 인증번호 비교 */
	$(".mail_check_input").keyup(function() {

		var inputCode = $(".mail_check_input").val(); // 입력코드    
		var checkResult = $("#injung_check"); // 비교 결과     
		if (inputCode == code) { // 일치할 경우
			checkResult.html("인증번호가 일치합니다.");
			checkResult.attr("class", "correct");
			$("#join-submit").attr("disabled", false);
		} else { // 일치하지 않을 경우
			checkResult.html("인증번호를 다시 확인해주세요.");
			checkResult.attr("class", "incorrect");
			$("#join-submit").attr("disabled", true);
		}
	});
	function checked() { //form 유효성 검사 
		var pw = $('#password').val();
		var pw2 = $('#passwordCheck').val();

		if (pwJ.test(pw) && pwJ.test(pw2)) {
			if (pw == pw2) {
				$('#passwordcheck_check').text('');
				return true;
			} else {
				$('#passwordcheck_check').text('비밀번호가 일치하지 않습니다 :)');
				$('#passwordcheck_check').css('color', 'red');
				return false;
			}
		} else {
			$('#password_check').text('비밀번호를 확인해주세요. 영어와 숫자 4~12자리만 가능합니다 :)');
			$('#password_check').css('color', 'red');
			return false;
		}
		return false;
	}
</script>
<%@ include file="../include/footer.jsp"%>