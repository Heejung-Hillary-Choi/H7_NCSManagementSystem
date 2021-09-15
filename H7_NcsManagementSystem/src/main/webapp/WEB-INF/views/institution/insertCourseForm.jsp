<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<style>
input[type=text] {
width: 90%;
background: transparent;
border: none;
border-bottom: solid 1px #ccc;
padding: 5px 0px 5px 0px;
font-size: 15px;
}
section {
width: 70%;
}
p#small{
color: #757575;
font-size: small;
}
</style>

<section>
	<h2>신규 강의 개설</h2>
	<form action="${pageContext.request.contextPath}/institution/insertCourseResult" method="POST"
	enctype="multipart/form-data">
	<input type="hidden" name="inid" value="${userid}">
		<table>
			<tr>
				<th>강의명</th>
				<td><input type="text" name="cname" placeholder="공식 강의명을 입력해주세요.." required></td>
			</tr>
			<tr>	
				<th>강사 ID</th>
				<td><input type="text" name="prid" placeholder="담당 강사의 정확한 ID를 입력해주세요.." required></td>
			</tr>
			<tr>
				<th>수강기간</th>
				<td><input type="text" name="date_full" placeholder="EX) 2000.01.01 ~ 2000.12.31" required></td>
			</tr>
			<tr>
				<th>강의실</th>
				<td><input type="text" name="room_num" placeholder="EX) 3층 2교육실" required></td>
			</tr>
			<tr>
				<th>진단평가지</th>
				<td><input type="file" name="file" required>
				<p id="small">질문문항이 정리된 .csv 파일을 업로드해주세요..(❁´◡`❁)</p></td>
			</tr>
			<tr><td colspan="2"><input type="submit" class="button" value="제출"></td>
			</tr>
		</table>
	</form>
</section>
<%@ include file="../include/footer.jsp"%>