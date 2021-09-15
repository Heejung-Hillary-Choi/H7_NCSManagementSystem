<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<div>
<script type="text/javascript">
swal({
title: "Insert Denied!",
text: "입력한 모든 정보가 정확한지 다시한번 확인해주세요..",
icon: "error"
}).then(function() {
window.history.go(-1);
});			
</script>
</div>
