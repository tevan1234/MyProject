<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="UTF-8">
    <title>局部頁面切換</title>
</head>
<body>
    <h1>我的網站</h1>

    <!-- 導航欄 -->
    <nav>
        <button onclick="loadPage('/InsuranceSystem/home.jsp')">頁面1</button>
        <button onclick="loadPage('/InsuranceSystem/prodouct.jsp')">頁面2</button>
    </nav>

    <!-- 使用 iframe 顯示局部內容 -->
    <iframe id="contentFrame" src="/InsuranceSystem/home.jsp" width="100%" height="500px" style="border: none;"></iframe>

    <script>
        function loadPage(url) {
            document.getElementById("contentFrame").src = url;
        }
    </script>
</body>
</html>
