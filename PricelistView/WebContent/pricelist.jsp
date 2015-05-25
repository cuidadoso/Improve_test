<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>P R I C E L I S T</title>
</head>
<body>
	<h1>Прайс-лист</h1>
	<form action="/view/pricelist" method="get">
		<table>
			<tr>
				<td>Категория:</td>
				<td>Наименование:</td>
				<td>Цена от:</td>
				<td>Цена до:</td>
			</tr>
			<tr>
				<td><input type="text" name="catName"/></td>
				<td><input type="text" name="prodName"/></td>
				<td><input type="text" name="lowPrice"/></td>
				<td><input type="text" name="highPrice"/></td>
				<td><input type="submit" name="prodSearch" value="Найти"/></td>
			</tr>
		</table>	
	</form>
	<table border=0>
		<tr>
			<td width="200">Категория</td>
			<td width="400">Наименование</td>
			<td width="100">Цена</td>
		</tr>
	<c:forEach items="${products}" var="product">
		<tr>
			<td>${product.cat.name}</td>
			<td>${product.name}</td>
			<td>${product.price}</td>
		</tr>
	</c:forEach>
	</table>
	<br/>	
</body>
</html>