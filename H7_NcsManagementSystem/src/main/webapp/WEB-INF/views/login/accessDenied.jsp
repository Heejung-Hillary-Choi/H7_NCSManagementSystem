<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<div>
<script type="text/javascript">
swal({
title: "Access Denied!",
text: "해당 페이지에 접근이 거부되었습니다.\n권한을 확인하시거나 관리자에게 연락해주세요..",
icon: "error"
}).then(function() {
window.history.go(-1);
});			
</script>
</div>
