<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"> 
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>휴먼3조 NCS교육관리시스템</title>
<link rel="stylesheet" type="text/css" href="../resources/css/main.css">
<link rel="stylesheet" type="text/css" href="../resources/css/table.css">
<link rel="stylesheet"
	href="https://unpkg.com/swiper/swiper-bundle.min.css">
<!--PDF로 저장하는 cdn  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>
<script src="/controller/resources/js/pdf.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
function hoverInMyPage() {
	document.getElementById('goMyPage').innerHTML = "회원정보 수정";
}
function hoverOutMyPage() {
	document.getElementById('goMyPage').innerHTML = "${userid} 님";
}
</script>
</head>
<body>
<div class="wrap">
		<!-- Header -->
		<header>
			<div id="logo">
				<a id="logobtn" href="../mainPage">NCS교육관리시스템</a>
			</div>
			<div id="login">
		<sec:authorize access="isAnonymous()">
			<a id="loginbtn" href="<c:url value="/login/loginForm" />">로그인</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<c:choose>
				<c:when test="${userauth.contains('ADMIN')}">
					<a href="${pageContext.request.contextPath}/admin/adminHome" id="goMyPage">관리자 페이지</a>
			</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/login/myPage" id="goMyPage" onmouseover="hoverInMyPage()" onmouseout="hoverOutMyPage()">${userid} 님</a>
				</c:otherwise>
			</c:choose>
			<form:form action="${pageContext.request.contextPath}/logout" id='gologout' method="POST" />
			<a id="loginbtn" href="#" onclick="document.getElementById('gologout').submit();">로그아웃</a>
		</sec:authorize>
	</div>
		</header>
		<!-- Menu -->
		<div id="menu">
			<ul class="main">
				<li id="sub1">교육관리
					<ul class="sub">
						<c:choose>
							<c:when test="${userauth.contains('ADMIN')}">
								<li><a href="../admin/adminHome">😁관리자 홈으로 이동👉🏻</a></li>
							</c:when>						
							<c:when test="${userauth.contains('STUDENT')}">
								<li><a href="../student/stListCourse">나의 강의목록</a></li>
								<li><a href="../student/stTestSelect">진단평가 수행</a></li>
								<li><a href="../student/stTestResultList">평가내역 조회</a></li>
							</c:when>
							<c:when test="${userauth.contains('PROFESSOR')}">
								<li><a href="../professor/prCourseList">나의 강의목록</a></li>
								<li><a href="../professor/prtestSelect">평가내역 조회</a></li>
							</c:when>
							<c:when test="${userauth.contains('INSTITUTION')}">
								<li><a href="../institution/insertCourseForm">신규 강의 개설</a></li>
								<li><a href="../institution/insertStincoForm">수강생 등록</a></li>
								<li><a href="../institution/inTestSelect">평가내역 조회</a></li>
							</c:when>						
						</c:choose>
				</ul></li>
			<li id="sub1">NCS안내</a>
				<ul class="sub">
					<li><a href="../whatisNCS">NCS란?</a></li>
					<li><a href="https://ncs.go.kr/unity/th03/ncsSearchMain.do">NCS 및 학습모듈검색</a></li>
				</ul></li>
			<li id="sub1">커뮤니티</a>
				<ul class="sub">
					<li><a href="../boardNotice/list">공지사항</a></li>
					<li><a href="../boardQna/list">Q&A</a></li>
				</ul></li>
		</ul>
	</div>
	<!-- header.jsp end -->