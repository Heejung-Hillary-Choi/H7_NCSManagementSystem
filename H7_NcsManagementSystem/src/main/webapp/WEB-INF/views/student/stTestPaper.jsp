<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<head>
<link href="${pageContext.request.contextPath}/resources/css/table.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>

<c:if test="${resultAlreadyIn != null}">
	<script>
    	swal("이미 수행한 평가입니다!","OK버튼을 누르면 결과를 조회하러 이동합니다..", "info").then(okay => {
    		  if (okay) {
    		    window.location.href = "../student/stTestResult?tid=${tid}";
    		  }
    		});
//    	location.href = "../student/stTestResult?tid=${tid}";

	</script>
</c:if>

<section>
	<form action="stTestResultInsert" method="post">
		<input type="hidden" name="stid" value="${userid}"> 
		<input type="hidden" name="tid" value="${tid }">
		<h2>상단의 정보가 정확한지 확인 후, 하단의 진단평가를 실시해주세요.</h2>
		<table>
			<tr>
				<td colspan="6" style="font-size:large;"><br>(${type}) 학습자 자가 진단 평가<br><br></td>
			</tr>
			<tr>
				<th>교육기관</th>
				<td>${tldtos.name}</td>
				<th>교육기간</th>
				<td colspan="3">${tldtos.date_session}</td>
			</tr>
			<tr>
				<th>평가일시</th>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${tldtos.date_test}" /></td>
				<th>과정명</th>
				<td>${tldtos.cname}</td>
				<th>학생명</th>
				<td>${tldtos.sname}</td>
			</tr>
			<tr>
				<th>교과목</th>
				<td colspan="3">${tldtos.ncs_name}</td>
				<th>평가자</th>
				<td>${tldtos.pname}</td>
			</tr>
			<tr>
				<th>능력단위명</th>
				<td colspan="5">${tldtos.ncs_name}</td>
			</tr>
			<tr>
				<th>능력단위코드</th>
				<td colspan="5">${tldtos.ncs_num}</td>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<th>진단영역</th>
				<th>진단문항</th>
				<th>매우<br>미흡
				</th>
				<th>미흡</th>
				<th>보통</th>
				<th>우수</th>
				<th>매우<br>우수
				</th>
			</tr>
			<c:forEach items="${tqldtos}" var="tqldtos" varStatus="status">
				<tr>
					<td>${tqldtos.ques_area}</td>
					<td align="left"><input type="hidden" name="qn${status.count}"
						value="${tqldtos.ques_num}">${tqldtos.ques_num}
						${tqldtos.question}</td>
					<td><input type="radio" name="answer${status.count}" value="1" required></td>
					<td><input type="radio" name="answer${status.count}" value="2" required></td>
					<td><input type="radio" name="answer${status.count}" value="3" required></td>
					<td><input type="radio" name="answer${status.count}" value="4" required></td>
					<td><input type="radio" name="answer${status.count}" value="5" required></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7" align="center"><input type="hidden"
					name="ques_count" value="${tqldtos.size()}"> <input
					type="submit" value="제출" class="button"></td>
			</tr>
		</table>
	</form>
<%@ include file="../include/footer.jsp"%>
