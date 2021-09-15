<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/loginHeader.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--회원가입 성공-->
<% 
        // 한글 깨짐을 방지하기 위한 인코딩 처리
        request.setCharacterEncoding("euc-kr"); 
        String name = request.getParameter("name");
    %>
<div class="form">
	<div class="sub_title">
		<h2>회원가입 성공</h2>
		<p><b><%=name%></b>님 가입을 축하합니다 φ(゜▽゜*)♪φ(゜▽゜*)♪ㅇ </p>
		<p> 관리자 확인 후 가입이 승인 혹은 거부될 수 있습니다. </p>
			 
	</div>
	<button type="button" onclick="location.href='loginForm'">로그인하러
		가기</button>
</div>
<%@ include file="../include/footer.jsp"%>
