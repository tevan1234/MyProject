<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>保險資訊</title>
<link rel="stylesheet" type="text/css" href="styles.css">
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

nav ul {
	list-style: none;
	padding: 0;
}

nav ul li {
	display: inline;
	margin: 0 15px;
}

nav ul li a {
	color: white;
	text-decoration: none;
}

.container {
	display: grid;
	grid-template-columns: repeat(3, 1fr);
	gap: 20px;
	padding: 20px;
}

.card {
	background: white;
	border-radius: 5px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	overflow: hidden;
	text-align: center;
	padding: 15px;
}

.card img {
	width: 100%;
	height: 50%;
}

footer {
	text-align: center;
	padding: 20px;
	background: #007bff;
	color: white;
	position: relative;
	bottom: 0;
	width: 100%;
}
</style>
</head>
<body style="background-color: #f4e500">
	<%@ include file="/WEB-INF/view/menu.jsp"%>

	<div class="container">
		<div class="card" id="home">
			<h2>保險的重要性</h2>
			<img src="image/1.jpg" alt="保險的重要性">
			<p>保險在現代生活中扮演著不可或缺的角色，其主要目的在於分散風險並提供經濟保障，尤其在突發事件或意外發生時尤為重要。意外、疾病或財產損失等風險無法預測，這些事件往往會給個人和家庭帶來沉重的財務負擔，而保險可以在這些關鍵時刻提供支持。
				個人購買健康險、意外險或人壽險，能夠在面對醫療費用或收入中斷時獲得經濟援助，使自己和家人免於財務壓力，專注於康復或重建生活。而企業也需要透過各類保險，如財產險、責任險等，來保障自身的財務安全，防範意外事故或法律糾紛帶來的潛在損失。
				保險還對社會穩定和經濟繁榮有重要影響。透過風險分擔機制，讓所有人能夠在不確定性中獲得一定程度的保障，有助於減輕社會整體的財務壓力。總的來說，保險是一項至關重要的投資，它不僅為個人和家庭提供安全網，也推動了更廣泛的經濟穩定和社會和諧。</p>
		</div>

		<div class="card" id="about">
			<h2>關於我們</h2>
			<img src="image/2.jpg" alt="關於我們">
			<p>我們是一家專業的保險公司，致力於為客戶提供最優質的保險服務。我們的團隊擁有多年的行業經驗，能夠為您提供量身定制的保險解決方案。</p>
		</div>

		<div class="card" id="services">
			<h2>我們的服務</h2>
			<img src="image/3.jpg" alt="我們的服務">
			<!-- <ul>
                <li>健康保險</li>
                <li>人壽保險</li>
                <li>汽車保險</li>
                <li>財產保險</li> 
            </ul> -->
		</div>
	</div>

	<footer>
		<h2>聯絡我們</h2>
		<p>電話: 123-456-7890</p>
		<p>電子郵件: info@insurance.com</p>
	</footer>
</body>
</html>
