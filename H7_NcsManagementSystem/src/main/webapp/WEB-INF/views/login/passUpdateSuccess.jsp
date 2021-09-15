<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<div>
<script type="text/javascript">
swal({
title: "WOW!",
text: "비밀번호가 변경되었습니다..",
icon: "success",
button: "메인페이지로"
}).then(function() {
window.location.href = "../mainPage";
});			
</script>
</div>