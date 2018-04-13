<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="util" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>제곱표</title>
</head>
<body>
	<h3>1부터 5까지의 제곱표</h3>
	<util:compute var="num" start="1" end="5">
		${num}의 제곱은?${num*num}<br>
	</util:compute>
</body>
</html>