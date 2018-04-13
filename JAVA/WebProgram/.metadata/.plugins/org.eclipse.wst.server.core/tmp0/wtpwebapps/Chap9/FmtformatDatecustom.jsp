<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="date" value="<%=new Date()%>" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>현재의 시각</title>
</head>
<body>
	[S]
	<fmt:formatDate value="${date}" type="both" dateStyle="short"
		timeStyle="short" />
	<br> [M]
	<fmt:formatDate value="${date}" type="both" dateStyle="medium"
		timeStyle="medium" />
	<br> [L]
	<fmt:formatDate value="${date}" type="both" dateStyle="long"
		timeStyle="long" />
	<br> [F]
	<fmt:formatDate value="${date}" type="both" dateStyle="full"
		timeStyle="full" />
</body>
</html>