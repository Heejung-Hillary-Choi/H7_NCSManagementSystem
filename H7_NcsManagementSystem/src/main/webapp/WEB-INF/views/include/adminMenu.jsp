<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	
<div class="dropdown-menu">
	<ul>
		<li class="menu1">사용자 관리 <i class="fa fa-caret-down"></i>
			<ul>
				<li class="menu2"><a href="/controller/admin/adminNewMember">신규회원
						관리</a></li>
				<li class="menu2"><a
					href="/controller/admin/adminDormantMember">휴면회원 관리</a></li>
			</ul>
		</li>
		<li class="menu3"><a href="/controller/admin/adminNcsTest">진단평가지
				등록 </a></li>
		<!-- <li class="menu1">게시판 <i class="fa fa-caret-down"></i>
			<ul>
				<li class="menu2">소메뉴1</li>
				<li class="menu2">소메뉴2</li>
				<li class="menu2">소메뉴3</li>
			</ul>
		</li> -->
		<!-- 		<li class="menu1">학생기능관리 <i class="fa fa-caret-down"></i>
			<ul>
				<li class="menu2">나의 강의목록</li>
				<li class="menu2">진단평가 수행</li>
				<li class="menu2">평가내역 조회</li>
			</ul>
		</li>
		<li class="menu1">강사기능관리 <i class="fa fa-caret-down"></i>
			<ul>
				<li class="menu2">나의 강의목록</li>
				<li class="menu2">평가 내역 조회</li>
			</ul>
		</li>
		<li class="menu1">학원 기능 관리 <i class="fa fa-caret-down"></i>
			<ul>
				<li class="menu2">신규 강의 개설</li>
				<li class="menu2">수강생 등록</li>
				<li class="menu2">평가내역 조회</li>
			</ul>
		</li> -->
	</ul>
	<div class="admin_login">
		<a id="loginbtn"
			onclick="document.getElementById('gologout').submit();">로그아웃</a>
		<form:form action="${pageContext.request.contextPath}/logout"
			id='gologout' method="POST" />
	</div>
</div>
