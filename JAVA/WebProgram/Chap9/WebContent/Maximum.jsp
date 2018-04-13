<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>최대값 구하기</title>
</head>
<body>
	최대값:
	<c:if test="${param.NUM1-param.NUM2>=0}">
		${param.NUM1}
	</c:if>
	<c:if test="${param.NUM1-param.NUM2<0}">
		${param.NUM2}
	</c:if>
</body>
</html>