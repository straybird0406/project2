<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*, hw1.ProductsBean"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料取得失敗</title>
<link rel="stylesheet" href="style.css">
</head>
<body >
  
	<%@ include file="/topic2/navtest.html"%>
	<div class="content">
	<div align="center">
		<h2>資料取得失敗</h2>
		<% hw1.ProductsBean product = (hw1.ProductsBean) request.getAttribute("product"); %>
				
			<h3>無法取得商品資料，請確認是否正確輸入。</h3>
	
		<a href="RedeemHomePage.jsp">回到首頁</a>
		
	</div>
	</div>
</body>
</html>