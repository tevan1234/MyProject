<%@page import="InsuranceSystem.model.dto.ProdouctDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ProdouctDto prodouctDto = (ProdouctDto)request.getAttribute("prodouctDto");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>更新商品資料</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/InsuranceSystem/css/buttons.css">
	</head>
	<body style="background-color: #f4e500">
		<%@ include file="/WEB-INF/view/menu.jsp" %>
		<form style="padding: 15px" class="pure-form" method="post" action ="/InsuranceSystem/prodouct/update">
			<fieldset>
				<legend>更新商品資料</legend>
				序號: <input type="text" name="prodouctId" value="<%=prodouctDto.getPid() %>" readonly /><p />
				保險種類: <select name="prodouctType">
							<option value="汽車險" <%=prodouctDto.getPtype().equals("汽車險")?"selected":"" %>>汽車險</option>
							<option value="旅平險" <%=prodouctDto.getPtype().equals("旅平險")?"selected":"" %>>旅平險</option>
							<option value="重大傷病險" <%=prodouctDto.getPtype().equals("重大傷病險")?"selected":"" %>>重大傷病險</option>
							<option value="意外險" <%=prodouctDto.getPtype().equals("意外險")?"selected":"" %>>意外險</option>
							<option value="天災險" <%=prodouctDto.getPtype().equals("天災險")?"selected":"" %>>天災險</option>
							<option value="儲蓄險" <%=prodouctDto.getPtype().equals("儲蓄險")?"selected":"" %>>儲蓄險</option>
					  </select><p />
				年繳額:<input type="text" name="price" placeholder="請輸入年繳額" required /><p />
				商品上架: <input type = "radio" name="prodouctStatus" value="true" <%=prodouctDto.getPstatus() ? "checked" : ""%>>在售
					  	  <input type = "radio" name="prodouctStatus" value="false" <%=prodouctDto.getPstatus() ? "" : "checked"%>>已停售<p />
				
				<button type="submit" class="button-success pure-button">Update</button>
			</fieldset>		
		</form>
	</body>
</html>