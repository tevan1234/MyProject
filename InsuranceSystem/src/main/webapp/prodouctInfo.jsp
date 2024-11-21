<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>查看單筆保單詳情</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/InsuranceSystem/css/buttons.css">
	</head>
	<body style="background-color: #f4e500">
		<%@ include file="/WEB-INF/view/menu.jsp" %>
		<div style="padding: 15px">
			<button onclick="show()">新增保單</button>
		</div>
		
		<script>
			function show() {
				document.getElementById("form1").style.visibility = "visible";
			}
		</script>		
		<form id="form1" class="row g-3" style="padding: 15px ;visibility: hidden;">
			
			<h1>保單編號:####</h1>
			<div class="col-md-6">
				<label for="inputEmail4" class="form-label">要保人</label> <input
					type="email" class="form-control" id="inputEmail4">
			</div>
			<div class="col-md-6">
				<label for="inputPassword4" class="form-label">要保人身分證字號</label> <input
					type="password" class="form-control" id="inputPassword4">
			</div>
			<div class="col-md-6">
				<label for="inputEmail4" class="form-label">被保人</label> <input
					type="email" class="form-control" id="inputEmail4">
			</div>
			<div class="col-md-6">
				<label for="inputPassword4" class="form-label">被保人身分證字號</label> <input
					type="password" class="form-control" id="inputPassword4">
			</div>
			<div class="col-3">
				<label for="inputAddress" class="form-label">保險種類</label> <input
					type="text" class="form-control" id="inputAddress">
			</div>
			<div class="col-3">
				<label for="inputAddress" class="form-label">繳費年期</label> <input
					type="text" class="form-control" id="inputAddress">
			</div>
			<div class="col-3">
				<label for="inputAddress" class="form-label">保額</label> <input
					type="text" class="form-control" id="inputAddress">
			</div>
			<div class="col-3">
				<label for="inputAddress" class="form-label">簽約日期</label> <input
					type="text" class="form-control" id="inputAddress">
			</div>
			<div class="col-6">
				<label for="inputAddress2" class="form-label">負責業務</label> <input
					type="text" class="form-control" id="inputAddress2">
			</div>
			<div class="col-md-6">
				<label for="inputCity" class="form-label">負責業務聯絡方式</label> <input
					type="text" class="form-control" id="inputCity">
			</div>
			<p/>
			<div class="col-12">
				<div class="input-group">
					<span class="input-group-text">標註</span>
					<textarea class="form-control" aria-label="With textarea"></textarea>
				</div>
			</div>
			<div class="col-12">
				<a href="/InsuranceSystem/test.jsp" class="button-secondary pure-button">返回上一頁</a>
			</div>
		</form>
	</body>
</html>