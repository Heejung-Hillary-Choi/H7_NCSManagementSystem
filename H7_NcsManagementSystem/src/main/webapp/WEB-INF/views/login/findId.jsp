<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/loginHeader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--아이디 찾기 폼-->
<div class="form">
	<div class="sub_title">
		<h2>아이디 찾기 검색결과</h2>
		<p>
			찾으시는 아이디는 <b>" ${userid} "</b> 입니다.
		</p>
	</div>
	<button type="button" class="mini-button" onclick="location.href='loginForm'">로그인 하러가기</button>
	<button type="button" class="mini-button" onclick="location.href='../mainPage'">돌아가기</button>
</div>
<%@ include file="../include/footer.jsp"%>
