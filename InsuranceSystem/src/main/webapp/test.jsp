<%@page import="InsuranceSystem.model.dto.ProdouctDto"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%
	List<ProdouctDto>prodouctDtos = (List<ProdouctDto>)request.getAttribute("prodouctDtos");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<link rel="stylesheet" href="/InsuranceSystem/css/buttons.css">
	</head>
	<body style="background-color: #f4e500">
		<%@ include file="/WEB-INF/view/menu.jsp" %>
		
		<form class="pure-form" style="padding: 15px">
			<fieldset>
				<legend>保單詳情</legend>
				<table class="pure-table pure-table-bordered" style="background-color: white;">
					<thead>
						<tr>
							<th>保單編號</th><th>要保人</th><th>被保人</th><th>保險種類</th><th>負責業務</th>
							<th>詳細資訊</th>
						</tr>
					</thead>					
						<tr>
							<td style="text-align: center;"><%= "1" %></td>
							<td><%= "Alan" %></td>
							<td><%= "kevin" %></td>
							<td><%= "意外險" %></td>
							<td><%= "John" %></td>	
							<td><a href="#" class="button-secondary pure-button">詳細資訊</a></td>							
						</tr>	
						
						<tr>
							<td style="text-align: center;"><%= "2" %></td>
							<td><%= "Ben" %></td>
							<td><%= "Hanry" %></td>
							<td><%= "旅平險" %></td>
							<td><%= "Adam" %></td>	
							<td><a href="#" class="button-secondary pure-button">詳細資訊</a></td>							
						</tr>
						
						<tr>
							<td style="text-align: center;"><%= "3" %></td>
							<td><%= "Bob" %></td>
							<td><%= "Cindy" %></td>
							<td><%= "重大傷病險" %></td>
							<td><%= "Alex" %></td>	
							<td><a href="/InsuranceSystem/prodouctInfo.jsp" class="button-secondary pure-button">詳細資訊</a></td>							
						</tr>				
				</table>
			</fieldset>
		</form>
	</body>
</html>