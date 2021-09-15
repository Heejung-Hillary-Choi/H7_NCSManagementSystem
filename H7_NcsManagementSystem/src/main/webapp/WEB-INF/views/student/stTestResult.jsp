<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<script type="text/javascript">
	function controlbt() {
		var obj_bt = document.getElementById('cbt'); //클릭 메뉴의 아이디
		var obj = document.getElementById('box'); //펼쳐질 박스의 아이디

		if (obj.style.display == 'none') { //닫혀 있다면
			obj.style.display = ''; //펼쳐주고
			obj_bt.innerText = '총점과 평균 닫기'; //닫기 라고 표기하고
		} else { //열려 있다면
			obj.style.display = 'none'; //닫아 주고
			obj_bt.innerText = '총점과 평균 펼쳐보기'; //펼치기라고 표기 하고
		}
	}
</script>

<section>
	<h2>진단평가 결과</h2>
	<div class="showResult">
			<table>
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
				<th>입력답안</th>
			</tr>
			<c:forEach items="${qrdtos}" var="qrdtos">
				<tr>
					<td>${qrdtos.ques_area}</td>
					<td align="left">${qrdtos.ques_num} ${qrdtos.question}</td>
					<td width="60px">${qrdtos.answer}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br>
	<div id="cbt" class="button" onClick="controlbt()" style="cursor: pointer; width: 200px; margin: auto; margin-bottom:20px; padding: 10px;">총점과 평균 펼쳐보기</div>
	<div class="submenu" id="box" style="display: none;">
		<table>
			<tr>
				<th>진단영역</th>
				<th>문항수</th>
				<th>점수</th>
				<th>점수/문항수</th>
			</tr>
			<c:forEach items="${trldtos}" var="trldtos">
				<tr>
					<td>${trldtos.ques_area }</td>
					<td>${trldtos.count }</td>
					<td>${trldtos.sum }</td>
					<td>${trldtos.avg }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
<%@ include file="../include/footer.jsp"%>
