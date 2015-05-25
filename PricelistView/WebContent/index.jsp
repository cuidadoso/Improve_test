<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>START PAGE</title>
</head>
<body>
<form action="/view/pricelist" method="get">
		<input type="hidden" name="catName" value="" />
		<input type="hidden" name="prodName" value="" />
		<input type="hidden" name="lowPrice" value="" />
		<input type="hidden" name="highPrice" value="" />
		<input type="submit" name="enter" value="Показать прайс-лист" />
</form>
</body>
</html>