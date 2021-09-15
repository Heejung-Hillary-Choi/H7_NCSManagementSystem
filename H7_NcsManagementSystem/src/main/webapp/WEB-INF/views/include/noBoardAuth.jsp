<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<div>
<script type="text/javascript">
swal({
title: "Oops!",
text: "현재 권한으로는 해당 기능에 접근이 불가합니다..",
icon: "warning"
}).then(function() {
window.history.go(-1);
});			
</script>
</div>
