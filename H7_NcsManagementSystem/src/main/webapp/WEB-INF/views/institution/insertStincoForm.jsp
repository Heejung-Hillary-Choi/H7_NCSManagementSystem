<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include/header.jsp"%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style>
select {
width: 70%;
}
input[type=checkbox]{
width:15px;
height:15px;
}
</style>

<section>
	<h2>수강생 등록</h2>
	<form action="${pageContext.request.contextPath}/institution/insertStincoProve" method="GET">
		<!-- 해당 페이지 진입시 userid(==inid)를 이용해 DefaultCourseDTO의 list를 model.addattribute에 가져온 상태.
		셀렉트박스에서 한 강의명을 선택하면, onchange 실패! ㅋ
		courseInfoDiv가 onchange function을 통해 display되면서 선택한 강의의 정보를 보여준다. 
		그리고 다음 div에서, 모든 학생의 정보 list를 보여주고, 각 열 맨 처음에 체크박스가 있어서 한번에 여러 명의 수강생을 등록할 수 있게 한다.
		학생을 선택하는 div는 div 자체에 overflow style을 주어 height를 고정하되 스크롤바가 나오도록 하자. -->
		<h2>강의 선택</h2>
		<div id="selectCourse">	
			<table>
				<tr><th>강의명</th></tr>
				<tr>
					<td><select id="selectedCID" name="cid" required>
						<option value="">강의명을 선택해주세요.</option>
						<c:forEach items="${ourCourses}" var="ourCourses">
							<option value="${ourCourses.cid}">${ourCourses.cname}</option>
						</c:forEach>
					</select></td>
				</tr><tr>
					<td>현재 로그인된 교육기관의 강의에 대해서만 작업이 가능합니다.</td>
				</tr>
			</table>
		</div>
		<h2>학생 선택</h2>
		<div id="selectStudents" style="overflow: auto; height: 300px;">	
			<table>
				<tr>
					<th>순 번</th>
					<th>선 택</th>
					<th>학생ID</th>
					<th>이 름</th>
					<th>연락처</th>
					<th>기본주소</th>
				</tr>
				<c:forEach items="${allStudents}" var="allStudents" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td><input type="checkbox" name="stid" value="${allStudents.stid}" ></td>
					<td>${allStudents.stid}</td>
					<td>${allStudents.name}</td>
					<td>${allStudents.phone}</td>
					<td>${allStudents.address}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<input type="submit" class="button" value="완료" id="submitBtn">
	</form>
</section>

<script type="text/javascript">
window.onload = function() {
	console.log('..onload function started.');
	
	var getBtnbyId = document.getElementById('submitBtn');
	getBtnbyId.addEventListener("click",  preventSubmit, false);
	console.log('event listener set.');
	
	console.log('..onload function completed.');
};

function preventSubmit(e){
	console.log('..function preventSubmit started.');

	e.preventDefault();
	console.log('default event prevented.');
	var isRightId = e.target.id;
	console.log('targeted id is '+isRightId);

	var RVisChecked = isChecked();
	console.log('final RVisChecked : '+RVisChecked);
	if(RVisChecked){
		var getBtnbyId = document.getElementById('submitBtn');
		getBtnbyId.removeEventListener("click", preventSubmit);
		console.log('event listener removed!!!');
	}
	
	console.log('..function preventSubmit completed.');
};

function isChecked(){
	console.log('..function isChecked started.');

	var resultValue = false;	
	console.log('now RV '+resultValue);
	var listCheckBox = document.getElementsByName('stid');
	var count = 0;

	for(var i=0;i<listCheckBox.length;i++){
		if(listCheckBox[i].checked){
			count++;
			console.log(i+"th count : "+count);
		}
	}
	
	if(count==0){
		swal({
			title: "No Student Checked!",
			text: "목록에서 한 명 이상의 학생을 선택해주시기 바랍니다..",
			icon: "warning"
		});
		resultValue = false;
		console.log('now RV still '+resultValue);

	}else{
		resultValue = true;
		console.log('now RV '+resultValue+'!!!');
	}
	
	console.log('..function isChecked completed.');
	return resultValue;
};
</script>
<%@ include file="../include/footer.jsp"%>