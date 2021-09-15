<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>
<style>
select {
width: 70%;
}
</style>
<script type="text/javascript">
var cascadingList = {
	<c:forEach items="${cnameList}" var="cnameList" varStatus="loop">	
		"${cnameList.name}" : [ 
			<c:forEach items="${ncsList}" var="ncsList" varStatus="loop">
				<c:if test="${ncsList.cid == cnameList.cid}">
				"${ncsList.name}"${!loop.last ? ',' :''} 
				</c:if>	
			</c:forEach>
		]${!loop.last ? ',' : ''}
	</c:forEach>
}
window.onload = function() {
	var firstSelect = document.getElementById("cname");
	var SecondSelect = document.getElementById("ncs_name");
	for (var x in cascadingList) {
		 firstSelect.options[firstSelect.options.length] = new Option(x, x);
	}
	firstSelect.onchange = function() {
		SecondSelect.length = 1;
		var y = cascadingList[this.value];
	    for (var i = 0; i < y.length; i++) {
	    	SecondSelect.options[SecondSelect.options.length] = new Option(y[i], y[i]);
	    	
	    }
	}
}

</script>

<section>
	<div>
	<h2>진단평가 결과조회</h2>
		<form action="prScoreList2" method="get">
		<table>
				<tr>
					<th colspan="2">확인할 진단평가 정보를 선택해주세요.</th>
				</tr>
				<tr>
					<td>강의명</td>
					<td><select name="cname" id="cname">
							<option value="" selected="selected">강의명을 선택해주세요.</option>
					</select></td>
				</tr>
				<tr>
					<td>능력단위명</td>
					<td><select name="ncs_name" id="ncs_name">
							<option value="" selected="selected">강의명을 먼저 선택해주세요.</option>
					</select></td>
				</tr>
				<tr>
					<td>사전/사후</td>
					<td><input type="radio" name="type" value="1"
						checked="checked">사전 &emsp;&emsp;&emsp; <input type="radio"
						name="type" value="2">사후</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" class="button"></td>
				</tr>
			</table>
		</form>
	</div>
</section>
<%@ include file="../include/footer.jsp"%>