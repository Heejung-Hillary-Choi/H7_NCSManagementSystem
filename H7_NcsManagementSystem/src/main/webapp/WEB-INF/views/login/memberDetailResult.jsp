<%@ page language="java" isELIgnored="false"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/loginHeader.jsp"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%
	response.setContentType("text/html");
%>
<!--회원정보 수정 폼-->
<h3>회원 정보 수정이 완료되었습니다 []~(￣▽￣)~*</h3>

<c:forEach items="${memberDetailDtos}" var="memberDetailDtos">
	<div class="form">
		<div class="input-box">

			<label for="userid">아이디</label> <input type="text" name="userid"
				id="userid" value="${memberDetailDtos.userid}" readonly>
			<div class="red">*아이디는 변경불가 ＞︿＜</div>
		</div>
		<div class="input-box">
			<input type="text" name="name" id="name" maxlength="100"
				value="${memberDetailDtos.name}" readonly> <label for="name">이름</label>
		</div>
		<div class="input-box">
			<input type="text" name="email" id="email" maxlength="50"
				value="${memberDetailDtos.email}" readonly> <label
				for="email">이메일</label>
		</div>
		<div class="input-box">
			<input type="text" name="phone" id="phone" maxlength="50"
				value="${memberDetailDtos.phone}" readonly> <label
				for="phone">전화번호</label>
		</div>
		<div class="input-box">
			<label for="postcode">주소</label> <input type="text" id="postcode"
				name="postcode" value="${memberDetailDtos.postcode}" readonly>
			<br> <input type="text" id="address" name="address"
				value="${memberDetailDtos.address}" readonly> <input
				type="text" id="address_extra" name="address_extra"
				value="${memberDetailDtos.address_extra}" readonly> <input
				type="text" id="address_detail" name="address_detail"
				value="${memberDetailDtos.address_detail}" readonly>
		</div>
		<button type="button" onclick="location.href='../mainPage'"
			class="mini-button">메인으로 가기</button>
	</div>
</c:forEach>

<%@ include file="../include/footer.jsp"%>