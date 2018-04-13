<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>숫자 포맷</title>
</head>
<body>
	금액:
	<fmt:formatNumber value="1000000" type="currency" currencySymbol="￦" />
	<BR> 퍼센트:
	<fmt:formatNumber value="0.99" type="percent" />
</body>
</html>