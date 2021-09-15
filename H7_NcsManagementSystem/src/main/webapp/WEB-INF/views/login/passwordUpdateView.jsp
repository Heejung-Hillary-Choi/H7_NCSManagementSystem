<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/loginHeader.jsp"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%
	response.setContentType("text/html");
%>
<!--비밀번호 수정 폼-->

<div class="form">
	<form role="form" method="GET"
		action="/controller/login/passwordUpdate" onsubmit="return checked()">
		<div class="input-box">
			<sec:authorize access="isAuthenticated()">
				<label for="userid">아이디</label>
				<input type="text" name="userid" id="userid" value="${userid}"
					readonly>
				<div class="red">*아이디는 변경불가 ＞︿＜</div>
			</sec:authorize>
		</div>
		<div class="input-box">
			<input type="password" name="prePassword" id="prePassword"
				maxlength="50" required> <label for="prePassword">기존
				비밀번호</label>
			<div class="check_font" id="pre-password_check"></div>
		</div>
		<div class="input-box">
			<input type="password" name="password" id="password" maxlength="50"
				required> <label for="password">새 비밀번호</label>
			<div class="check_font" id="password_check"></div>
		</div>
		<div class="input-box">
			<input type="password" name="passwordCheck" id="passwordCheck"
				maxlength="50" required> <label for="passwordCheck">새
				비밀번호 확인</label>
			<div class="check_font" id="passwordcheck_check"></div>
		</div>
		<input type="submit" id="modify-submit" value="회원정보수정"
			class="mini-button">
		<button type="button" onclick="location.href='../mainPage'"
			class="mini-button">취소</button>
	</form>
</div>
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
	//비밀번호 기존 
	$("#prePassword")
			.keyup(
					function() {
						var password = $('#prePassword').val();
						var userid = $('#userid').val();
						$
								.ajax({
									url : '${pageContext.request.contextPath}/login/passwordCheck?password='
											+ password + '&userid=' + userid,
									type : 'get',
									success : function(result) {
										console.log("1 = 중복o / 0 = 중복x : "
												+ result);

										if (result == 1) {
											$("#pre-password_check").text("");
											$("#modify-submit").attr(
													"disabled", false);

										} else {
											$('#pre-password_check').text(
													'기존 비밀번호를 입력해주세요 :)');
											$('#pre-password_check').css(
													'color', 'red');
											$("#modify-submit").attr(
													"disabled", true);

										}
									},
									error : function() {
										console.log("실패");
									}
								});
					});

	//비밀번호 정규식
	var pwJ = /^[A-Za-z0-9]{4,12}$/;
	// 비밀번호 
	$('#password').keyup(function() {
		var prepassword = $('#prePassword').val();
		var userpassword = $('#password').val();
		var userpassword2 = $('#passwordCheck').val();
		console.log(pwJ.test(userpassword));
		if (pwJ.test(userpassword)) {
			if (userpassword == prepassword) {
				$('#password_check').text('기존 비밀번호와 동일합니다 :)');
				$('#password_check').css('color', 'red');
			} else {
				$('#password_check').text('');
			}

		} else {
			$('#password_check').text('비밀번호를 확인해주세요. 영어와 숫자 4~12자리만 가능합니다 :)');
			$('#password_check').css('color', 'red');

		}
	});
	// 비밀번호 확인
	$('#passwordCheck').keyup(function() {
		var userpassword = $('#password').val();
		var userpassword2 = $('#passwordCheck').val();
		if (userpassword == userpassword2) {
			$('#passwordcheck_check').text('');
		} else {
			$('#passwordcheck_check').text('비밀번호가 일치하지 않습니다 :)');
			$('#passwordcheck_check').css('color', 'red');
		}
	});
	function checked() { //form 유효성 검사 
		var pw = $('#password').val();
		var pw2 = $('#passwordCheck').val();
		var pw3 = $('#prePassword').val();
		/* var pw4 = $('#prePassword').val(); */

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