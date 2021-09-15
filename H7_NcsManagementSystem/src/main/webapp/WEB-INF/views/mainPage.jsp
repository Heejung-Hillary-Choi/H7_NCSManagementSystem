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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/swiper.css">
<link rel="stylesheet"
	href="https://unpkg.com/swiper@6.8.1/swiper-bundle.min.css">
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
<div>
	<c:if test="${userauth.contains('new')||userauth.contains('NEW')}">
		<script type="text/javascript">
			swal({
			title: "Hello!",
			text: "현재 가입대기 상태입니다. \n 관리자 확인 후 가입이 승인 혹은 거부될 수 있습니다..",
			icon: "info",
			button: "확인"
			})			
			</script>
		</c:if>
</div>
	<div class="wrap">
		<!-- Header -->
		<header>
			<div id="logo">
				<a id="logobtn" href="mainPage">NCS교육관리시스템</a>
			</div>
			<div id="login">
		<sec:authorize access="isAnonymous()">
			<a id="loginbtn" href="<c:url value="/login/loginForm" />">로그인</a>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<c:choose>
				<c:when test="${userauth.contains('ADMIN')}">
					<a href="admin/adminHome" id="goMyPage">관리자 페이지</a>
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
								<li><a href="admin/adminHome">😁관리자 홈으로 이동👉🏻</a></li>
							</c:when>						
							<c:when test="${userauth.contains('STUDENT')}">
								<li><a href="student/stListCourse">나의 강의목록</a></li>
								<li><a href="student/stTestSelect">진단평가 수행</a></li>
								<li><a href="student/stTestResultList">평가내역 조회</a></li>
							</c:when>
							<c:when test="${userauth.contains('PROFESSOR')}">
								<li><a href="professor/prCourseList">나의 강의목록</a></li>
								<li><a href="professor/prtestSelect">평가내역 조회</a></li>
							</c:when>
							<c:when test="${userauth.contains('INSTITUTION')}">
								<li><a href="institution/insertCourseForm">신규 강의 개설</a></li>
								<li><a href="institution/insertStincoForm">수강생 등록</a></li>
								<li><a href="institution/inTestSelect">평가내역 조회</a></li>
							</c:when>
						</c:choose>
					</ul>
				</li>
				<li id="sub1">NCS안내
					<ul class="sub">
						<li><a href="whatisNCS">NCS란?</a></li>
						<li><a href="https://ncs.go.kr/unity/th03/ncsSearchMain.do">NCS
								및 학습모듈검색</a></li>
					</ul>
				</li>
				<li id="sub1">커뮤니티
					<ul class="sub">
						<li><a href="boardNotice/list">공지사항</a></li>
						<li><a href="boardQna/list">Q&A</a></li>
					</ul>
				</li>
			</ul>
		</div>
		<!-- Swiper -->
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<a href="https://www.work.go.kr/kua/index.do" class="swiper-slide"><img
					class="mySlides" src="resources/img/취업.png"></a> <a
					href="http://www.moel.go.kr/index.do" class="swiper-slide"><img
					class="mySlides" src="resources/img/내일.png"></a> <a
					href="http://www.human.or.kr/" class="swiper-slide"><img
					class="mySlides" src="resources/img/휴먼.png"></a>
			</div>
			<!-- Add Pagination -->
			<div class="swiper-pagination"></div>
			<!-- Add Arrows -->
			<button type="button" class="swiper-button-next"></button>
			<button type="button" class="swiper-button-prev"></button>
		</div>
		<!-- 게시판 -->
		<section>
			<article>
				<div>
					<h3>✔공지사항 게시판✔</h3>
					<a href="boardNotice/list" class="plus">더보기></a> <br>
					<hr>
					<table class="board">
						<tr>
							<th class="overflowtitle">제목</th>
							<th class="overflowname">이름</th>
							<th class="date">날짜</th>
							<th class="count">조회수</th>
						</tr>
						<c:forEach items="${boardNotiDtos}" var="boardNotiDtos">
							<tr>
								<td class="overflowtitle" id="title">${boardNotiDtos.btitle}</td>
								<td class="overflowname">${boardNotiDtos.bname}</td>
								<td class="date">${boardNotiDtos.bdate}</td>
								<td class="count">${boardNotiDtos.bhit}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</article>
			<article>
				<div>
					<h3>✔Q&A 게시판✔</h3>
					<a href="boardQna/list" class="plus">더보기></a> <br>
					<hr>
					<table class="board">
						<tr>
							<th class="overflowtitle">제목</th>
							<th class="overflowname">이름</th>
							<th class="date">날짜</th>
							<th class="count">조회수</th>
						</tr>
						<c:forEach items="${boardQnaDtos}" var="boardQnaDtos">
							<tr>
								<td class="overflowtitle" id="title">${boardQnaDtos.btitle}</td>
								<td class="overflowname">${boardQnaDtos.bname}</td>
								<td class="date">${boardQnaDtos.bdate}</td>
								<td class="count">${boardQnaDtos.bhit}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</article>
		</section>
	</div>
	<!--footer-->
	<footer>
		<p>Copyright © 2021 Human Dev Lucky Seven. All rights reserved.</p>
			<address>Contact our developers for more information.<br>
			In the end, it's not the years in your life that count.<br>
			It's the life in your years.<br>
			- Edward J. Stieglitz
	</address>
	</footer>
	<script src="https://unpkg.com/swiper@6.8.1/swiper-bundle.min.js"></script>
	<script>
        const mySwiper = new Swiper('.swiper-container', {
            spaceBetween: 50,
            centeredSlides: true,
            pagination: {
                el: '.swiper-pagination',
                clickable: true
            },
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev'
            },
            autoplay: {
                delay: 5000,
                disableOnInteraction: true // 쓸어 넘기거나 버튼 클릭 시 자동 슬라이드 정지.
            },
            loop: true
        });

        const swiperContainer = document.querySelector('.swiper-container');

        // 데스크톱에서는 포커스가 빠진 경우 자동 슬라이드 재생.
        swiperContainer.addEventListener('focusout', () => {
            setTimeout(() => {
                if (swiperContainer.querySelector(':focus') === null) {
                    console.log('focusout, startAutoplay');
                    mySwiper.autoplay.start();
                };
            }, 100);
        });

        // 모바일에서는 화면을 움직이면 자동 슬라이드 기능 재생.
        document.addEventListener('touchmove', () => {
            console.log('touchmove, startAutoplay');
            mySwiper.autoplay.start();
        });
    </script>
</body>
</html>