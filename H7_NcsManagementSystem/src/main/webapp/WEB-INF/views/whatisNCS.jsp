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
	href="${pageContext.request.contextPath}/resources/css/table.css">
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
		<section>
			<h2>NCS(국가직무능력표준)이란?</h2>
			<table>
				<tr>
					<th>✔NCS(국가직무능력표준, National Competency Standards)는 무엇인가요?</th>
				</tr>
				<tr>
					<td>산업현장에서 직무를 수행하는 데 필요한 능력(지식, 기술, 태도)을 국가가 표준화한 것입니다. <br>교육훈련
						‧ 자격에 NCS를 활용하여 현장중심의 인재를 양성할 수 있도록 지원하고 있습니다.
					</td>
				</tr>
				<tr>
					<th>✔NCS 개념도</th>
				</tr>
				<tr>
					<td><img src="${pageContext.request.contextPath}/resources/img/whatisncs1.png"><br>’21년 기준
						1,039개의 NCS가 개발되었으며 클라우드플랫폼구축, 수소연료전지제조, 스마트공장 시스템설치 등 미래 일자리 변화에
						대응한 직무도 포함</td>
				</tr>
				<tr>
					<th>✔NCS를 활용하면 어떤 점이 좋을까요?</th>
				</tr>
				<tr>
					<td>기업은 NCS를 활용해서 조직 내 직무를 체계적으로 분석하고 이를 토대로 직무 중심의 인사제도를(채용,
						배치, 승진, 교육, 임금 등) 운영할 수 있습니다.</td>
				</tr>
				<tr>
					<td>취업준비생은 기업이 어떤 능력을 지닌 사람을 채용하고자 하는지 명확히 알고 이에 맞춰 직무능력을 키울 수
						있어 스펙 쌓기 부담이 줄어듭니다.</td>
				</tr>
				<tr>
					<td>교수자는 NCS를 활용하여 교육과정을 설계함으로써 체계적으로 교육훈련과정을 운영할 수 있고, 이를 통해
						산업현장에서 필요로 하는 실무형 인재를 양성할 수 있습니다.</td>
				</tr>
				<tr>
					<td>국가기술자격을 직무 중심(NCS 활용)으로 개선하여 실제로 그 일을 잘할 수 있는 사람이 자격증을 딸 수
						있도록 해줍니다.</td>
				</tr>
			</table>
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
</body>
</html>