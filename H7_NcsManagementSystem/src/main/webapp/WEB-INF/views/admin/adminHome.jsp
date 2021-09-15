<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="../include/loginHeader.jsp"%>
<!-- c:if test="${is2ndAtuthrized == null}"
	<script>
		swal({
			title: "Oh, no!",
			text: "관리자 페이지 진입을 위해서는 2차 인증이 필요합니다.",
			icon: "warning"}).then(function() {
				window.location.href = "/controller/admin/admin2ndAuth";
			});		
	</script> -->
<div id="item">
	<%@ include file="../include/adminMenu.jsp"%>
</div>
<%@ include file="../include/footer.jsp"%>
