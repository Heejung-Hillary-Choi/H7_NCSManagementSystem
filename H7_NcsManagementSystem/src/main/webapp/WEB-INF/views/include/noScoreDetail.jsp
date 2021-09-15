<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<div>
	<c:if test="${error != null}">
		<script type="text/javascript">
			swal({
				title : "Oops!",
				text : "해당 학생은 진단평가 미실시 상태입니다..",
				icon : "warning"
			}).then(function() {
				window.history.go(-1);
			});
		</script>
	</c:if>
</div>
