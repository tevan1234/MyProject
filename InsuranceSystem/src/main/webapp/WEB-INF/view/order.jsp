<%@page import="InsuranceSystem.model.dto.UserCert"%>
<%@page import="InsuranceSystem.model.dto.OrderDto"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%
	List<OrderDto>orderDtos = (List<OrderDto>)request.getAttribute("orderDtos");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>保單系統</title>
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- DataTables -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>

    <!-- DataTables Buttons -->
    <link rel="stylesheet" href="https://cdn.datatables.net/buttons/2.4.1/css/buttons.dataTables.min.css">
    <script src="https://cdn.datatables.net/buttons/2.4.1/js/dataTables.buttons.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.html5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/2.4.1/js/buttons.print.min.js"></script>
</head>
<body style="background-color: #f4e500">
	<%@ include file="/WEB-INF/view/menu.jsp" %>
    <div class="container mt-4">
        <h1>保單列表</h1>
        <table id="myTable" class="display" style="background-color: white; width:100%">
            <thead>
                <tr>
                    <th>保單編號</th>
                    <th>要保人</th>
                    <th>被保人</th>
                    <th>保險種類</th>
                    <th>負責業務</th>
                    <th>修改</th>
                    <th>詳細資訊</th>
                </tr>
            </thead>
            <tbody>
                <% for(OrderDto orderDto : orderDtos) { %>
                    <tr>
                        <td><%= orderDto.getOid() %></td>
                        <td><%= orderDto.getHolder() %></td>
                        <td><%= orderDto.getInsured() %></td>
                        <td><%= orderDto.getOtype() %></td>
                        <td><%= orderDto.getOcharge() %></td>
                        <td><a href="/InsuranceSystem/order/get?orderId=<%= orderDto.getOid() %>" class="btn btn-secondary">修改</a></td>
                        <td><a href="/InsuranceSystem/order/info?orderId=<%= orderDto.getOid() %>" class="btn btn-info">詳細資訊</a></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <script>
        $(document).ready(function() {
            $('#myTable').DataTable({
            	
                language: {
                    url: "https://cdn.datatables.net/plug-ins/1.11.3/i18n/zh_Hant.json"
                },
                lengthMenu: [5, 10, 15],
                
                pageLength: 5,
                dom: 'RlBfrtip',
                layout: {
			        topStart: {
			            buttons: ['copy', 'csv', 'excel', 'pdf', 'print']
			        }
			    }
            });
        });
    </script>
</body>
</html>
