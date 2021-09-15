<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../include/loginHeader.jsp"%>
<div class="form">
	<c:url value="/login" var="loginUrl" />
	<form:form name="loginForm" action="${loginUrl}" method="POST">
		<c:if test="${param.error != null}">
		<script type="text/javascript">
			swal({
			title: "로그인 실패!",
			text: "아이디 혹은 비밀번호가 잘못되었습니다..",
			icon: "warning",
			button: "다시 시도"
			}).then(function() {
			window.location.href = "../login/loginForm";
			});			
			</script>
		</c:if>
		<c:if test="${param.logout != null}">
			<script type="text/javascript">
				alert("로그아웃 하였습니다.");
			</script>
		</c:if>

		<div class="input-box">
			<input type="text" name="userid" id="userid" required> <label
				for="userid">아이디</label>
		</div>
		<div class="input-box">
			<input type="password" name="password" id="password" required> <label
				for="password">비밀번호</label>
		</div>
		<div class="links">
			<a href="loginIdFind">아이디 찾기</a> | <a href="loginPwFind">비밀번호 찾기</a>
			| <a href="loginJoin">회원가입</a>
		</div>
		<!-- <input name="_csrf" type="hidden" value="93d4bd2b-29ea-40dd-9d9d-d0ae2b84f743" /> -->
		<input type="submit" value="로그인">
	</form:form>
</div>
</section>
<%@ include file="../include/footer.jsp"%>
