<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>문법 설명</title>
</head>
<body>
	<h3>FONT 태그에 대하여</h3>
	<c:out value="<FONT size =7>커다란 글씨</FONT>는 다음과 같은 출력을 합니다." />
	<br>
	<br>
	<c:out value="<FONT size =7>커다란 글씨</FONT>" escapeXml="false" />
</body>
</html>