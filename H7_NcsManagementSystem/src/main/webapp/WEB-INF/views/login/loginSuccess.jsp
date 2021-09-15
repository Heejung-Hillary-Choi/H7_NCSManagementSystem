<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<div>
<script type="text/javascript">
swal({
title: "WELCOME!",
text: "로그인하셨습니다..",
icon: "success",
button: "확★인"
}).then(function() {
window.location.href = "../mainPage";
});			
</script>
</div>